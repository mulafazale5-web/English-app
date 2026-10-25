package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.R
import com.example.ui.screens.ConversationsScreen
import com.example.ui.screens.GrammarScreen
import com.example.ui.screens.HomeDashboardScreen
import com.example.ui.screens.OfflineSearchScreen
import com.example.ui.screens.PhrasesScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SavedWordsScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SpeakingPracticeScreen
import com.example.ui.screens.VocabularyScreen

private data class PrimaryNavItem(
    val screen: CoachScreen,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val tag: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnglishCoachApp(viewModel: CoachViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenStack by viewModel.screenStack.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val vocabFilter by viewModel.vocabFilter.collectAsStateWithLifecycle()
    val phraseFilter by viewModel.phraseFilter.collectAsStateWithLifecycle()
    val selectedTopic by viewModel.selectedTopic.collectAsStateWithLifecycle()
    val activeQuiz by viewModel.activeQuiz.collectAsStateWithLifecycle()
    val ttsStatus by viewModel.ttsStatus.collectAsStateWithLifecycle()
    val offlineCapability by viewModel.offlineSpeechCapability.collectAsStateWithLifecycle()
    val practiceState by viewModel.speechPracticeState.collectAsStateWithLifecycle()

    val currentScreen = screenStack.lastOrNull() ?: CoachScreen.HOME

    val primaryNavItems = listOf(
        PrimaryNavItem(CoachScreen.HOME, "Home", Icons.Filled.Home, Icons.Outlined.Home, "nav_home"),
        PrimaryNavItem(CoachScreen.VOCABULARY, "Words", Icons.AutoMirrored.Filled.MenuBook, Icons.Outlined.MenuBook, "nav_vocabulary"),
        PrimaryNavItem(CoachScreen.SPEAKING, "Speak", Icons.Filled.Mic, Icons.Outlined.Mic, "nav_speaking"),
        PrimaryNavItem(CoachScreen.QUIZ, "Quiz", Icons.Filled.Quiz, Icons.Outlined.Quiz, "nav_quiz"),
        PrimaryNavItem(CoachScreen.PROGRESS, "Progress", Icons.Filled.BarChart, Icons.Outlined.BarChart, "nav_progress")
    )

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useNavRail = maxWidth >= 600.dp

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = currentScreen.titleEn,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        if (currentScreen != CoachScreen.HOME) {
                            IconButton(
                                onClick = { viewModel.navigateBack() },
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("top_back_button")
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.cd_back)
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { viewModel.navigateTo(CoachScreen.SEARCH) },
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("top_search_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(R.string.cd_open_search)
                            )
                        }
                        IconButton(
                            onClick = { viewModel.navigateTo(CoachScreen.SAVED) },
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("top_saved_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Bookmark,
                                contentDescription = stringResource(R.string.cd_open_saved)
                            )
                        }
                        IconButton(
                            onClick = { viewModel.navigateTo(CoachScreen.SETTINGS) },
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("top_settings_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = stringResource(R.string.cd_open_settings)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            bottomBar = {
                if (!useNavRail) {
                    NavigationBar {
                        primaryNavItems.forEach { item ->
                            val selected = currentScreen == item.screen
                            NavigationBarItem(
                                selected = selected,
                                onClick = { viewModel.navigateTo(item.screen) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.label
                                    )
                                },
                                label = { Text(item.label) },
                                modifier = Modifier.testTag(item.tag)
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (useNavRail) {
                    NavigationRail(modifier = Modifier.fillMaxHeight()) {
                        primaryNavItems.forEach { item ->
                            val selected = currentScreen == item.screen
                            NavigationRailItem(
                                selected = selected,
                                onClick = { viewModel.navigateTo(item.screen) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.label
                                    )
                                },
                                label = { Text(item.label) },
                                modifier = Modifier.testTag(item.tag)
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Box(modifier = Modifier.widthIn(max = 860.dp)) {
                        when (currentScreen) {
                            CoachScreen.HOME -> HomeDashboardScreen(
                                uiState = uiState,
                                onNavigate = { screen, hint -> viewModel.navigateTo(screen, hint) },
                                onSelectPlanDay = { viewModel.selectPlanDay(it) },
                                onStartDailyQuiz = {
                                    viewModel.startQuiz(
                                        "DAILY_MINI",
                                        "Day ${uiState.settings.currentPlanDay} Mini Quiz"
                                    )
                                },
                                onSpeakText = { viewModel.speakEnglish(it) },
                                onMarkDailySentencesDone = { viewModel.markDailySentencesReviewed() }
                            )

                            CoachScreen.VOCABULARY -> VocabularyScreen(
                                words = uiState.words,
                                currentPlanDay = uiState.settings.currentPlanDay,
                                activeFilter = vocabFilter,
                                alwaysShowUrdu = uiState.settings.alwaysShowUrdu,
                                onFilterChange = { viewModel.setVocabFilter(it) },
                                onSpeakText = { viewModel.speakEnglish(it) },
                                onToggleLearned = { viewModel.toggleWordLearned(it) },
                                onToggleSaved = { viewModel.toggleWordSaved(it) },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.PHRASES -> PhrasesScreen(
                                phrases = uiState.phrases,
                                currentPlanDay = uiState.settings.currentPlanDay,
                                activeFilter = phraseFilter,
                                alwaysShowUrdu = uiState.settings.alwaysShowUrdu,
                                onFilterChange = { viewModel.setPhraseFilter(it) },
                                onSpeakText = { viewModel.speakEnglish(it) },
                                onToggleLearned = { viewModel.togglePhraseLearned(it) },
                                onToggleSaved = { viewModel.togglePhraseSaved(it) },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.CONVERSATIONS -> ConversationsScreen(
                                conversations = uiState.conversations,
                                selectedTopic = selectedTopic,
                                alwaysShowUrdu = uiState.settings.alwaysShowUrdu,
                                onSelectTopic = { viewModel.setSelectedTopic(it) },
                                onSpeakLine = { viewModel.speakEnglish(it) },
                                onPlayFullDialogue = { viewModel.speakDialogue(it) },
                                onCompleteOrRepeat = { viewModel.completeConversation(it) },
                                onPracticeSpeakingTopic = { viewModel.navigateTo(CoachScreen.SPEAKING) },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.GRAMMAR -> GrammarScreen(
                                lessons = uiState.grammarLessons,
                                currentPlanDay = uiState.settings.currentPlanDay,
                                onSpeakExample = { viewModel.speakEnglish(it) },
                                onCompleteOrRepeatLesson = { viewModel.completeGrammar(it) },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.QUIZ -> QuizScreen(
                                activeQuiz = activeQuiz,
                                incorrectAnswers = uiState.incorrectAnswers,
                                currentPlanDay = uiState.settings.currentPlanDay,
                                onStartQuizMode = { mode, title -> viewModel.startQuiz(mode, title) },
                                onSelectAnswer = { viewModel.selectQuizAnswer(it) },
                                onNextOrFinish = { viewModel.advanceOrFinishQuiz() },
                                onExitQuiz = { viewModel.exitActiveQuiz() },
                                onDismissMistake = { viewModel.dismissMistake(it) },
                                onClearAllMistakes = { viewModel.clearAllMistakes() },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.SPEAKING -> SpeakingPracticeScreen(
                                conversations = uiState.conversations,
                                currentPlanDay = uiState.settings.currentPlanDay,
                                dailySpeakingDone = uiState.dailyPlan.speakingDoneToday,
                                ttsStatus = ttsStatus,
                                offlineCapability = offlineCapability,
                                practiceState = practiceState,
                                onSpeakNormal = { viewModel.speakEnglish(it, slow = false) },
                                onSpeakSlow = { viewModel.speakEnglish(it, slow = true) },
                                onPlayFullDialogue = { viewModel.speakDialogue(it) },
                                onStartOfflineMic = { viewModel.startOfflineMicPractice(it) },
                                onStopOfflineMic = { viewModel.stopOfflineMicPractice() },
                                onMarkDailySpeakingComplete = { viewModel.markDailySpeakingDone() },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.PROGRESS -> ProgressScreen(
                                uiState = uiState,
                                onNavigate = { viewModel.navigateTo(it) },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.SAVED -> SavedWordsScreen(
                                savedWords = uiState.savedWords,
                                savedPhrases = uiState.savedPhrases,
                                alwaysShowUrdu = uiState.settings.alwaysShowUrdu,
                                onSpeakText = { viewModel.speakEnglish(it) },
                                onToggleWordLearned = { viewModel.toggleWordLearned(it) },
                                onToggleWordSaved = { viewModel.toggleWordSaved(it) },
                                onTogglePhraseLearned = { viewModel.togglePhraseLearned(it) },
                                onTogglePhraseSaved = { viewModel.togglePhraseSaved(it) },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.SETTINGS -> SettingsScreen(
                                settings = uiState.settings,
                                onUpdateTheme = { viewModel.setThemeMode(it) },
                                onUpdateTextScale = { viewModel.setTextScaleMode(it) },
                                onUpdateAlwaysShowUrdu = { viewModel.setAlwaysShowUrdu(it) },
                                onUpdateSpeechRate = { viewModel.setSpeechRate(it) },
                                onTestSpeech = {
                                    viewModel.speakEnglish("Welcome to English Daily Coach! Keep practicing every day.")
                                },
                                onResetProgress = { viewModel.resetAllProgress() },
                                onBack = { viewModel.navigateBack() }
                            )

                            CoachScreen.SEARCH -> OfflineSearchScreen(
                                uiState = uiState,
                                query = searchQuery,
                                onQueryChange = { viewModel.updateSearchQuery(it) },
                                onSpeakText = { viewModel.speakEnglish(it) },
                                onToggleWordLearned = { viewModel.toggleWordLearned(it) },
                                onToggleWordSaved = { viewModel.toggleWordSaved(it) },
                                onTogglePhraseLearned = { viewModel.togglePhraseLearned(it) },
                                onTogglePhraseSaved = { viewModel.togglePhraseSaved(it) },
                                onBack = { viewModel.navigateBack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
