package com.example.data.repository

import com.example.data.curriculum.CurriculumCatalog
import com.example.data.local.CoachDao
import com.example.data.local.ConversationEntity
import com.example.data.local.GrammarLessonEntity
import com.example.data.local.IncorrectAnswerEntity
import com.example.data.local.PhraseItemEntity
import com.example.data.local.QuizQuestion
import com.example.data.local.QuizResultEntity
import com.example.data.local.UserSettingsEntity
import com.example.data.local.VocabWordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoachRepository(
    private val dao: CoachDao,
    private val epochDayProvider: () -> Long = { System.currentTimeMillis() / 86_400_000L }
) {
    val allWords: Flow<List<VocabWordEntity>> = dao.observeAllWords()
    val savedWords: Flow<List<VocabWordEntity>> = dao.observeSavedWords()
    val allPhrases: Flow<List<PhraseItemEntity>> = dao.observeAllPhrases()
    val savedPhrases: Flow<List<PhraseItemEntity>> = dao.observeSavedPhrases()
    val allGrammar: Flow<List<GrammarLessonEntity>> = dao.observeAllGrammar()
    val allConversations: Flow<List<ConversationEntity>> = dao.observeAllConversations()
    val quizResults: Flow<List<QuizResultEntity>> = dao.observeQuizResults()
    val incorrectAnswers: Flow<List<IncorrectAnswerEntity>> = dao.observeIncorrectAnswers()
    val userSettings: Flow<UserSettingsEntity> = dao.observeUserSettings().map {
        it ?: UserSettingsEntity()
    }

    suspend fun initializeBundledContent() {
        dao.insertWordsIgnore(CurriculumCatalog.allWords)
        dao.insertPhrasesIgnore(CurriculumCatalog.allPhrases)
        dao.insertGrammarIgnore(CurriculumCatalog.allGrammar)
        dao.insertConversationsIgnore(CurriculumCatalog.allConversations)

        val todayEpoch = epochDayProvider()
        val existingSettings = dao.getUserSettingsOnce()
        if (existingSettings == null) {
            dao.insertSettingsIgnore(
                UserSettingsEntity(
                    id = 1,
                    lastActiveEpochDay = todayEpoch,
                    currentStreak = 1,
                    bestStreak = 1
                )
            )
        } else {
            refreshStreakOnLaunch(existingSettings, todayEpoch)
        }
    }

    private suspend fun refreshStreakOnLaunch(settings: UserSettingsEntity, todayEpoch: Long) {
        if (settings.lastActiveEpochDay == 0L) {
            dao.updateUserSettings(settings.copy(lastActiveEpochDay = todayEpoch, currentStreak = 1, bestStreak = maxOf(1, settings.bestStreak)))
            return
        }
        val diff = todayEpoch - settings.lastActiveEpochDay
        when {
            diff == 1L -> {
                val nextStreak = settings.currentStreak + 1
                dao.updateUserSettings(
                    settings.copy(
                        currentStreak = nextStreak,
                        bestStreak = maxOf(settings.bestStreak, nextStreak),
                        lastActiveEpochDay = todayEpoch
                    )
                )
            }
            diff > 1L -> {
                dao.updateUserSettings(
                    settings.copy(
                        currentStreak = 1,
                        lastActiveEpochDay = todayEpoch
                    )
                )
            }
        }
    }

    private suspend fun awardXp(points: Int) {
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity(lastActiveEpochDay = epochDayProvider())
        val today = epochDayProvider()
        val newXp = (current.totalXp + points).coerceAtLeast(0)
        dao.updateUserSettings(current.copy(totalXp = newXp, lastActiveEpochDay = today))
    }

    suspend fun toggleWordLearned(word: VocabWordEntity) {
        val next = !word.isLearned
        dao.setWordLearned(word.id, next)
        if (next) awardXp(10)
    }

    suspend fun toggleWordSaved(word: VocabWordEntity) {
        dao.setWordSaved(word.id, !word.isSaved)
    }

    suspend fun togglePhraseLearned(phrase: PhraseItemEntity) {
        val next = !phrase.isLearned
        dao.setPhraseLearned(phrase.id, next)
        if (next) awardXp(10)
    }

    suspend fun togglePhraseSaved(phrase: PhraseItemEntity) {
        dao.setPhraseSaved(phrase.id, !phrase.isSaved)
    }

    suspend fun completeOrRepeatGrammar(lesson: GrammarLessonEntity) {
        val repeatDelta = if (lesson.isCompleted) 1 else 0
        dao.updateGrammarProgress(lesson.id, completed = true, incrementRepeat = repeatDelta)
        awardXp(20)
    }

    suspend fun completeOrRepeatConversation(conversation: ConversationEntity) {
        val practiceDelta = 1
        dao.updateConversationProgress(conversation.id, completed = true, incrementPractice = practiceDelta)
        awardXp(20)
    }

    suspend fun markDailySentencesReviewed(dayNumber: Int) {
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity()
        if (current.dailySentencesReadDay != dayNumber) {
            dao.updateUserSettings(
                current.copy(
                    dailySentencesReadDay = dayNumber,
                    totalXp = current.totalXp + 15
                )
            )
        }
    }

    suspend fun markDailySpeakingCompleted(dayNumber: Int) {
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity()
        dao.updateUserSettings(
            current.copy(
                dailySpeakingDoneDay = dayNumber,
                totalXp = current.totalXp + 25
            )
        )
    }

    suspend fun recordQuizSubmission(
        quizType: String,
        dayNumber: Int,
        questions: List<QuizQuestion>,
        selectedIndices: Map<Int, Int>
    ): Int {
        var score = 0
        val now = System.currentTimeMillis()
        questions.forEachIndexed { idx, q ->
            val chosenIdx = selectedIndices[idx]
            if (chosenIdx != null && chosenIdx == q.correctIndex) {
                score++
                dao.removeIncorrectAnswerByKey(q.key)
            } else if (chosenIdx != null) {
                val chosenText = q.options.getOrElse(chosenIdx) { "Skipped" }
                val rightText = q.options.getOrElse(q.correctIndex) { "" }
                dao.upsertIncorrectAnswer(
                    IncorrectAnswerEntity(
                        questionKey = q.key,
                        questionPrompt = listOfNotNull(q.promptEn, q.promptUr).joinToString(" — "),
                        selectedOption = chosenText,
                        correctOption = rightText,
                        explanationEn = q.explanationEn,
                        explanationUr = q.explanationUr,
                        timestamp = now
                    )
                )
            }
        }
        dao.insertQuizResult(
            QuizResultEntity(
                quizType = quizType,
                score = score,
                totalQuestions = questions.size,
                timestamp = now,
                dayNumber = dayNumber
            )
        )
        awardXp(score * 15)
        return score
    }

    suspend fun dismissIncorrectAnswer(questionKey: String) {
        dao.removeIncorrectAnswerByKey(questionKey)
    }

    suspend fun clearAllMistakes() {
        dao.clearAllIncorrectAnswers()
    }

    suspend fun updateThemeMode(mode: String) {
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity()
        dao.updateUserSettings(current.copy(themeMode = mode))
    }

    suspend fun updateTextScaleMode(scaleMode: String) {
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity()
        dao.updateUserSettings(current.copy(textScaleMode = scaleMode))
    }

    suspend fun updateAlwaysShowUrdu(alwaysShow: Boolean) {
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity()
        dao.updateUserSettings(current.copy(alwaysShowUrdu = alwaysShow))
    }

    suspend fun updateSpeechRate(rate: Float) {
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity()
        dao.updateUserSettings(current.copy(speechRate = rate))
    }

    suspend fun selectPlanDay(day: Int) {
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity()
        dao.updateUserSettings(current.copy(currentPlanDay = day.coerceIn(1, 10)))
    }

    suspend fun resetAllLearningProgress() {
        dao.resetWordsProgress()
        dao.resetPhrasesProgress()
        dao.resetGrammarProgress()
        dao.resetConversationsProgress()
        dao.clearQuizResults()
        dao.clearAllIncorrectAnswers()
        val current = dao.getUserSettingsOnce() ?: UserSettingsEntity()
        dao.updateUserSettings(
            current.copy(
                currentPlanDay = 1,
                currentStreak = 1,
                bestStreak = 1,
                totalXp = 0,
                dailySpeakingDoneDay = 0,
                dailySentencesReadDay = 0,
                lastActiveEpochDay = epochDayProvider()
            )
        )
    }
}
