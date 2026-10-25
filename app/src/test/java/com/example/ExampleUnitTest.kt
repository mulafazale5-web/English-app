package com.example

import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.QuizBankGenerator
import com.example.speech.OfflineSpeechController
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    private fun containsUrduScript(text: String): Boolean {
        return text.any { it.code in 0x0600..0x06FF }
    }

    @Test
    fun curriculum_meetsAllVolumeAndQualityRequirements() {
        val words = CurriculumCatalog.allWords
        val phrases = CurriculumCatalog.allPhrases
        val grammar = CurriculumCatalog.allGrammar
        val conversations = CurriculumCatalog.allConversations

        // Volume checks (>=100 words, >=100 phrases, >=30 grammar, >=20 dialogues)
        assertTrue("Expected >= 100 words, got ${words.size}", words.size >= 100)
        assertTrue("Expected >= 100 phrases, got ${phrases.size}", phrases.size >= 100)
        assertTrue("Expected >= 30 grammar lessons, got ${grammar.size}", grammar.size >= 30)
        assertTrue("Expected >= 20 dialogues, got ${conversations.size}", conversations.size >= 20)

        // Uniqueness checks (no duplicate IDs or duplicate English entries)
        assertEquals(words.size, words.map { it.id }.distinct().size)
        assertEquals(words.size, words.map { it.english.lowercase() }.distinct().size)
        assertEquals(phrases.size, phrases.map { it.id }.distinct().size)
        assertEquals(phrases.size, phrases.map { it.english.lowercase() }.distinct().size)
        assertEquals(grammar.size, grammar.map { it.id }.distinct().size)
        assertEquals(conversations.size, conversations.map { it.id }.distinct().size)

        // Urdu script checks across all educational items
        assertTrue(words.all { containsUrduScript(it.urdu) && containsUrduScript(it.exampleUr) })
        assertTrue(phrases.all { containsUrduScript(it.urdu) && containsUrduScript(it.exampleUr) })
        assertTrue(grammar.all { containsUrduScript(it.titleUr) && containsUrduScript(it.explanationUr) && it.parsedExamples().size >= 3 })
        assertTrue(conversations.all { containsUrduScript(it.titleUr) && it.parsedTurns().size >= 6 })

        // Verify all 7 required conversation topics have dialogues
        val expectedTopics = listOf(
            "Introducing yourself",
            "Talking to friends",
            "School conversations",
            "Shopping",
            "Asking for directions",
            "Job interviews",
            "Programming and technology"
        )
        expectedTopics.forEach { topic ->
            assertTrue("Missing dialogues for topic: $topic", conversations.any { it.topic == topic })
        }
    }

    @Test
    fun quizGenerator_producesValidMultipleChoiceQuestions() {
        for (day in 1..10) {
            val dailyQuiz = QuizBankGenerator.buildDailyMiniQuiz(day)
            assertEquals(6, dailyQuiz.size)
            dailyQuiz.forEach { q ->
                assertEquals(4, q.options.size)
                assertTrue(q.correctIndex in q.options.indices)
            }
        }
    }

    @Test
    fun speechSimilarity_scoresPronunciationAccurately() {
        val target = "Hello! My name is Hassan, and I enjoy learning English."
        assertEquals(100, OfflineSpeechController.evaluateSimilarity(target, "hello my name is hassan and i enjoy learning english"))
        val partial = OfflineSpeechController.evaluateSimilarity(target, "hello my name is hassan")
        assertTrue(partial in 40..70)
    }
}
