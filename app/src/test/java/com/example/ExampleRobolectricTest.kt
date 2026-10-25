package com.example

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.curriculum.QuizBankGenerator
import com.example.data.local.CoachDatabase
import com.example.data.repository.CoachRepository
import com.example.speech.OfflineSpeechController
import com.example.ui.CoachViewModel
import com.example.ui.EnglishCoachApp
import com.example.ui.theme.EnglishCoachTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36], qualifiers = "w412dp-h915dp-xxhdpi")
class ExampleRobolectricTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context
    private lateinit var db: CoachDatabase
    private lateinit var speechController: OfflineSpeechController

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, CoachDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        speechController = OfflineSpeechController(context)
    }

    @After
    fun tearDown() {
        speechController.shutdown()
        db.close()
    }

    @Test
    fun appName_matchesEnglishDailyCoach() {
        assertEquals("English Daily Coach", context.getString(R.string.app_name))
    }

    @Test
    fun repository_persistsProgressAcrossReinitializationAndScoresQuizzes() = runBlocking {
        var simulatedDay = 100L
        val repo = CoachRepository(db.coachDao(), epochDayProvider = { simulatedDay })
        repo.initializeBundledContent()

        val initialWords = repo.allWords.first()
        assertEquals(110, initialWords.size)

        // Mark word learned & saved
        val firstWord = initialWords.first()
        repo.toggleWordLearned(firstWord)
        repo.toggleWordSaved(firstWord)

        // Simulate closing & reopening app on next consecutive day
        simulatedDay = 101L
        repo.initializeBundledContent()

        val reloadedWord = repo.allWords.first().first { it.id == firstWord.id }
        assertTrue("Learned status must survive app restart", reloadedWord.isLearned)
        assertTrue("Saved status must survive app restart", reloadedWord.isSaved)

        val settings = repo.userSettings.first()
        assertEquals("Streak should increment on consecutive day", 2, settings.currentStreak)
        assertTrue("XP should be awarded for learning word", settings.totalXp >= 10)

        // Submit a 6-question Daily Mini Quiz with 5 right and 1 wrong
        val questions = QuizBankGenerator.buildDailyMiniQuiz(1)
        val answers = questions.mapIndexed { idx, q ->
            if (idx == 0) idx to ((q.correctIndex + 1) % 4) else idx to q.correctIndex
        }.toMap()

        val score = repo.recordQuizSubmission("DAILY_MINI", 1, questions, answers)
        assertEquals(5, score)
        assertEquals(1, repo.quizResults.first().size)
        assertEquals(1, repo.incorrectAnswers.first().size)

        // Answer that missed question right on review -> should clear from incorrect_answers
        val fixAnswers = questions.mapIndexed { idx, q -> idx to q.correctIndex }.toMap()
        val perfectScore = repo.recordQuizSubmission("DAILY_MINI", 1, questions, fixAnswers)
        assertEquals(6, perfectScore)
        assertTrue(repo.incorrectAnswers.first().isEmpty())
        assertFalse(repo.savedWords.first().isEmpty())
    }

    @Test
    fun composeScreens_renderUrduAndNavigateAcrossAllModules() {
        val repo = CoachRepository(db.coachDao())
        runBlocking { repo.initializeBundledContent() }
        val vm = CoachViewModel(repo, speechController)

        composeTestRule.setContent {
            EnglishCoachTheme {
                EnglishCoachApp(viewModel = vm)
            }
        }
        composeTestRule.waitForIdle()

        // 1. Home Dashboard renders Daily Plan
        composeTestRule.onNodeWithTag("daily_plan_card").assertIsDisplayed()

        // 2. Navigate to Daily Vocabulary & reveal Urdu translation
        composeTestRule.onNodeWithTag("nav_vocabulary").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("vocabulary_screen_list")
            .performScrollToNode(hasTestTag("vocab_card_1"))
        composeTestRule.onNodeWithTag("reveal_word_urdu_1").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("خوش آمدید", useUnmergedTree = true).assertIsDisplayed()

        // 3. Return Home & open Phrases, Conversations, and Grammar modules
        composeTestRule.onNodeWithTag("nav_home").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("home_dashboard_list")
            .performScrollToNode(hasTestTag("module_phrases"))
        composeTestRule.onNodeWithTag("module_phrases").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("phrases_screen_list").assertIsDisplayed()

        composeTestRule.onNodeWithTag("top_back_button").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("home_dashboard_list")
            .performScrollToNode(hasTestTag("module_conversations"))
        composeTestRule.onNodeWithTag("module_conversations").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("conversations_screen_list").assertIsDisplayed()

        composeTestRule.onNodeWithTag("top_back_button").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("home_dashboard_list")
            .performScrollToNode(hasTestTag("module_grammar"))
        composeTestRule.onNodeWithTag("module_grammar").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("grammar_screen_list").assertIsDisplayed()

        // 4. Navigate to Speaking Practice & verify honest offline status banner
        composeTestRule.onNodeWithTag("nav_speaking").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("offline_speech_status_banner").assertIsDisplayed()

        // 5. Navigate to Quiz Hub
        composeTestRule.onNodeWithTag("nav_quiz").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("start_quiz_daily").assertIsDisplayed()

        // 6. Navigate to My Progress
        composeTestRule.onNodeWithTag("nav_progress").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("progress_streak_card").assertIsDisplayed()

        // 7. Open Offline Search via Top Bar & search Urdu word
        composeTestRule.onNodeWithTag("top_search_button").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("global_offline_search_input").performTextInput("مہمان")
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Guest", useUnmergedTree = true).assertIsDisplayed()

        // 8. Open Saved Words & Settings via Top Bar
        composeTestRule.onNodeWithTag("top_saved_button").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("saved_tab_words").assertIsDisplayed()

        composeTestRule.onNodeWithTag("top_settings_button").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("theme_option_DARK").assertIsDisplayed()
    }
}
