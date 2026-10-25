package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.curriculum.QuizBankGenerator
import com.example.data.local.BilingualSentence
import com.example.data.local.ConversationEntity
import com.example.data.local.GrammarLessonEntity
import com.example.data.local.IncorrectAnswerEntity
import com.example.data.local.PhraseItemEntity
import com.example.data.local.QuizQuestion
import com.example.data.local.QuizResultEntity
import com.example.data.local.UserSettingsEntity
import com.example.data.local.VocabWordEntity
import com.example.data.repository.CoachRepository
import com.example.speech.OfflineRecognitionCapability
import com.example.speech.OfflineSpeechController
import com.example.speech.SpeechPracticeState
import com.example.speech.TtsStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class CoachScreen(val routeId: String, val titleEn: String, val titleUr: String) {
    HOME("home", "English Daily Coach", "انگلش ڈیلی کوچ"),
    VOCABULARY("vocabulary", "Daily Vocabulary", "روزمرہ الفاظ"),
    PHRASES("phrases", "Phrases & Idioms", "جملے اور محاورے"),
    CONVERSATIONS("conversations", "Conversations", "روزمرہ مکالمے"),
    GRAMMAR("grammar", "Grammar Lessons", "گرامر کے اسباق"),
    QUIZ("quiz", "Quiz & Tests", "کوئز اور ٹیسٹ"),
    SPEAKING("speaking", "Speaking Practice", "بولنے کی مشق"),
    PROGRESS("progress", "My Progress", "میری پیش رفت"),
    SAVED("saved", "Saved Words", "محفوظ الفاظ و جملے"),
    SETTINGS("settings", "Settings", "ترتیبات"),
    SEARCH("search", "Search Offline", "آف لائن تلاش")
}

data class DailyPlanBundle(
    val dayNumber: Int,
    val words: List<VocabWordEntity>,
    val phrases: List<PhraseItemEntity>,
    val fiveExampleSentences: List<BilingualSentence>,
    val grammarLesson: GrammarLessonEntity?,
    val speakingConversation: ConversationEntity?,
    val miniQuizCompletedToday: Boolean,
    val speakingDoneToday: Boolean,
    val sentencesReviewedToday: Boolean
) {
    val wordsLearnedCount: Int get() = words.count { it.isLearned }
    val phrasesLearnedCount: Int get() = phrases.count { it.isLearned }
    val grammarDone: Boolean get() = grammarLesson?.isCompleted == true

    val completedStepsCount: Int
        get() {
            var steps = 0
            if (words.isNotEmpty() && wordsLearnedCount >= words.size) steps++
            if (phrases.isNotEmpty() && phrasesLearnedCount >= phrases.size) steps++
            if (sentencesReviewedToday) steps++
            if (grammarDone) steps++
            if (miniQuizCompletedToday) steps++
            if (speakingDoneToday) steps++
            return steps
        }
}

data class ActiveQuizSession(
    val mode: String,
    val modeTitle: String,
    val questions: List<QuizQuestion>,
    val currentIndex: Int = 0,
    val selectedAnswers: Map<Int, Int> = emptyMap(),
    val isFinished: Boolean = false,
    val finalScore: Int = 0
)

data class CoachUiState(
    val isInitialized: Boolean = false,
    val words: List<VocabWordEntity> = emptyList(),
    val savedWords: List<VocabWordEntity> = emptyList(),
    val phrases: List<PhraseItemEntity> = emptyList(),
    val savedPhrases: List<PhraseItemEntity> = emptyList(),
    val grammarLessons: List<GrammarLessonEntity> = emptyList(),
    val conversations: List<ConversationEntity> = emptyList(),
    val quizResults: List<QuizResultEntity> = emptyList(),
    val incorrectAnswers: List<IncorrectAnswerEntity> = emptyList(),
    val settings: UserSettingsEntity = UserSettingsEntity()
) {
    val dailyPlan: DailyPlanBundle
        get() {
            val day = settings.currentPlanDay.coerceIn(1, 10)
            val dayWords = words.filter { it.dayNumber == day }.take(10)
            val dayPhrases = phrases.filter { it.dayNumber == day }.take(10)
            val sentences = (dayWords.take(3).map { BilingualSentence(it.exampleEn, it.exampleUr) } +
                dayPhrases.take(2).map { BilingualSentence(it.exampleEn, it.exampleUr) })
            val gram = grammarLessons.firstOrNull { it.id == day } ?: grammarLessons.firstOrNull()
            val conv = conversations.getOrNull((day - 1) % conversations.size.coerceAtLeast(1))
            val miniDone = quizResults.any { it.quizType == "DAILY_MINI" && it.dayNumber == day }
            return DailyPlanBundle(
                dayNumber = day,
                words = dayWords,
                phrases = dayPhrases,
                fiveExampleSentences = sentences,
                grammarLesson = gram,
                speakingConversation = conv,
                miniQuizCompletedToday = miniDone,
                speakingDoneToday = settings.dailySpeakingDoneDay == day,
                sentencesReviewedToday = settings.dailySentencesReadDay == day
            )
        }
}

