package com.example.speech

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

enum class TtsStatus {
    INITIALIZING,
    AVAILABLE,
    UNAVAILABLE
}

enum class OfflineRecognitionCapability {
    AVAILABLE_ON_DEVICE,
    UNAVAILABLE_NO_ENGINE,
    UNAVAILABLE_OFFLINE_NOT_SUPPORTED
}

sealed class SpeechPracticeState {
    data object Idle : SpeechPracticeState()
    data object Listening : SpeechPracticeState()
    data class Result(
        val spokenText: String,
        val accuracyPercent: Int,
        val feedbackEn: String,
        val feedbackUr: String
    ) : SpeechPracticeState()
    data class UnavailableOrError(
        val messageEn: String,
        val messageUr: String
    ) : SpeechPracticeState()
}

class OfflineSpeechController(private val appContext: Context) {

    private var tts: TextToSpeech? = null
    private var speechRecognizer: SpeechRecognizer? = null

    private val _ttsStatus = MutableStateFlow(TtsStatus.INITIALIZING)
    val ttsStatus: StateFlow<TtsStatus> = _ttsStatus.asStateFlow()

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    private val _offlineCapability = MutableStateFlow(detectOfflineRecognitionCapability())
    val offlineCapability: StateFlow<OfflineRecognitionCapability> = _offlineCapability.asStateFlow()

    private val _practiceState = MutableStateFlow<SpeechPracticeState>(SpeechPracticeState.Idle)
    val practiceState: StateFlow<SpeechPracticeState> = _practiceState.asStateFlow()

    private var currentTargetSentence: String = ""

    init {
        initTextToSpeech()
    }

