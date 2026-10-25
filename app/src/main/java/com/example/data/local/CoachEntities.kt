package com.example.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "vocab_words")
data class VocabWordEntity(
    @PrimaryKey val id: Int,
    val dayNumber: Int,
    val category: String,
    val english: String,
    val pronunciation: String,
    val partOfSpeech: String,
    val urdu: String,
    val explanation: String,
    val exampleEn: String,
    val exampleUr: String,
    val isLearned: Boolean = false,
    val isSaved: Boolean = false
)

@Entity(tableName = "phrases")
data class PhraseItemEntity(
    @PrimaryKey val id: Int,
    val dayNumber: Int,
    val category: String,
    val english: String,
    val urdu: String,
    val explanation: String,
    val exampleEn: String,
    val exampleUr: String,
    val isIdiom: Boolean = false,
    val isLearned: Boolean = false,
    val isSaved: Boolean = false
)

@Entity(tableName = "grammar_lessons")
data class GrammarLessonEntity(
    @PrimaryKey val id: Int,
    val dayNumber: Int,
    val titleEn: String,
    val titleUr: String,
    val formula: String,
    val explanationEn: String,
    val explanationUr: String,
    val examplesPacked: String, // Pipe-delimited "en::ur||en::ur||en::ur"
    val urduTip: String,
    val isCompleted: Boolean = false,
    val timesRepeated: Int = 0
) {
    fun parsedExamples(): List<BilingualSentence> {
        if (examplesPacked.isBlank()) return emptyList()
        return examplesPacked.split("||").mapNotNull { chunk ->
            val parts = chunk.split("::")
            if (parts.size >= 2) BilingualSentence(parts[0].trim(), parts[1].trim()) else null
        }
    }
}

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey val id: Int,
    val topic: String,
    val titleEn: String,
    val titleUr: String,
    val turnsPacked: String, // "Speaker::English::Urdu||Speaker::English::Urdu"
    val speakingPromptEn: String,
    val speakingPromptUr: String,
    val isCompleted: Boolean = false,
    val timesPracticed: Int = 0
) {
    fun parsedTurns(): List<DialogueTurn> {
        if (turnsPacked.isBlank()) return emptyList()
        return turnsPacked.split("||").mapNotNull { chunk ->
            val parts = chunk.split("::")
            if (parts.size >= 3) {
                DialogueTurn(
                    speaker = parts[0].trim(),
                    english = parts[1].trim(),
                    urdu = parts[2].trim()
                )
            } else null
        }
    }
}

@Entity(tableName = "quiz_results")
data class QuizResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val quizType: String,
    val score: Int,
    val totalQuestions: Int,
    val timestamp: Long,
    val dayNumber: Int
)

@Entity(
    tableName = "incorrect_answers",
    indices = [Index(value = ["questionKey"], unique = true)]
)
data class IncorrectAnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val questionKey: String,
    val questionPrompt: String,
    val selectedOption: String,
    val correctOption: String,
    val explanationEn: String,
    val explanationUr: String,
    val timestamp: Long
)

@Entity(tableName = "user_settings")
data class UserSettingsEntity(
    @PrimaryKey val id: Int = 1,
    val themeMode: String = "SYSTEM", // LIGHT, DARK, SYSTEM
    val textScaleMode: String = "LARGE", // NORMAL, LARGE, XLARGE
    val alwaysShowUrdu: Boolean = false,
    val speechRate: Float = 0.95f,
    val currentPlanDay: Int = 1,
    val currentStreak: Int = 1,
    val bestStreak: Int = 1,
    val lastActiveEpochDay: Long = 0L,
    val totalXp: Int = 0,
    val dailySpeakingDoneDay: Int = 0,
    val dailySentencesReadDay: Int = 0
) {
    fun fontScaleFactor(): Float = when (textScaleMode) {
        "NORMAL" -> 1.0f
        "XLARGE" -> 1.22f
        else -> 1.10f // LARGE default for beginner readability
    }
}

data class BilingualSentence(
    val english: String,
    val urdu: String
)

data class DialogueTurn(
    val speaker: String,
    val english: String,
    val urdu: String
)

data class QuizQuestion(
    val key: String,
    val category: String,
    val promptEn: String,
    val promptUr: String?,
    val options: List<String>,
    val correctIndex: Int,
    val explanationEn: String,
    val explanationUr: String
)
