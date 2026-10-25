package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        VocabWordEntity::class,
        PhraseItemEntity::class,
        GrammarLessonEntity::class,
        ConversationEntity::class,
        QuizResultEntity::class,
        IncorrectAnswerEntity::class,
        UserSettingsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CoachDatabase : RoomDatabase() {
    abstract fun coachDao(): CoachDao

    companion object {
        @Volatile
        private var INSTANCE: CoachDatabase? = null

        fun getInstance(context: Context): CoachDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoachDatabase::class.java,
                    "english_daily_coach.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