class CoachViewModel(
    private val repository: CoachRepository,
    private val speechController: OfflineSpeechController
) : ViewModel() {

    private val _screenStack = MutableStateFlow(listOf(CoachScreen.HOME))
    val screenStack: StateFlow<List<CoachScreen>> = _screenStack.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _vocabFilter = MutableStateFlow("TODAY") // TODAY, ALL, UNLEARNED, LEARNED
    val vocabFilter: StateFlow<String> = _vocabFilter.asStateFlow()

    private val _phraseFilter = MutableStateFlow("TODAY") // TODAY, ALL, IDIOMS, LEARNED
    val phraseFilter: StateFlow<String> = _phraseFilter.asStateFlow()

    private val _selectedTopic = MutableStateFlow("All Topics")
    val selectedTopic: StateFlow<String> = _selectedTopic.asStateFlow()

    private val _activeQuiz = MutableStateFlow<ActiveQuizSession?>(null)
    val activeQuiz: StateFlow<ActiveQuizSession?> = _activeQuiz.asStateFlow()

    val ttsStatus: StateFlow<TtsStatus> = speechController.ttsStatus
    val isSpeaking: StateFlow<Boolean> = speechController.isSpeaking
    val offlineSpeechCapability: StateFlow<OfflineRecognitionCapability> = speechController.offlineCapability
    val speechPracticeState: StateFlow<SpeechPracticeState> = speechController.practiceState

    val uiState: StateFlow<CoachUiState> = combine(
        combine(
            repository.allWords,
            repository.savedWords,
            repository.allPhrases,
            repository.savedPhrases
        ) { words, savedW, phrases, savedP ->
            Tuple4(words, savedW, phrases, savedP)
        },
        combine(
            repository.allGrammar,
            repository.allConversations,
            repository.quizResults,
            repository.incorrectAnswers,
            repository.userSettings
        ) { grammar, convs, quizzes, mistakes, settings ->
            Tuple5(grammar, convs, quizzes, mistakes, settings)
        }
    ) { part1, part2 ->
        CoachUiState(
            isInitialized = part1.a.isNotEmpty(),
            words = part1.a,
            savedWords = part1.b,
            phrases = part1.c,
            savedPhrases = part1.d,
            grammarLessons = part2.a,
            conversations = part2.b,
            quizResults = part2.c,
            incorrectAnswers = part2.d,
            settings = part2.e
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CoachUiState()
    )

    init {
        viewModelScope.launch {
            repository.initializeBundledContent()
        }
    }

    fun navigateTo(screen: CoachScreen, filterHint: String? = null) {
        if (screen == CoachScreen.VOCABULARY && filterHint != null) {
            _vocabFilter.value = filterHint
        }
        if (screen == CoachScreen.PHRASES && filterHint != null) {
            _phraseFilter.value = filterHint
        }
        if (screen == CoachScreen.HOME) {
            _screenStack.value = listOf(CoachScreen.HOME)
        } else {
            val current = _screenStack.value
            if (current.lastOrNull() != screen) {
                _screenStack.value = (current.filter { it != screen } + screen)
            }
        }
        speechController.stopSpeaking()
        speechController.resetPracticeState()
    }

    fun navigateBack(): Boolean {
        speechController.stopSpeaking()
        speechController.stopListening()
        val current = _screenStack.value
        return if (current.size > 1) {
            _screenStack.value = current.dropLast(1)
            true
        } else {
            false
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setVocabFilter(filter: String) {
        _vocabFilter.value = filter
    }

    fun setPhraseFilter(filter: String) {
        _phraseFilter.value = filter
    }

    fun setSelectedTopic(topic: String) {
        _selectedTopic.value = topic
    }

    // Audio & Speech Actions
    fun speakEnglish(text: String, slow: Boolean = false) {
        val baseRate = uiState.value.settings.speechRate
        val rate = if (slow) 0.72f else baseRate
        speechController.speak(text, rate)
    }

    fun speakDialogue(lines: List<String>) {
        speechController.speakSequence(lines, uiState.value.settings.speechRate)
    }

    fun stopAudio() {
        speechController.stopSpeaking()
    }

    fun startOfflineMicPractice(targetSentence: String) {
        speechController.stopSpeaking()
        speechController.startOfflineListening(targetSentence)
    }

    fun stopOfflineMicPractice() {
        speechController.stopListening()
    }

    fun resetSpeechPractice() {
        speechController.resetPracticeState()
    }

    // Repository Progress Mutations
    fun toggleWordLearned(word: VocabWordEntity) {
        viewModelScope.launch { repository.toggleWordLearned(word) }
    }

    fun toggleWordSaved(word: VocabWordEntity) {
        viewModelScope.launch { repository.toggleWordSaved(word) }
    }

    fun togglePhraseLearned(phrase: PhraseItemEntity) {
        viewModelScope.launch { repository.togglePhraseLearned(phrase) }
    }

    fun togglePhraseSaved(phrase: PhraseItemEntity) {
        viewModelScope.launch { repository.togglePhraseSaved(phrase) }
    }

    fun completeGrammar(lesson: GrammarLessonEntity) {
        viewModelScope.launch { repository.completeOrRepeatGrammar(lesson) }
    }

    fun completeConversation(conversation: ConversationEntity) {
        viewModelScope.launch { repository.completeOrRepeatConversation(conversation) }
    }

    fun markDailySentencesReviewed() {
        val day = uiState.value.settings.currentPlanDay
        viewModelScope.launch { repository.markDailySentencesReviewed(day) }
    }

    fun markDailySpeakingDone() {
        val day = uiState.value.settings.currentPlanDay
        viewModelScope.launch { repository.markDailySpeakingCompleted(day) }
    }

    // Quiz Lifecycle
    fun startQuiz(mode: String, title: String) {
        val state = uiState.value
        val questions = QuizBankGenerator.buildQuizByMode(
            mode = mode,
            dayNumber = state.settings.currentPlanDay,
            incorrectItems = state.incorrectAnswers
        )
        if (questions.isNotEmpty()) {
            _activeQuiz.value = ActiveQuizSession(
                mode = mode,
                modeTitle = title,
                questions = questions
            )
            if (_screenStack.value.lastOrNull() != CoachScreen.QUIZ) {
                navigateTo(CoachScreen.QUIZ)
            }
        }
    }

    fun selectQuizAnswer(optionIndex: Int) {
        val session = _activeQuiz.value ?: return
        if (session.isFinished) return
        if (session.selectedAnswers.containsKey(session.currentIndex)) return // lock once answered for instant feedback
        val updated = session.selectedAnswers + (session.currentIndex to optionIndex)
        _activeQuiz.value = session.copy(selectedAnswers = updated)
    }

    fun advanceOrFinishQuiz() {
        val session = _activeQuiz.value ?: return
        if (session.currentIndex + 1 < session.questions.size) {
            _activeQuiz.value = session.copy(currentIndex = session.currentIndex + 1)
        } else {
            viewModelScope.launch {
                val score = repository.recordQuizSubmission(
                    quizType = session.mode,
                    dayNumber = uiState.value.settings.currentPlanDay,
                    questions = session.questions,
                    selectedIndices = session.selectedAnswers
                )
                _activeQuiz.value = session.copy(isFinished = true, finalScore = score)
            }
        }
    }

    fun exitActiveQuiz() {
        _activeQuiz.value = null
    }

    fun dismissMistake(questionKey: String) {
        viewModelScope.launch { repository.dismissIncorrectAnswer(questionKey) }
    }

    fun clearAllMistakes() {
        viewModelScope.launch { repository.clearAllMistakes() }
    }

    // Settings
    fun setThemeMode(mode: String) {
        viewModelScope.launch { repository.updateThemeMode(mode) }
    }

    fun setTextScaleMode(scaleMode: String) {
        viewModelScope.launch { repository.updateTextScaleMode(scaleMode) }
    }

    fun setAlwaysShowUrdu(alwaysShow: Boolean) {
        viewModelScope.launch { repository.updateAlwaysShowUrdu(alwaysShow) }
    }

    fun setSpeechRate(rate: Float) {
        viewModelScope.launch { repository.updateSpeechRate(rate) }
    }

    fun selectPlanDay(day: Int) {
        viewModelScope.launch { repository.selectPlanDay(day) }
    }

    fun resetAllProgress() {
        viewModelScope.launch { repository.resetAllLearningProgress() }
    }

    override fun onCleared() {
        super.onCleared()
        speechController.shutdown()
    }

    private data class Tuple4<A, B, C, D>(val a: A, val b: B, val c: C, val d: D)
    private data class Tuple5<A, B, C, D, E>(val a: A, val b: B, val c: C, val d: D, val e: E)

    companion object {
        fun provideFactory(
            repository: CoachRepository,
            speechController: OfflineSpeechController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CoachViewModel(repository, speechController) as T
            }
        }
    }
}
