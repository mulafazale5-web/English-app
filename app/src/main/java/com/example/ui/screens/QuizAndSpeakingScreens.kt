package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.R
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.local.ConversationEntity
import com.example.data.local.IncorrectAnswerEntity
import com.example.speech.OfflineRecognitionCapability
import com.example.speech.SpeechPracticeState
import com.example.speech.TtsStatus
import com.example.ui.ActiveQuizSession
import com.example.ui.components.SectionHeaderBox
import com.example.ui.components.UrduText

@Composable
fun QuizScreen(
    activeQuiz: ActiveQuizSession?,
    incorrectAnswers: List<IncorrectAnswerEntity>,
    currentPlanDay: Int,
    onStartQuizMode: (String, String) -> Unit,
    onSelectAnswer: (Int) -> Unit,
    onNextOrFinish: () -> Unit,
    onExitQuiz: () -> Unit,
    onDismissMistake: (String) -> Unit,
    onClearAllMistakes: () -> Unit,
    onBack: () -> Unit
) {
    BackHandler {
        if (activeQuiz != null) onExitQuiz() else onBack()
    }

    if (activeQuiz != null) {
        ActiveQuizContent(
            session = activeQuiz,
            onSelectAnswer = onSelectAnswer,
            onNextOrFinish = onNextOrFinish,
            onRepeatQuiz = { onStartQuizMode(activeQuiz.mode, activeQuiz.modeTitle) },
            onExitQuiz = onExitQuiz
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("quiz_hub_list"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                SectionHeaderBox(
                    titleEn = "Quiz & Tests",
                    titleUr = "کوئز اور امتحانی مشق",
                    subtitleEn = "Test your English offline, earn XP, and review any incorrect answers."
                )
            }

            item {
                QuizModeOptionCard(
                    titleEn = "Day $currentPlanDay Mini Quiz (6 Questions)",
                    titleUr = "آج کا مختصر کوئز",
                    desc = "Focused check on today's 10 words, phrases, and grammar rule.",
                    tag = "start_quiz_daily",
                    onClick = { onStartQuizMode("DAILY_MINI", "Day $currentPlanDay Mini Quiz") }
                )
            }
            item {
                QuizModeOptionCard(
                    titleEn = "Vocabulary Challenge (10 Questions)",
                    titleUr = "الفاظ کا کوئز",
                    desc = "Match English words with their Urdu script meanings.",
                    tag = "start_quiz_vocab",
                    onClick = { onStartQuizMode("VOCABULARY", "Vocabulary Challenge") }
                )
            }
            item {
                QuizModeOptionCard(
                    titleEn = "Phrases & Idioms Quiz (10 Questions)",
                    titleUr = "جملے اور محاورے کوئز",
                    desc = "Pick the most natural spoken English expression for each situation.",
                    tag = "start_quiz_phrases",
                    onClick = { onStartQuizMode("PHRASES", "Phrases & Idioms Quiz") }
                )
            }
            item {
                QuizModeOptionCard(
                    titleEn = "Grammar Mastery Test (10 Questions)",
                    titleUr = "گرامر ٹیسٹ",
                    desc = "Pronouns, tenses, articles, prepositions, and conditionals.",
                    tag = "start_quiz_grammar",
                    onClick = { onStartQuizMode("GRAMMAR", "Grammar Mastery Test") }
                )
            }
            item {
                QuizModeOptionCard(
                    titleEn = "Comprehensive Coach Test (15 Questions)",
                    titleUr = "مکمل جامع ٹیسٹ",
                    desc = "Mixed test combining vocabulary, phrases, idioms, and grammar.",
                    tag = "start_quiz_comprehensive",
                    onClick = { onStartQuizMode("COMPREHENSIVE", "Comprehensive Coach Test") }
                )
            }

            // Review Incorrect Answers Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Review Incorrect Answers (${incorrectAnswers.size})",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    if (incorrectAnswers.isNotEmpty()) {
                        IconButton(
                            onClick = onClearAllMistakes,
                            modifier = Modifier.testTag("clear_all_mistakes_btn")
                        ) {
                            Icon(Icons.Filled.DeleteSweep, contentDescription = "Clear all mistakes")
                        }
                    }
                }
            }

            if (incorrectAnswers.isEmpty()) {
                item {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "No incorrect answers waiting for review! Any question you miss in a quiz will automatically appear here with full English & Urdu explanations.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            } else {
                item {
                    Button(
                        onClick = { onStartQuizMode("REVIEW_MISTAKES", "Mistake Recovery Quiz") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("retest_mistakes_button")
                    ) {
                        Icon(Icons.Filled.Replay, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Re-Test My ${incorrectAnswers.size} Incorrect Answers")
                    }
                }

                items(incorrectAnswers, key = { it.questionKey }) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.35f)),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = item.questionPrompt,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(
                                    onClick = { onDismissMistake(item.questionKey) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(Icons.Filled.Close, contentDescription = "Remove from review")
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Your answer: ${item.selectedOption}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = "Correct answer: ${item.correctOption}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = item.explanationEn, style = MaterialTheme.typography.bodySmall)
                            UrduText(text = item.explanationUr, fontSizeSp = 15f)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizModeOptionCard(
    titleEn: String,
    titleUr: String,
    desc: String,
    tag: String,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .testTag(tag),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = titleEn,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(0.7f)) {
                    UrduText(text = titleUr, fontSizeSp = 15f, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ActiveQuizContent(
    session: ActiveQuizSession,
    onSelectAnswer: (Int) -> Unit,
    onNextOrFinish: () -> Unit,
    onRepeatQuiz: () -> Unit,
    onExitQuiz: () -> Unit
) {
    if (session.isFinished) {
        val missedList = session.questions.mapIndexedNotNull { idx, q ->
            val sel = session.selectedAnswers[idx]
            if (sel != q.correctIndex) Pair(q, sel) else null
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("quiz_result_view"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.EmojiEvents,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(52.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${session.finalScore} / ${session.questions.size}",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "${session.modeTitle} Completed! (+${session.finalScore * 15} XP)",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        UrduText(
                            text = if (missedList.isEmpty()) "ماشاءاللہ! آپ کے تمام جوابات درست ہیں!" else "شاباش! نیچے اپنی غلطیوں کا جائزہ لیں۔",
                            fontSizeSp = 16f,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            if (missedList.isNotEmpty()) {
                item {
                    Text(
                        text = "Review Incorrect Answers (${missedList.size})",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                items(missedList) { (q, chosenIdx) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(text = q.promptEn, style = MaterialTheme.typography.titleMedium)
                            if (!q.promptUr.isNullOrBlank()) {
                                UrduText(text = q.promptUr, fontSizeSp = 16f)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "You chose: ${chosenIdx?.let { q.options.getOrNull(it) } ?: "None"}",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Correct: ${q.options[q.correctIndex]}",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = q.explanationEn, style = MaterialTheme.typography.bodySmall)
                            UrduText(text = q.explanationUr, fontSizeSp = 15f)
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onRepeatQuiz,
                        modifier = Modifier
                            .weight(1f)
                            .minimumInteractiveComponentSize()
                            .testTag("repeat_quiz_button")
                    ) {
                        Text("Repeat Quiz")
                    }
                    Button(
                        onClick = onExitQuiz,
                        modifier = Modifier
                            .weight(1f)
                            .minimumInteractiveComponentSize()
                            .testTag("done_quiz_button")
                    ) {
                        Text("Back to Quizzes")
                    }
                }
            }
        }
        return
    }

    val question = session.questions[session.currentIndex]
    val selectedOption = session.selectedAnswers[session.currentIndex]
    val hasAnswered = selectedOption != null

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("active_quiz_question_view"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${session.modeTitle} • Q ${session.currentIndex + 1}/${session.questions.size}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                OutlinedButton(onClick = onExitQuiz) {
                    Text("Exit")
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { (session.currentIndex + 1).toFloat() / session.questions.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape)
            )
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = question.category,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = question.promptEn,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    if (!question.promptUr.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        UrduText(
                            text = question.promptUr,
                            fontSizeSp = 19f,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        items(question.options.indices.toList()) { idx ->
            val optText = question.options[idx]
            val isSelected = selectedOption == idx
            val isCorrect = question.correctIndex == idx

            val borderColor = when {
                !hasAnswered -> MaterialTheme.colorScheme.outline
                isCorrect -> MaterialTheme.colorScheme.primary
                isSelected -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
            }
            val bgColor = when {
                !hasAnswered -> MaterialTheme.colorScheme.surface
                isCorrect -> MaterialTheme.colorScheme.primaryContainer
                isSelected -> MaterialTheme.colorScheme.errorContainer
                else -> MaterialTheme.colorScheme.surface
            }

            OutlinedCard(
                onClick = { onSelectAnswer(idx) },
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .testTag("quiz_option_$idx"),
                colors = CardDefaults.outlinedCardColors(containerColor = bgColor),
                border = BorderStroke(if (hasAnswered && (isCorrect || isSelected)) 2.dp else 1.dp, borderColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val containsUrdu = optText.any { it.code in 0x0600..0x06FF }
                    if (containsUrdu) {
                        Column(modifier = Modifier.weight(1f)) {
                            UrduText(text = optText, fontSizeSp = 18f, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Text(
                            text = "${('A' + idx)}.  $optText",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (hasAnswered && isCorrect) {
                        Icon(Icons.Filled.CheckCircle, contentDescription = "Correct", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }

        if (hasAnswered) {
            item {
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = if (selectedOption == question.correctIndex) "Correct!" else "Not quite — here is why:",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = question.explanationEn,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        UrduText(
                            text = question.explanationUr,
                            fontSizeSp = 15f,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onNextOrFinish,
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize()
                        .testTag("quiz_next_button")
                ) {
                    Text(
                        if (session.currentIndex + 1 < session.questions.size) "Next Question"
                        else "Finish & Save Score"
                    )
                }
            }
        }
    }
}

@Composable
fun SpeakingPracticeScreen(
    conversations: List<ConversationEntity>,
    currentPlanDay: Int,
    dailySpeakingDone: Boolean,
    ttsStatus: TtsStatus,
    offlineCapability: OfflineRecognitionCapability,
    practiceState: SpeechPracticeState,
    onSpeakNormal: (String) -> Unit,
    onSpeakSlow: (String) -> Unit,
    onPlayFullDialogue: (List<String>) -> Unit,
    onStartOfflineMic: (String) -> Unit,
    onStopOfflineMic: () -> Unit,
    onMarkDailySpeakingComplete: () -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    val context = LocalContext.current
    var activeTopic by remember { mutableStateOf(CurriculumCatalog.speakingTopics.first()) }
    var selectedConvIndex by remember(activeTopic) { mutableStateOf(0) }
    var permissionDeniedNote by remember { mutableStateOf(false) }

    val topicDialogues = remember(conversations, activeTopic) {
        conversations.filter { it.topic == activeTopic }.ifEmpty { conversations }
    }
    val currentDialogue = topicDialogues.getOrElse(selectedConvIndex) { topicDialogues.firstOrNull() }
    val targetSentence = currentDialogue?.speakingPromptEn ?: "Hello! I am practicing spoken English every day."

    val micPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            permissionDeniedNote = false
            onStartOfflineMic(targetSentence)
        } else {
            permissionDeniedNote = true
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("speaking_practice_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(shape = MaterialTheme.shapes.large) {
                Image(
                    painter = painterResource(id = R.drawable.img_speaking_banner),
                    contentDescription = stringResource(R.string.cd_speaking_banner),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(155.dp)
                )
            }
        }

        item {
            SectionHeaderBox(
                titleEn = "Speaking Practice",
                titleUr = "انگریزی بولنے کی مشق",
                subtitleEn = "Listen with Android Text-to-Speech and practice speaking across 7 real-life topics."
            )
        }

        // Honest Offline Engine Status Card
        item {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("offline_speech_status_banner")
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        val ttsLabel = when (ttsStatus) {
                            TtsStatus.AVAILABLE -> "Android Text-to-Speech: Ready Offline ✓"
                            TtsStatus.INITIALIZING -> "Android Text-to-Speech: Initializing…"
                            TtsStatus.UNAVAILABLE -> "Android Text-to-Speech: No English voice pack found on device"
                        }
                        val sttLabel = when (offlineCapability) {
                            OfflineRecognitionCapability.AVAILABLE_ON_DEVICE ->
                                "Offline Speech Recognition: Supported (On-device mode only)"
                            else ->
                                "Offline Speech Recognition: Not installed on this device (Listen & Read-Aloud mode active — we never silently use online speech services)"
                        }
                        Text(
                            text = ttsLabel,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = sttLabel,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // 7 Conversation Topics Selector
        item {
            Text(
                text = "Choose a Conversation Topic (7 Topics)",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(CurriculumCatalog.speakingTopics) { topic ->
                    FilterChip(
                        selected = activeTopic == topic,
                        onClick = { activeTopic = topic },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("speak_topic_${topic.lowercase().replace(' ', '_')}"),
                        label = { Text(topic) }
                    )
                }
            }
        }

        // Sub-dialogue picker within topic
        if (topicDialogues.size > 1) {
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(topicDialogues.indices.toList()) { idx ->
                        FilterChip(
                            selected = selectedConvIndex == idx,
                            onClick = { selectedConvIndex = idx },
                            modifier = Modifier.minimumInteractiveComponentSize(),
                            label = { Text("Scenario ${idx + 1}: ${topicDialogues[idx].titleEn}") }
                        )
                    }
                }
            }
        }

        // Featured Speaking Prompt Exercise Card
        if (currentDialogue != null) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("speaking_exercise_card"),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Speaking Exercise • Day $currentPlanDay",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "\"${currentDialogue.speakingPromptEn}\"",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        UrduText(
                            text = currentDialogue.speakingPromptUr,
                            fontSizeSp = 17f,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            FilledTonalButton(
                                onClick = { onSpeakNormal(currentDialogue.speakingPromptEn) },
                                modifier = Modifier
                                    .weight(1f)
                                    .minimumInteractiveComponentSize()
                                    .testTag("btn_speak_normal")
                            ) {
                                Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Listen")
                            }
                            OutlinedButton(
                                onClick = { onSpeakSlow(currentDialogue.speakingPromptEn) },
                                modifier = Modifier
                                    .weight(1f)
                                    .minimumInteractiveComponentSize()
                                    .testTag("btn_speak_slow")
                            ) {
                                Icon(Icons.Filled.SlowMotionVideo, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Slow 0.7x")
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val isListening = practiceState is SpeechPracticeState.Listening
                        Button(
                            onClick = {
                                if (isListening) {
                                    onStopOfflineMic()
                                } else {
                                    val hasPerm = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.RECORD_AUDIO
                                    ) == PackageManager.PERMISSION_GRANTED
                                    if (hasPerm) {
                                        onStartOfflineMic(currentDialogue.speakingPromptEn)
                                    } else {
                                        micPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .testTag("btn_offline_mic_check")
                        ) {
                            Icon(
                                imageVector = if (isListening) Icons.Filled.Stop else Icons.Filled.Mic,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                if (isListening) "Listening Offline… Tap to Stop"
                                else "Check My Pronunciation (Offline Mic)"
                            )
                        }

                        if (permissionDeniedNote) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Microphone permission was declined. You can still listen to the audio above and practice reading aloud!",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        when (practiceState) {
                            is SpeechPracticeState.Result -> {
                                Spacer(modifier = Modifier.height(10.dp))
                                Surface(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = MaterialTheme.shapes.medium,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Text(
                                            text = "Match Score: ${practiceState.accuracyPercent}%",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                        Text(
                                            text = "Heard: \"${practiceState.spokenText}\"",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = practiceState.feedbackEn,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                        UrduText(text = practiceState.feedbackUr, fontSizeSp = 15f)
                                    }
                                }
                            }
                            is SpeechPracticeState.UnavailableOrError -> {
                                Spacer(modifier = Modifier.height(10.dp))
                                Surface(
                                    color = MaterialTheme.colorScheme.tertiaryContainer,
                                    shape = MaterialTheme.shapes.medium,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Text(
                                            text = practiceState.messageEn,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        UrduText(
                                            text = practiceState.messageUr,
                                            fontSizeSp = 14f,
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }
                                }
                            }
                            else -> Unit
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedButton(
                            onClick = onMarkDailySpeakingComplete,
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .testTag("mark_speaking_done_button")
                        ) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                if (dailySpeakingDone) "Today's Speaking Exercise Completed ✓"
                                else "I Read This Aloud — Complete Exercise (+25 XP)"
                            )
                        }
                    }
                }
            }

            // Sample Dialogue Preview with Line-by-Line Audio
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sample Dialogue: ${currentDialogue.titleEn}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    FilledTonalButton(
                        onClick = {
                            onPlayFullDialogue(currentDialogue.parsedTurns().map { it.english })
                        }
                    ) {
                        Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Play All")
                    }
                }
            }

            items(currentDialogue.parsedTurns()) { turn ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = turn.speaker,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Row {
                                IconButton(onClick = { onSpeakSlow(turn.english) }) {
                                    Icon(Icons.Filled.SlowMotionVideo, contentDescription = "Listen slow", modifier = Modifier.size(20.dp))
                                }
                                IconButton(onClick = { onSpeakNormal(turn.english) }) {
                                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Listen normal", tint = MaterialTheme.colorScheme.primary)
                                }
                            }
                        }
                        Text(text = turn.english, style = MaterialTheme.typography.bodyLarge)
                        UrduText(text = turn.urdu, fontSizeSp = 15f, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}
