package com.example.data.curriculum

import com.example.data.local.ConversationEntity
import com.example.data.local.GrammarLessonEntity
import com.example.data.local.IncorrectAnswerEntity
import com.example.data.local.PhraseItemEntity
import com.example.data.local.QuizQuestion
import com.example.data.local.VocabWordEntity
import kotlin.random.Random

object CurriculumCatalog {
    val allWords: List<VocabWordEntity> by lazy { VocabSeedPart1.words + VocabSeedPart2.words }
    val allPhrases: List<PhraseItemEntity> by lazy { PhrasesSeedPart1.phrases + PhrasesSeedPart2.phrases }
    val allGrammar: List<GrammarLessonEntity> by lazy { GrammarSeedPart1.lessons + GrammarSeedPart2.lessons }
    val allConversations: List<ConversationEntity> by lazy {
        ConversationsSeedPart1.conversations + ConversationsSeedPart2.conversations
    }

    val speakingTopics: List<String> = listOf(
        "Introducing yourself",
        "Talking to friends",
        "School conversations",
        "Shopping",
        "Asking for directions",
        "Job interviews",
        "Programming and technology"
    )
}

object QuizBankGenerator {

    private val curatedGrammarQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            key = "gram_1",
            category = "Grammar",
            promptEn = "Choose the correct sentence:",
            promptUr = "درست جملہ منتخب کریں:",
            options = listOf("i am a student.", "I am a student.", "I is a student.", "I are a student."),
            correctIndex = 1,
            explanationEn = "'I' is always capitalized and takes 'am' in the present tense.",
            explanationUr = "انگریزی میں 'I' ہمیشہ بڑا لکھا جاتا ہے اور اس کے ساتھ 'am' آتا ہے۔"
        ),
        QuizQuestion(
            key = "gram_2",
            category = "Grammar",
            promptEn = "Bilal ___ very polite to his elders.",
            promptUr = "بلال اپنے بڑوں کے ساتھ بہت شائستہ ___۔",
            options = listOf("am", "are", "is", "be"),
            correctIndex = 2,
            explanationEn = "Use 'is' with a singular subject like Bilal (he/she/it).",
            explanationUr = "واحد فاعل (جیسے بلال، He، She) کے ساتھ 'is' استعمال ہوتا ہے۔"
        ),
        QuizQuestion(
            key = "gram_3",
            category = "Grammar",
            promptEn = "Please carry ___ umbrella because it looks cloudy.",
            promptUr = "براہ کرم ___ چھتری ساتھ رکھیں کیونکہ بادل ہیں۔",
            options = listOf("a", "an", "these", "many"),
            correctIndex = 1,
            explanationEn = "'Umbrella' begins with a vowel sound ('uh'), so we use 'an'.",
            explanationUr = "'Umbrella' کی پہلی آواز واول (अ/اَ) ہے، اس لیے 'an' لگے گا۔"
        ),
        QuizQuestion(
            key = "gram_4",
            category = "Grammar",
            promptEn = "What is the correct plural of 'child'?",
            promptUr = "'child' (بچہ) کی درست جمع کیا ہے؟",
            options = listOf("childs", "childes", "children", "childrens"),
            correctIndex = 2,
            explanationEn = "'Children' is an irregular plural noun; never add '-s' to it.",
            explanationUr = "'children' خود جمع ہے، اس کے ساتھ 's' نہیں لگتا۔"
        ),
        QuizQuestion(
            key = "gram_5",
            category = "Grammar",
            promptEn = "___ mangoes in this basket here are very sweet.",
            promptUr = "یہاں ٹوکری میں رکھے ___ آم بہت میٹھے ہیں۔",
            options = listOf("This", "That", "These", "It"),
            correctIndex = 2,
            explanationEn = "Use 'These' for plural items ('mangoes') that are near you.",
            explanationUr = "قریب موجود جمع چیزوں کے لیے 'These' آتا ہے۔"
        ),
        QuizQuestion(
            key = "gram_6",
            category = "Grammar",
            promptEn = "Hina completed ___ homework before dinner.",
            promptUr = "حنا نے کھانے سے پہلے ___ ہوم ورک مکمل کر لیا۔",
            options = listOf("his", "her", "their", "my"),
            correctIndex = 1,
            explanationEn = "For a female subject ('Hina'), use the possessive adjective 'her'.",
            explanationUr = "لڑکی (حنا) کے لیے 'اپنا' کا ترجمہ 'her' ہوتا ہے۔"
        ),
        QuizQuestion(
            key = "gram_7",
            category = "Grammar",
            promptEn = "Ali ___ up at six o'clock every morning.",
            promptUr = "علی ہر صبح چھ بجے جاگتا ہے۔",
            options = listOf("wake", "wakes", "waking", "is wake"),
            correctIndex = 1,
            explanationEn = "In Present Simple, add '-s' to the verb for he/she/it ('Ali wakes').",
            explanationUr = "فعل حال مطلق میں واحد غائب (Ali) کے ساتھ فعل میں 's' کا اضافہ ہوتا ہے۔"
        ),
        QuizQuestion(
            key = "gram_8",
            category = "Grammar",
            promptEn = "Does this bus ___ downtown?",
            promptUr = "کیا یہ بس شہر کے مرکز جاتی ہے؟",
            options = listOf("goes", "going", "go", "went"),
            correctIndex = 2,
            explanationEn = "After 'Does' or 'doesn't', always use the base verb without '-s' ('go').",
            explanationUr = "'Does' آ جائے تو فعل واپس اپنی پہلی فارم ('go') میں آ جاتا ہے۔"
        ),
        QuizQuestion(
            key = "gram_9",
            category = "Grammar",
            promptEn = "Listen! Mother ___ dinner in the kitchen right now.",
            promptUr = "سنو! امی اس وقت باورچی خانے میں کھانا پکا رہی ہیں۔",
            options = listOf("cooks", "cooking", "is cooking", "cooked"),
            correctIndex = 2,
            explanationEn = "For actions happening right now, use 'am/is/are + verb-ing'.",
            explanationUr = "جو کام ابھی جاری ہو اس کے لیے 'is cooking' استعمال کریں۔"
        ),
        QuizQuestion(
            key = "gram_10",
            category = "Grammar",
            promptEn = "I didn't ___ out last night because I was tired.",
            promptUr = "میں کل رات باہر نہیں گیا کیونکہ میں تھکا ہوا تھا۔",
            options = listOf("went", "goes", "going", "go"),
            correctIndex = 3,
            explanationEn = "After 'didn't', always use the base verb V1 ('didn't go', never 'didn't went').",
            explanationUr = "'didn't' کے بعد ہمیشہ فعل کی پہلی فارم ('go') لگتی ہے۔"
        ),
        QuizQuestion(
            key = "gram_11",
            category = "Grammar",
            promptEn = "Don't worry, I ___ help you carry this heavy box.",
            promptUr = "فکر نہ کریں، میں یہ بھاری ڈبہ اٹھانے میں آپ کی مدد کروں گا۔",
            options = listOf("will", "will to", "am", "did"),
            correctIndex = 0,
            explanationEn = "Use 'will + base verb' (without 'to') for promises and quick offers.",
            explanationUr = "وعدے یا فوری مدد کے لیے 'will' کے بعد سیدھی پہلی فارم آتی ہے۔"
        ),
        QuizQuestion(
            key = "gram_12",
            category = "Grammar",
            promptEn = "Can you ___ that word again, please?",
            promptUr = "براہ کرم کیا آپ وہ لفظ دوبارہ دہرا سکتے ہیں؟",
            options = listOf("repeats", "to repeat", "repeat", "repeating"),
            correctIndex = 2,
            explanationEn = "Modal verb 'can' is always followed by the base verb ('repeat').",
            explanationUr = "'Can' کے بعد ہمیشہ فعل کی سادہ پہلی فارم آتی ہے۔"
        ),
        QuizQuestion(
            key = "gram_13",
            category = "Grammar",
            promptEn = "Our English class starts ___ nine o'clock ___ Monday.",
            promptUr = "ہماری کلاس پیر کو نو بجے شروع ہوتی ہے۔",
            options = listOf("in / at", "at / on", "on / in", "at / in"),
            correctIndex = 1,
            explanationEn = "Use 'at' for clock times ('at nine o'clock') and 'on' for days ('on Monday').",
            explanationUr = "گھڑی کے وقت کے لیے 'at' اور دن کے نام سے پہلے 'on' لگتا ہے۔"
        ),
        QuizQuestion(
            key = "gram_14",
            category = "Grammar",
            promptEn = "Today is ___ than yesterday.",
            promptUr = "آج کل سے زیادہ سردی ہے۔",
            options = listOf("cold", "more colder", "colder", "coldest"),
            correctIndex = 2,
            explanationEn = "Short adjectives add '-er' before 'than' ('colder than').",
            explanationUr = "دو دنوں کے موازنے کے لیے چھوٹی صفت میں 'er' لگائیں ('colder')۔"
        ),
        QuizQuestion(
            key = "gram_15",
            category = "Grammar",
            promptEn = "Thank you for your helpful ___.",
            promptUr = "آپ کے مفید مشورے کا شکریہ۔",
            options = listOf("advices", "an advice", "advice", "many advices"),
            correctIndex = 2,
            explanationEn = "'Advice' is uncountable in English, so never add '-s' or 'an'.",
            explanationUr = "انگریزی میں 'advice' ناقابلِ شمار ہے، اس لیے 'advices' غلط ہے۔"
        ),
        QuizQuestion(
            key = "gram_16",
            category = "Grammar",
            promptEn = "If you ___ daily, you will speak English fluently.",
            promptUr = "اگر آپ روز مشق کریں گے تو روانی سے انگریزی بولیں گے۔",
            options = listOf("will practice", "practiced", "practice", "practicing"),
            correctIndex = 2,
            explanationEn = "In First Conditional, use Present Simple after 'If' ('If you practice...').",
            explanationUr = "'If' والے شرطیہ حصے میں حال سادہ ('practice') آتا ہے، 'will' نہیں۔"
        )
    )

    fun buildVocabQuestions(
        words: List<VocabWordEntity>,
        count: Int,
        seed: Int = 42
    ): List<QuizQuestion> {
        if (words.size < 4) return emptyList()
        val rng = Random(seed)
        val pool = words.shuffled(rng).take(count)
        return pool.mapIndexed { idx, target ->
            val distractors = CurriculumCatalog.allWords
                .filter { it.id != target.id }
                .shuffled(rng)
                .take(3)
            val askUrduMeaning = idx % 2 == 0
            if (askUrduMeaning) {
                val options = (distractors.map { it.urdu } + target.urdu).shuffled(rng)
                QuizQuestion(
                    key = "vocab_en2ur_${target.id}",
                    category = "Vocabulary",
                    promptEn = "What is the Urdu meaning of '${target.english}'?",
                    promptUr = "'${target.english}' کا اردو مطلب کیا ہے؟",
                    options = options,
                    correctIndex = options.indexOf(target.urdu),
                    explanationEn = "${target.english}: ${target.explanation} Example: ${target.exampleEn}",
                    explanationUr = "${target.english} کا مطلب '${target.urdu}' ہے۔ مثال: ${target.exampleUr}"
                )
            } else {
                val options = (distractors.map { it.english } + target.english).shuffled(rng)
                QuizQuestion(
                    key = "vocab_ur2en_${target.id}",
                    category = "Vocabulary",
                    promptEn = "Which English word means:",
                    promptUr = "اس اردو لفظ کے لیے درست انگریزی لفظ چنیں:  ${target.urdu}",
                    options = options,
                    correctIndex = options.indexOf(target.english),
                    explanationEn = "'${target.english}' (${target.pronunciation}) — ${target.explanation}",
                    explanationUr = "'${target.urdu}' کے لیے درست انگریزی لفظ '${target.english}' ہے۔"
                )
            }
        }
    }

    fun buildPhraseQuestions(
        phrases: List<PhraseItemEntity>,
        count: Int,
        seed: Int = 99
    ): List<QuizQuestion> {
        if (phrases.size < 4) return emptyList()
        val rng = Random(seed)
        val pool = phrases.shuffled(rng).take(count)
        return pool.map { target ->
            val distractors = CurriculumCatalog.allPhrases
                .filter { it.id != target.id }
                .shuffled(rng)
                .take(3)
            val options = (distractors.map { it.english } + target.english).shuffled(rng)
            QuizQuestion(
                key = "phrase_${target.id}",
                category = if (target.isIdiom) "Idiom" else "Phrase",
                promptEn = "Which English phrase expresses this meaning?",
                promptUr = "${target.urdu}",
                options = options,
                correctIndex = options.indexOf(target.english),
                explanationEn = "${target.explanation} Example: ${target.exampleEn}",
                explanationUr = "${target.english} — ${target.urdu}"
            )
        }
    }

    fun buildDailyMiniQuiz(dayNumber: Int): List<QuizQuestion> {
        val dayWords = CurriculumCatalog.allWords.filter { it.dayNumber == dayNumber }
            .ifEmpty { CurriculumCatalog.allWords.take(10) }
        val dayPhrases = CurriculumCatalog.allPhrases.filter { it.dayNumber == dayNumber }
            .ifEmpty { CurriculumCatalog.allPhrases.take(10) }
        val vocabQs = buildVocabQuestions(dayWords, count = 3, seed = dayNumber * 17)
        val phraseQs = buildPhraseQuestions(dayPhrases, count = 2, seed = dayNumber * 31)
        val grammarQ = curatedGrammarQuestions[(dayNumber - 1).coerceAtLeast(0) % curatedGrammarQuestions.size]
        return vocabQs + phraseQs + grammarQ
    }

    fun buildQuizByMode(
        mode: String,
        dayNumber: Int,
        incorrectItems: List<IncorrectAnswerEntity>,
        sessionSeed: Int = (System.currentTimeMillis() % 100000).toInt()
    ): List<QuizQuestion> {
        return when (mode) {
            "DAILY_MINI" -> buildDailyMiniQuiz(dayNumber)
            "VOCABULARY" -> buildVocabQuestions(CurriculumCatalog.allWords, count = 10, seed = sessionSeed)
            "PHRASES" -> buildPhraseQuestions(CurriculumCatalog.allPhrases, count = 10, seed = sessionSeed)
            "GRAMMAR" -> curatedGrammarQuestions.shuffled(Random(sessionSeed)).take(10)
            "REVIEW_MISTAKES" -> incorrectItems.mapIndexed { index, item ->
                val fillerPool = listOf(
                    "I am learning every day.",
                    "Thank you so much.",
                    "Welcome",
                    "Practice"
                ).filter { it != item.correctOption && it != item.selectedOption }
                val opts = (listOf(item.correctOption, item.selectedOption) + fillerPool.take(2))
                    .distinct()
                    .shuffled(Random(sessionSeed + index))
                QuizQuestion(
                    key = item.questionKey,
                    category = "Review",
                    promptEn = item.questionPrompt,
                    promptUr = "اپنی پچھلی غلطی درست کریں:",
                    options = opts,
                    correctIndex = opts.indexOf(item.correctOption).coerceAtLeast(0),
                    explanationEn = item.explanationEn,
                    explanationUr = item.explanationUr
                )
            }
            else -> { // COMPREHENSIVE (15 questions)
                val v = buildVocabQuestions(CurriculumCatalog.allWords, count = 6, seed = sessionSeed)
                val p = buildPhraseQuestions(CurriculumCatalog.allPhrases, count = 5, seed = sessionSeed + 7)
                val g = curatedGrammarQuestions.shuffled(Random(sessionSeed + 13)).take(4)
                (v + p + g).shuffled(Random(sessionSeed))
            }
        }
    }
}