    private fun initTextToSpeech() {
        try {
            tts = TextToSpeech(appContext) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    val result = tts?.setLanguage(Locale.US)
                    val fallback = if (
                        result == TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED
                    ) {
                        tts?.setLanguage(Locale.ENGLISH)
                    } else {
                        result
                    }
                    if (
                        fallback == TextToSpeech.LANG_MISSING_DATA ||
                        fallback == TextToSpeech.LANG_NOT_SUPPORTED
                    ) {
                        _ttsStatus.value = TtsStatus.UNAVAILABLE
                    } else {
                        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                            override fun onStart(utteranceId: String?) {
                                _isSpeaking.value = true
                            }

                            override fun onDone(utteranceId: String?) {
                                _isSpeaking.value = false
                            }

                            @Deprecated("Deprecated in Java")
                            override fun onError(utteranceId: String?) {
                                _isSpeaking.value = false
                            }
                        })
                        _ttsStatus.value = TtsStatus.AVAILABLE
                    }
                } else {
                    _ttsStatus.value = TtsStatus.UNAVAILABLE
                }
            }
        } catch (_: Throwable) {
            _ttsStatus.value = TtsStatus.UNAVAILABLE
        }
    }

    private fun detectOfflineRecognitionCapability(): OfflineRecognitionCapability {
        return try {
            if (!SpeechRecognizer.isRecognitionAvailable(appContext)) {
                OfflineRecognitionCapability.UNAVAILABLE_NO_ENGINE
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (SpeechRecognizer.isOnDeviceRecognitionAvailable(appContext)) {
                    OfflineRecognitionCapability.AVAILABLE_ON_DEVICE
                } else {
                    OfflineRecognitionCapability.UNAVAILABLE_OFFLINE_NOT_SUPPORTED
                }
            } else {
                // On API 24–30, SpeechRecognizer with EXTRA_PREFER_OFFLINE is used and verified by error codes
                OfflineRecognitionCapability.AVAILABLE_ON_DEVICE
            }
        } catch (_: Throwable) {
            OfflineRecognitionCapability.UNAVAILABLE_NO_ENGINE
        }
    }

    fun refreshCapabilities() {
        _offlineCapability.value = detectOfflineRecognitionCapability()
    }

    fun speak(text: String, rate: Float = 0.95f) {
        if (_ttsStatus.value != TtsStatus.AVAILABLE || text.isBlank()) return
        tts?.setSpeechRate(rate.coerceIn(0.6f, 1.4f))
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "utt_${System.currentTimeMillis()}")
    }

    fun speakSequence(lines: List<String>, rate: Float = 0.92f) {
        if (_ttsStatus.value != TtsStatus.AVAILABLE || lines.isEmpty()) return
        tts?.setSpeechRate(rate.coerceIn(0.6f, 1.4f))
        lines.forEachIndexed { index, line ->
            val queueMode = if (index == 0) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD
            tts?.speak(line, queueMode, null, "seq_${index}_${System.currentTimeMillis()}")
        }
    }

    fun stopSpeaking() {
        tts?.stop()
        _isSpeaking.value = false
    }

    fun startOfflineListening(targetSentence: String) {
        currentTargetSentence = targetSentence
        val capability = detectOfflineRecognitionCapability()
        _offlineCapability.value = capability

        if (capability != OfflineRecognitionCapability.AVAILABLE_ON_DEVICE) {
            _practiceState.value = SpeechPracticeState.UnavailableOrError(
                messageEn = "Offline speech recognition is not installed on this device. This app never sends your voice online. To enable offline mic checks, install 'English (US)' in Android Settings > System > Languages > Offline Speech Recognition—or use Listen & Read-Aloud mode below!",
                messageUr = "اس فون میں آف لائن آواز پہچاننے کا سسٹم موجود نہیں ہے۔ یہ ایپ آپ کی آواز کبھی آن لائن نہیں بھیجتی۔ براہ کرم فون کی سیٹنگز میں Offline Speech Recognition انسٹال کریں یا نیچے سن کر بلند آواز سے مشق کریں۔"
            )
            return
        }

        try {
            speechRecognizer?.destroy()
            speechRecognizer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                SpeechRecognizer.createOnDeviceSpeechRecognizer(appContext)
            } else {
                SpeechRecognizer.createSpeechRecognizer(appContext)
            }

            speechRecognizer?.setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {
                    _practiceState.value = SpeechPracticeState.Listening
                }

                override fun onBeginningOfSpeech() {
                    _practiceState.value = SpeechPracticeState.Listening
                }

                override fun onRmsChanged(rmsdB: Float) = Unit
                override fun onBufferReceived(buffer: ByteArray?) = Unit
                override fun onEndOfSpeech() = Unit

                override fun onError(error: Int) {
                    val (msgEn, msgUr) = when (error) {
                        SpeechRecognizer.ERROR_NO_MATCH, SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> Pair(
                            "No clear English speech was heard. Tap the microphone and speak clearly near the phone.",
                            "واضح انگریزی آواز سنائی نہیں دی۔ مائیک دبا کر فون کے قریب صاف آواز میں بولیں۔"
                        )
                        SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> Pair(
                            "Microphone permission is required for speaking evaluation.",
                            "بولنے کی جانچ کے لیے مائیکروفون کی اجازت ضروری ہے۔"
                        )
                        else -> Pair(
                            "Offline English voice pack is not downloaded in your Android speech engine. Download 'English (US)' offline speech data in Android Settings, or practice aloud with Audio Playback.",
                            "آپ کے اینڈرائیڈ فون میں انگریزی کا آف لائن وائس پیک ڈاؤن لوڈ نہیں ہے۔ سیٹنگز سے آف لائن پیک ڈاؤن لوڈ کریں یا آڈیو سن کر مشق کریں۔"
                        )
                    }
                    _practiceState.value = SpeechPracticeState.UnavailableOrError(msgEn, msgUr)
                }

                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    val bestTranscript = matches?.firstOrNull().orEmpty()
                    if (bestTranscript.isBlank()) {
                        _practiceState.value = SpeechPracticeState.UnavailableOrError(
                            "Could not catch that sentence. Please try speaking again.",
                            "جملہ سمجھ میں نہیں آیا، براہ کرم دوبارہ بولنے کی کوشش کریں۔"
                        )
                    } else {
                        val accuracy = evaluateSimilarity(currentTargetSentence, bestTranscript)
                        val (fbEn, fbUr) = when {
                            accuracy >= 80 -> Pair(
                                "Excellent pronunciation! You matched the target sentence clearly.",
                                "بہترین تلفظ! آپ نے ہدف جملہ بالکل صاف ادا کیا۔"
                            )
                            accuracy >= 50 -> Pair(
                                "Good effort! Listen once more at slow speed and try the missed words.",
                                "اچھی کوشش! سست رفتار میں ایک بار پھر سنیں اور باقی الفاظ دہرائیں۔"
                            )
                            else -> Pair(
                                "Keep practicing! Listen to the sample audio and read each word step by step.",
                                "مشق جاری رکھیں! نمونہ آڈیو سنیں اور ہر لفظ آہستہ آہستہ ادا کریں۔"
                            )
                        }
                        _practiceState.value = SpeechPracticeState.Result(
                            spokenText = bestTranscript,
                            accuracyPercent = accuracy,
                            feedbackEn = fbEn,
                            feedbackUr = fbUr
                        )
                    }
                }

                override fun onPartialResults(partialResults: Bundle?) = Unit
                override fun onEvent(eventType: Int, params: Bundle?) = Unit
            })

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
                putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true)
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
            }
            _practiceState.value = SpeechPracticeState.Listening
            speechRecognizer?.startListening(intent)
        } catch (_: Throwable) {
            _practiceState.value = SpeechPracticeState.UnavailableOrError(
                "Offline speech recognition engine could not be started on this device.",
                "اس ڈیوائس پر آف لائن اسپیچ انجن شروع نہیں ہو سکا۔"
            )
        }
    }

    fun stopListening() {
        try {
            speechRecognizer?.stopListening()
        } catch (_: Throwable) {
        }
        if (_practiceState.value is SpeechPracticeState.Listening) {
            _practiceState.value = SpeechPracticeState.Idle
        }
    }

    fun resetPracticeState() {
        _practiceState.value = SpeechPracticeState.Idle
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
            speechRecognizer?.destroy()
        } catch (_: Throwable) {
        }
    }

    companion object {
        fun evaluateSimilarity(target: String, spoken: String): Int {
            val cleanRegex = Regex("[^a-z0-9\\s]")
            val targetWords = target.lowercase(Locale.ROOT).replace(cleanRegex, "").split(Regex("\\s+")).filter { it.isNotBlank() }
            val spokenWords = spoken.lowercase(Locale.ROOT).replace(cleanRegex, "").split(Regex("\\s+")).filter { it.isNotBlank() }
            if (targetWords.isEmpty()) return 0
            val spokenSet = spokenWords.toSet()
            val matched = targetWords.count { it in spokenSet }
            return ((matched * 100f) / targetWords.size).toInt().coerceIn(0, 100)
        }
    }
}
