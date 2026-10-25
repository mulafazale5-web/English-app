package com.example.data.curriculum

import com.example.data.local.GrammarLessonEntity

object GrammarSeedPart2 {
    private fun g(
        id: Int,
        day: Int,
        titleEn: String,
        titleUr: String,
        formula: String,
        expEn: String,
        expUr: String,
        examples: List<Pair<String, String>>,
        tip: String
    ) = GrammarLessonEntity(
        id = id,
        dayNumber = day,
        titleEn = titleEn,
        titleUr = titleUr,
        formula = formula,
        explanationEn = expEn,
        explanationUr = expUr,
        examplesPacked = examples.joinToString("||") { "${it.first}::${it.second}" },
        urduTip = tip
    )

    val lessons: List<GrammarLessonEntity> = listOf(
        g(
            16, 6,
            "Prepositions of Place: In, On, At",
            "جگہ کے حروفِ جار: In, On, At",
            "in (inside room/city) | on (surface) | at (exact point)",
            "Use 'in' for enclosed spaces and cities, 'on' for flat surfaces like a table or wall, and 'at' for specific points like a bus stop or door.",
            "کسی بند جگہ یا شہر کے لیے in، کسی سطح (میز، دیوار) کے لیے on، اور مخصوص مقام (دروازے، اسٹاپ) کے لیے at لگائیں۔",
            listOf(
                "My keys are in my pocket." to "میری چابیاں میری جیب میں ہیں۔",
                "Put the dictionary on the desk." to "لغت میز پر رکھ دیں۔",
                "Wait for me at the bus stop." to "بس اسٹاپ پر میرا انتظار کریں۔"
            ),
            "بڑے شہروں اور ملکوں کے لیے in آتا ہے: 'in Pakistan, in Karachi'۔"
        ),
        g(
            17, 7,
            "Prepositions of Time: At, On, In",
            "وقت کے حروفِ جار: At, On, In",
            "at + clock time | on + day/date | in + month/year/part of day",
            "Use 'at' for clock times (at 7:00), 'on' for days (on Monday), and 'in' for months, years, and morning/evening.",
            "گھڑی کے وقت کے لیے at، دن اور تاریخ کے لیے on، جبکہ مہینوں، سالوں اور صبح/شام کے لیے in لگائیں۔",
            listOf(
                "Class starts at nine o'clock." to "کلاس نو بجے شروع ہوتی ہے۔",
                "We have a quiz on Friday." to "جمعہ کو ہمارا کوئز ہے۔",
                "I read English in the morning." to "میں صبح کے وقت انگریزی پڑھتا ہوں۔"
            ),
            "استثنا یاد رکھیں: 'in the morning' مگر رات کے لیے 'at night' بولا جاتا ہے۔"
        ),
        g(
            18, 8,
            "Question Words (Wh- Questions)",
            "سوالیہ الفاظ: What, Where, When, Why, Who, How",
            "Question Word + Helping Verb + Subject + Main Verb?",
            "Start information questions with What (thing), Where (place), When (time), Why (reason), Who (person), or How (manner).",
            "معلومات پوچھنے کے لیے سوالیہ لفظ سب سے پہلے رکھیں، اس کے بعد امدادی فعل (is/are/do/did) لگائیں۔",
            listOf(
                "Where do you live?" to "آپ کہاں رہتے ہیں؟",
                "Why are you smiling?" to "آپ کیوں مسکرا رہے ہیں؟",
                "How do you spell your name?" to "آپ اپنے نام کے ہجے کیسے کرتے ہیں؟"
            ),
            "اردو میں 'کیا/کہاں' جملے کے درمیان آ سکتا ہے، مگر انگریزی سوال میں یہ ہمیشہ شروع میں آتا ہے۔"
        ),
        g(
            19, 9,
            "Adjectives and Word Order",
            "صفت (Adjective) اور اس کی ترتیب",
            "Adjective + Noun | Subject + be + Adjective",
            "Adjectives describe nouns (polite boy, fresh tea). Unlike some languages, English adjectives never become plural!",
            "صفت کسی اسم کی خوبی بتاتی ہے۔ انگریزی میں جمع اسم کے ساتھ بھی صفت میں 's' نہیں لگتا۔",
            listOf(
                "Usman is an honest man." to "عثمان ایک ایماندار شخص ہے۔",
                "She bought two blue notebooks." to "اس نے دو نیلی کاپیاں خریدیں۔",
                "The weather feels pleasant." to "موسم خوشگوار محسوس ہو رہا ہے۔"
            ),
            "'two blues notebooks' غلط ہے؛ صفت ہمیشہ واحد رہتی ہے: 'two blue notebooks'۔"
        ),
        g(
            20, 10,
            "Comparative Adjectives (Comparing Two Things)",
            "موازنہ کرنے والی صفت (دو چیزوں کا مقابلہ)",
            "Short word + -er + than | more + Long word + than",
            "To compare two things, add '-er' to short words (fast -> faster) or put 'more' before longer words (expensive -> more expensive), followed by 'than'.",
            "دو چیزوں کے مقابلے میں چھوٹے الفاظ کے ساتھ er اور لمبے الفاظ سے پہلے more لگا کر آگے than استعمال کریں۔",
            listOf(
                "Today is colder than yesterday." to "آج کل سے زیادہ سردی ہے۔",
                "This bag is more affordable than that one." to "یہ بیگ اس والے سے زیادہ مناسب قیمت ہے۔",
                "Speaking is better than staying silent." to "خاموش رہنے سے بولنا بہتر ہے۔"
            ),
            "کبھی بھی 'more better' نہ کہیں کیونکہ good کی تقابلی حالت خود 'better' ہے۔"
        ),
        g(
            21, 1,
            "Superlative Adjectives (The Best / The Most)",
            "سب سے بڑھ کر صفت (Superlative)",
            "the + Short word-est | the + most + Long word",
            "To show one thing is number one in a whole group of three or more, use 'the -est' or 'the most'.",
            "تین یا زیادہ چیزوں میں کسی ایک کو سب سے نمایاں بتانے کے لیے the کے ساتھ est یا most لگائیں۔",
            listOf(
                "Sara is the tallest student in class." to "سارہ کلاس میں سب سے لمبی طالبہ ہے۔",
                "Consistency is the most important habit." to "مستقل مزاجی سب سے اہم عادت ہے۔",
                "This is the best tea in town." to "یہ شہر کی سب سے بہترین چائے ہے۔"
            ),
            "Superlative سے پہلے 'the' لگانا یاد رکھیں: 'the easiest lesson'۔"
        ),
        g(
            22, 2,
            "Adverbs of Frequency (Always, Usually, Never)",
            "تواتر بتانے والے الفاظ (ہمیشہ، عموماً، کبھی نہیں)",
            "Subject + Adverb + Main Verb | Subject + be + Adverb",
            "Words like always (100%), usually (80%), sometimes (50%), and never (0%) go BEFORE normal verbs, but AFTER am/is/are.",
            "ہمیشہ، عموماً اور کبھی نہیں جیسے الفاظ عام فعل سے پہلے آتے ہیں، لیکن am/is/are کے بعد رکھے جاتے ہیں۔",
            listOf(
                "I always review my saved words." to "میں ہمیشہ اپنے محفوظ الفاظ دہراتا ہوں۔",
                "He is never late for work." to "وہ کام کے لیے کبھی لیٹ نہیں ہوتا۔",
                "We sometimes study in the park." to "ہم کبھی کبھار پارک میں پڑھتے ہیں۔"
            ),
            "Never کے ساتھ don't نہ لگائیں: 'I never lie' درست ہے، 'I don't never lie' غلط ہے۔"
        ),
        g(
            23, 3,
            "Countable and Uncountable Nouns",
            "گنی جانے والی اور نہ گنی جانے والی اشیاء",
            "Countable: 1 cup, 2 cups | Uncountable: water, advice, information",
            "Countable nouns can be counted (books, chairs). Uncountable nouns act like singular liquids or ideas (water, milk, knowledge, advice) and do not take 'a/an' or '-s'.",
            "کچھ چیزیں گنی جا سکتی ہیں جیسے کتابیں۔ پانی، دودھ، علم (knowledge) اور مشورہ (advice) انگریزی میں ناقابلِ شمار ہیں، ان کے ساتھ s نہیں لگتا۔",
            listOf(
                "Please give me some cold water." to "براہ کرم مجھے تھوڑا ٹھنڈا پانی دیں۔",
                "Thank you for your helpful advice." to "آپ کے مفید مشورے کا شکریہ۔",
                "This app provides clear information." to "یہ ایپ واضح معلومات فراہم کرتی ہے۔"
            ),
            "اردو میں 'مشورے/معلومات' جمع لگتے ہیں، مگر انگریزی میں 'advices' اور 'informations' غلط الفاظ ہیں!"
        ),
        g(
            24, 4,
            "Some, Any, Much, and Many",
            "مقدار کے الفاظ: Some, Any, Much, Many",
            "some (positive) | any (negative/question) | many (countable) | much (uncountable)",
            "Use 'some' in positive sentences and polite offers. Use 'any' in negatives and questions. Use 'many' for countable plurals and 'much' for uncountable nouns.",
            "مثبت جملوں میں some اور منفی و سوالیہ میں any لگائیں۔ گنی جانے والی چیزوں کے لیے many اور نہ گنی جانے والی کے لیے much۔",
            listOf(
                "I saved some useful idioms today." to "میں نے آج کچھ مفید محاورے محفوظ کیے۔",
                "Do you have any questions?" to "کیا آپ کا کوئی سوال ہے؟",
                "How many siblings do you have?" to "آپ کے کتنے بہن بھائی ہیں؟"
            ),
            "پیسے کے لیے 'How much money' بولتے ہیں، لیکن روپوں کے لیے 'How many rupees'۔"
        ),
        g(
            25, 5,
            "There is and There are",
            "کسی چیز کی موجودگی بتانا: There is / There are",
            "There is + singular/uncountable | There are + plural",
            "Use 'There is' and 'There are' to introduce that something exists in a place.",
            "جب بتانا ہو کہ کسی جگہ کوئی چیز موجود ہے تو جملہ There is یا There are سے شروع کریں۔",
            listOf(
                "There is a bookstore at the corner." to "گلی کے موڑ پر کتابوں کی ایک دکان ہے۔",
                "There are twenty dialogues in this app." to "اس ایپ میں بیس مکالمے موجود ہیں۔",
                "Is there a pharmacy nearby?" to "کیا قریب میں کوئی دواخانہ ہے؟"
            ),
            "اردو ترتیب 'میز پر کتاب ہے' کا لفظی ترجمہ 'On table book is' نہ کریں، 'There is a book on the table' کہیں۔"
        ),
        g(
            26, 6,
            "Connecting Ideas: And, But, Because, So",
            "جملوں کو جوڑنے والے الفاظ: اور، لیکن، کیونکہ، اس لیے",
            "Clause 1 + and / but / because / so + Clause 2",
            "Join two short sentences smoothly: 'and' adds info, 'but' shows contrast, 'because' gives the reason, and 'so' shows the result.",
            "دو چھوٹے جملوں کو جوڑ کر روانی پیدا کریں: وجہ کے لیے because اور نتیجے کے لیے so استعمال کریں۔",
            listOf(
                "I wanted to walk, but it started raining." to "میں پیدل جانا چاہتا تھا، لیکن بارش شروع ہو گئی۔",
                "He passed the test because he practiced daily." to "وہ ٹیسٹ میں کامیاب ہوا کیونکہ اس نے روز مشق کی۔",
                "My battery was low, so I plugged in the charger." to "بیٹری کم تھی، اس لیے میں نے چارجر لگا دیا۔"
            ),
            "ایک ہی جملے میں 'because' اور 'so' دونوں اکٹھے نہ لگائیں — صرف ایک کافی ہے۔"
        ),
        g(
            27, 7,
            "Object Pronouns (me, you, him, her, it, us, them)",
            "مفعولی ضمائر (مجھے، اسے، ہمیں، انہیں)",
            "Verb / Preposition + Object Pronoun (help me, with them)",
            "When a pronoun receives the action after a verb or preposition (to, for, with), use me, him, her, us, or them.",
            "جب ضمیر فعل یا حرفِ جار (with, for, to) کے بعد آئے تو I کے بجائے me اور they کے بجائے them استعمال کریں۔",
            listOf(
                "Could you explain this rule to me?" to "کیا آپ مجھے یہ اصول سمجھا سکتے ہیں؟",
                "My parents support us in every goal." to "ہمارے والدین ہر مقصد میں ہمارا ساتھ دیتے ہیں۔",
                "I bought these apples and washed them." to "میں نے یہ سیب خریدے اور انہیں دھویا۔"
            ),
            "حرفِ جار کے بعد ہمیشہ مفعولی حالت آتی ہے: 'between you and me' (نہ کہ you and I)۔"
        ),
        g(
            28, 8,
            "Polite Imperatives (Instructions & Requests)",
            "شائستہ ہدایات اور درخواستیں",
            "Please + Base Verb (V1) | Don't + Base Verb (V1)",
            "Start directly with the base verb to give clear directions or instructions. Add 'Please' to sound warm and respectful.",
            "ہدایت یا راستہ بتانے کے لیے جملہ سیدھا فعل کی پہلی فارم سے شروع کریں۔ شائستگی کے لیے Please لگائیں۔",
            listOf(
                "Please open your book to page ten." to "براہ کرم اپنی کتاب کا صفحہ دس کھولیں۔",
                "Turn right after the bridge." to "پُل کے بعد دائیں مڑ جائیں۔",
                "Don't hesitate to ask questions." to "سوال پوچھنے میں ہچکچاہٹ نہ کریں۔"
            ),
            "ان جملوں میں شروع میں 'You' بولنے کی ضرورت نہیں ہوتی، یہ خود بخود مخاطب کے لیے ہوتے ہیں۔"
        ),
        g(
            29, 9,
            "Present Perfect Basics (have / has + V3)",
            "فعل حال مکمل: جو کام ابھی مکمل ہوا یا تجربہ بن گیا",
            "I/You/We/They + have + V3 | He/She/It + has + V3",
            "Use 'have/has + past participle (V3)' for life experiences or recent actions whose result matters right now.",
            "ایسے کام جو حال ہی میں مکمل ہوئے ہوں یا زندگی کا تجربہ بن چکے ہوں، ان کے لیے have/has اور تیسری فارم لگائیں۔",
            listOf(
                "I have learned fifty English words!" to "میں نے پچاس انگریزی الفاظ سیکھ لیے ہیں!",
                "She has completed her job application." to "اس نے اپنی ملازمت کی درخواست مکمل کر لی ہے۔",
                "Have you ever visited Islamabad?" to "کیا آپ کبھی اسلام آباد گئے ہیں؟"
            ),
            "اگر ماضی کا وقت (yesterday, in 2022) جملے میں موجود ہو تو have/has نہ لگائیں، صرف دوسری فارم لگائیں۔"
        ),
        g(
            30, 10,
            "First Conditional (Real Future Possibilities)",
            "شرطیہ جملے: اگر ایسا ہوگا تو ویسا ہوگا",
            "If + Present Simple, Subject + will + V1",
            "When talking about a real condition in the future, use Present Simple in the 'If' part, and 'will + V1' in the result part!",
            "مستقبل کی شرط بتاتے وقت 'اگر' (If) والے حصے میں حال سادہ (Present Simple) آتا ہے، اور دوسرے حصے میں will آتا ہے۔",
            listOf(
                "If you practice daily, you will speak fluently." to "اگر آپ روز مشق کریں گے تو روانی سے بولیں گے۔",
                "If it rains tomorrow, we will stay indoors." to "اگر کل بارش ہوئی تو ہم اندر ہی رہیں گے۔",
                "I will call you if I reach early." to "اگر میں جلدی پہنچ گیا تو آپ کو فون کروں گا۔"
            ),
            "اردو میں دونوں حصوں میں 'گا/گے' آتا ہے، مگر انگریزی میں 'If you will practice' غلط ہے — 'If you practice' کہیں!"
        )
    )
}
