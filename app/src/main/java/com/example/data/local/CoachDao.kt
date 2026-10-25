package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CoachDao {

    // Seeders (IGNORE preserves existing user progress across app restarts)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWordsIgnore(words: List<VocabWordEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhrasesIgnore(phrases: List<PhraseItemEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrammarIgnore(lessons: List<GrammarLessonEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertConversationsIgnore(conversations: List<ConversationEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSettingsIgnore(settings: UserSettingsEntity)

    // Reactive Streams
    @Query("SELECT * FROM vocab_words ORDER BY id ASC")
    fun observeAllWords(): Flow<List<VocabWordEntity>>

    @Query("SELECT * FROM vocab_words WHERE isSaved = 1 ORDER BY id ASC")
    fun observeSavedWords(): Flow<List<VocabWordEntity>>

    @Query("SELECT * FROM phrases ORDER BY id ASC")
    fun observeAllPhrases(): Flow<List<PhraseItemEntity>>

    @Query("SELECT * FROM phrases WHERE isSaved = 1 ORDER BY id ASC")
    fun observeSavedPhrases(): Flow<List<PhraseItemEntity>>

    @Query("SELECT * FROM grammar_lessons ORDER BY id ASC")
    fun observeAllGrammar(): Flow<List<GrammarLessonEntity>>

    @Query("SELECT * FROM conversations ORDER BY id ASC")
    fun observeAllConversations(): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM quiz_results ORDER BY timestamp DESC")
    fun observeQuizResults(): Flow<List<QuizResultEntity>>

    @Query("SELECT * FROM incorrect_answers ORDER BY timestamp DESC")
    fun observeIncorrectAnswers(): Flow<List<IncorrectAnswerEntity>>

    @Query("SELECT * FROM user_settings WHERE id = 1")
    fun observeUserSettings(): Flow<UserSettingsEntity?>

    // One-shot getters for seeding checks & logic
    @Query("SELECT COUNT(*) FROM vocab_words")
    suspend fun getWordCount(): Int

    @Query("SELECT * FROM user_settings WHERE id = 1")
    suspend fun getUserSettingsOnce(): UserSettingsEntity?

    // Interactive Progress Updates
    @Query("UPDATE vocab_words SET isLearned = :learned WHERE id = :wordId")
    suspend fun setWordLearned(wordId: Int, learned: Boolean)

    @Query("UPDATE vocab_words SET isSaved = :saved WHERE id = :wordId")
    suspend fun setWordSaved(wordId: Int, saved: Boolean)

    @Query("UPDATE phrases SET isLearned = :learned WHERE id = :phraseId")
    suspend fun setPhraseLearned(phraseId: Int, learned: Boolean)

    @Query("UPDATE phrases SET isSaved = :saved WHERE id = :phraseId")
    suspend fun setPhraseSaved(phraseId: Int, saved: Boolean)

    @Query("UPDATE grammar_lessons SET isCompleted = :completed, timesRepeated = timesRepeated + :incrementRepeat WHERE id = :lessonId")
    suspend fun updateGrammarProgress(lessonId: Int, completed: Boolean, incrementRepeat: Int)

    @Query("UPDATE conversations SET isCompleted = :completed, timesPracticed = timesPracticed + :incrementPractice WHERE id = :conversationId")
    suspend fun updateConversationProgress(conversationId: Int, completed: Boolean, incrementPractice: Int)

    // Quiz & Mistake Bank
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizResult(result: QuizResultEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertIncorrectAnswer(incorrect: IncorrectAnswerEntity)

    @Query("DELETE FROM incorrect_answers WHERE questionKey = :questionKey")
    suspend fun removeIncorrectAnswerByKey(questionKey: String)

    @Query("DELETE FROM incorrect_answers")
    suspend fun clearAllIncorrectAnswers()

    // Settings & Streak
    @Update
    suspend fun updateUserSettings(settings: UserSettingsEntity)

    // Full Progress Reset (only when triggered by user in Settings)
    @Query("UPDATE vocab_words SET isLearned = 0, isSaved = 0")
    suspend fun resetWordsProgress()

    @Query("UPDATE phrases SET isLearned = 0, isSaved = 0")
    suspend fun resetPhrasesProgress()

    @Query("UPDATE grammar_lessons SET isCompleted = 0, timesRepeated = 0")
    suspend fun resetGrammarProgress()

    @Query("UPDATE conversations SET isCompleted = 0, timesPracticed = 0")
    suspend fun resetConversationsProgress()

    @Query("DELETE FROM quiz_results")
    suspend fun clearQuizResults()
}
