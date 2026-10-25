package com.example.data.curriculum

import com.example.data.local.GrammarLessonEntity

object GrammarSeedPart1 {
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
            1, 1,
            "Subject Pronouns (I, You, We, They, He, She, It)",
            "فاعل ضمائر (میں، تم/آپ، ہم، وہ)",
            "Subject Pronoun + Verb + Object",
            "Subject pronouns replace a person or thing doing the action. Remember 'I' is always written with a capital letter.",
            "فاعل ضمائر کسی کام کرنے والے شخص یا چیز کی جگہ آتے ہیں۔ انگریزی میں 'I' ہمیشہ بڑے حرف سے لکھا جاتا ہے۔",
            listOf(
                "I am a student." to "میں ایک طالب علم ہوں۔",
                "She speaks Urdu and English." to "وہ اردو اور انگریزی بولتی ہے۔",
                "They live in Lahore." to "وہ لاہور میں رہتے ہیں۔"
            ),
            "اردو میں 'وہ' لڑکے اور لڑکی دونوں کے لیے آتا ہے، لیکن انگریزی میں لڑکے کے لیے He اور لڑکی کے لیے She استعمال کریں۔"
        ),
        g(
            2, 2,
            "Verb 'To Be' in Present (am, is, are)",
            "موجودہ زمانے میں (ہوں، ہے، ہیں) کا استعمال",
            "I + am | He/She/It + is | You/We/They + are",
            "Use 'am', 'is', and 'are' to describe who someone is, how they feel, or where they are right now.",
            "کسی شخص کی شناخت، حالت یا جگہ بتانے کے لیے I کے ساتھ am، واحد کے ساتھ is، اور جمع یا You کے ساتھ are لگائیں۔",
            listOf(
                "I am ready for class." to "میں کلاس کے لیے تیار ہوں۔",
                "Bilal is very polite." to "بلال بہت شائستہ ہے۔",
                "We are good friends." to "ہم اچھے دوست ہیں۔"
            ),
            "اردو میں 'ہوں/ہے' جملے کے آخر میں آتا ہے، مگر انگریزی میں am/is/are فاعل (Subject) کے فوراً بعد آتا ہے۔"
        ),
        g(
            3, 3,
            "Articles: A, An, and The",
            "حروفِ تخصیص: A, An اور The",
            "a + consonant sound | an + vowel sound (a,e,i,o,u) | the + specific item",
            "Use 'a' or 'an' for any one general countable thing. Use 'the' when both speaker and listener know which exact thing you mean.",
            "کسی ایک عام چیز کے لیے a یا an لگائیں (آواز vowel ہو تو an)۔ کسی خاص یا جانی پہچانی چیز کے لیے the استعمال کریں۔",
            listOf(
                "I bought a notebook." to "میں نے ایک نوٹ بک خریدی۔",
                "Carry an umbrella today." to "آج ایک چھتری ساتھ رکھیں۔",
                "Please close the window." to "براہ کرم (یہ خاص) کھڑکی بند کر دیں۔"
            ),
            "لفظ کے پہلے حرف کے بجائے پہلی آواز دیکھیں: 'an hour' (آور) اور 'a university' (یونیورسٹی)۔"
        ),
        g(
            4, 4,
            "Singular and Plural Nouns",
            "واحد اور جمع اسم",
            "Noun + -s / -es (or irregular: child -> children)",
            "Most nouns add '-s' for more than one. Nouns ending in -s, -sh, -ch, or -x add '-es'. Some common words change spelling.",
            "ایک سے زیادہ چیزوں کے لیے عموماً آخر میں s یا es لگاتے ہیں۔ کچھ خاص الفاظ کی جمع مختلف ہوتی ہے جیسے child سے children۔",
            listOf(
                "One book, three books." to "ایک کتاب، تین کتابیں۔",
                "Two buses arrived together." to "دو بسیں ایک ساتھ آئیں۔",
                "The children are playing outside." to "بچے باہر کھیل رہے ہیں۔"
            ),
            "کبھی بھی 'childrens' یا 'peoples' (عام لوگوں کے لیے) نہ کہیں؛ children اور people خود جمع ہیں۔"
        ),
        g(
            5, 5,
            "Demonstratives: This, That, These, Those",
            "اشارے کے الفاظ: یہ اور وہ (واحد و جمع)",
            "Near: this (1), these (2+) | Far: that (1), those (2+)",
            "Use 'this' and 'these' for things close to your hand. Use 'that' and 'those' for things farther away.",
            "قریب کی ایک چیز کے لیے this اور زیادہ کے لیے these لگائیں۔ دور کی ایک چیز کے لیے that اور زیادہ کے لیے those۔",
            listOf(
                "This chair is comfortable." to "یہ کرسی آرام دہ ہے۔",
                "These mangoes are sweet." to "یہ آم میٹھے ہیں۔",
                "Those mountains look beautiful." to "وہ پہاڑ خوبصورت لگ رہے ہیں۔"
            ),
            "This/That کے ساتھ ہمیشہ 'is' اور These/Those کے ساتھ ہمیشہ 'are' لگائیں۔"
        ),
        g(
            6, 6,
            "Possessive Adjectives (my, your, his, her, our, their)",
            "ملکیت ظاہر کرنے والے الفاظ (میرا، تمہارا، ہمارا)",
            "Possessive Word + Noun (my bag, her book)",
            "Possessive adjectives show who owns something. They always come directly before a noun.",
            "ملکیت ظاہر کرنے والے الفاظ بتاتے ہیں کہ کوئی چیز کس کی ہے۔ یہ ہمیشہ اسم (Noun) سے پہلے آتے ہیں۔",
            listOf(
                "My goal is clear." to "میرا مقصد واضح ہے۔",
                "Hina completed her homework." to "حنا نے اپنا ہوم ورک مکمل کر لیا۔",
                "Our teacher explains clearly." to "ہمارے استاد صاف وضاحت کرتے ہیں۔"
            ),
            "اردو میں 'اپنا/اپنی' سب کے لیے بولتے ہیں، لیکن انگریزی میں I کے لیے my، He کے لیے his، اور She کے لیے her آتا ہے۔"
        ),
        g(
            7, 7,
            "Present Simple: Habits & Facts",
            "فعل حال مطلق: عادات اور حقائق",
            "I/You/We/They + V1 | He/She/It + V1(-s/-es)",
            "Use Present Simple for daily routines and general truths. Add '-s' or '-es' to the verb when the subject is he, she, it, or one name.",
            "روزمرہ کی عادات اور سچائیوں کے لیے فعل کی پہلی فارم لگائیں۔ اگر فاعل He, She, It یا واحد نام ہو تو فعل کے ساتھ s یا es بڑھائیں۔",
            listOf(
                "I drink tea every morning." to "میں ہر صبح چائے پیتا ہوں۔",
                "Ali wakes up at six o'clock." to "علی چھ بجے جاگتا ہے۔",
                "The sun rises in the east." to "سورج مشرق سے طلوع ہوتا ہے۔"
            ),
            "He/She کے ساتھ 'is' اور پہلی فارم اکٹھے نہ لگائیں: 'He goes' درست ہے، 'He is go' غلط ہے۔"
        ),
        g(
            8, 8,
            "Present Simple: Negatives & Questions",
            "فعل حال مطلق: منفی اور سوالیہ جملے",
            "don't / doesn't + V1 | Do / Does + Subject + V1?",
            "Use 'do not (don't)' with I/you/we/they and 'does not (doesn't)' with he/she/it. After 'does' or 'doesn't', use the base verb without '-s'.",
            "منفی اور سوالیہ جملوں میں do یا does استعمال کریں۔ یاد رکھیں کہ does یا doesn't کے بعد فعل کے ساتھ s نہیں لگتا۔",
            listOf(
                "I do not waste time." to "میں وقت ضائع نہیں کرتا۔",
                "She doesn't like spicy food." to "اسے تیز مرچ والا کھانا پسند نہیں۔",
                "Does this bus go downtown?" to "کیا یہ بس شہر کے مرکز جاتی ہے؟"
            ),
            "غلطی سے بچیں: 'Does she knows?' غلط ہے — درست جملہ 'Does she know?' ہے۔"
        ),
        g(
            9, 9,
            "Present Continuous: Happening Right Now",
            "فعل حال جاری: جو کام ابھی ہو رہا ہے",
            "am / is / are + Verb-ing",
            "Use Present Continuous for actions happening right at this moment as you speak.",
            "جو کام بالکل اس وقت آپ کے بولتے ہوئے جاری ہو، اس کے لیے am/is/are کے بعد فعل میں ing لگائیں۔",
            listOf(
                "I am learning English right now." to "میں اس وقت انگریزی سیکھ رہا ہوں۔",
                "Mother is cooking dinner in the kitchen." to "امی باورچی خانے میں کھانا پکا رہی ہیں۔",
                "Are you listening carefully?" to "کیا آپ غور سے سن رہے ہیں؟"
            ),
            "Verb-ing سے پہلے am, is یا are لگانا لازمی ہے، صرف 'I learning' کہنا ادھورا ہے۔"
        ),
        g(
            10, 10,
            "Past Simple: Regular Verbs (-ed)",
            "فعل ماضی مطلق: باقاعدہ افعال (-ed)",
            "Subject + Verb-ed (walked, studied, helped)",
            "Use Past Simple for actions that finished in the past (yesterday, last night, two days ago). Regular verbs add '-ed'.",
            "ماضی میں مکمل ہو چکے کاموں کے لیے فعل کی دوسری فارم استعمال ہوتی ہے۔ عام افعال کے آخر میں ed لگتا ہے۔",
            listOf(
                "I practiced ten phrases yesterday." to "میں نے کل دس جملوں کی مشق کی۔",
                "We visited our relatives on Sunday." to "ہم اتوار کو اپنے رشتہ داروں سے ملنے گئے۔",
                "He helped an elderly passenger." to "اس نے ایک بزرگ مسافر کی مدد کی۔"
            ),
            "ماضی کے سادہ جملے میں دوسری فارم کے ساتھ was نہیں آتا: 'I walked' درست ہے، 'I was walked' غلط ہے۔"
        ),
        g(
            11, 1,
            "Past Simple: Irregular Verbs & Did",
            "فعل ماضی: بے قاعدہ افعال اور Did کا استعمال",
            "went / saw / bought | didn't + V1 | Did + Subject + V1?",
            "Common verbs like go->went, buy->bought, and eat->ate have special past forms. In questions and negatives with 'did/didn't', return to V1!",
            "کچھ عام افعال کی دوسری فارم مختلف ہوتی ہے جیسے go سے went۔ لیکن did یا didn't آ جائے تو فعل واپس پہلی فارم (V1) میں آ جاتا ہے۔",
            listOf(
                "We bought fresh fruit from the market." to "ہم نے بازار سے تازہ پھل خریدے۔",
                "I didn't go out last night." to "میں کل رات باہر نہیں گیا۔",
                "Did you understand the story?" to "کیا آپ کو کہانی سمجھ آئی؟"
            ),
            "بہت عام غلطی: 'I didn't went' غلط ہے! ہمیشہ 'I didn't go' کہیں۔"
        ),
        g(
            12, 2,
            "Future with 'Will'",
            "مستقبل کا اظہار: Will کا استعمال",
            "Subject + will + V1 (will not = won't)",
            "Use 'will + base verb' for promises, quick decisions made right now, and general predictions about the future.",
            "آنے والے وقت کے وعدوں، فوری فیصلوں اور اندازوں کے لیے تمام ضمائر کے ساتھ will اور پہلی فارم لگائیں۔",
            listOf(
                "I will call you this evening." to "میں آج شام آپ کو فون کروں گا۔",
                "Don't worry, I will help you." to "فکر نہ کریں، میں آپ کی مدد کروں گا۔",
                "Will they arrive on time?" to "کیا وہ وقت پر پہنچیں گے؟"
            ),
            "Will کے بعد 'to' کبھی نہ لگائیں: 'I will study' درست ہے، 'I will to study' غلط ہے۔"
        ),
        g(
            13, 3,
            "Future Plans with 'Going to'",
            "پہلے سے طے شدہ مستقبل کے ارادے: Going to",
            "am / is / are + going to + V1",
            "Use 'be going to' when you have already planned or decided something before speaking.",
            "جب آپ نے کوئی کام کرنے کا ارادہ یا منصوبہ پہلے سے بنا لیا ہو تو am/is/are going to استعمال کریں۔",
            listOf(
                "I am going to start a coding course." to "میں کوڈنگ کا کورس شروع کرنے والا ہوں۔",
                "She is going to prepare for her interview." to "وہ اپنے انٹرویو کی تیاری کرنے جا رہی ہے۔",
                "Look at the clouds! It is going to rain." to "بادلوں کو دیکھو! بارش ہونے والی ہے۔"
            ),
            "یہاں 'going to' کا مطلب پیدل جانا نہیں بلکہ 'ارادہ ہونا / والا ہونا' ہے۔"
        ),
        g(
            14, 4,
            "Modal Verb: Can & Can't (Ability & Requests)",
            "قابلیت اور اجازت: Can اور Can't",
            "Subject + can / cannot (can't) + V1",
            "Use 'can' to talk about skills you have or to make friendly requests. The verb after 'can' never takes '-s' or 'to'.",
            "اپنی صلاحیت بتانے یا شائستہ درخواست کرنے کے لیے can کے بعد ہمیشہ فعل کی پہلی فارم لگائیں۔",
            listOf(
                "I can read Urdu and English." to "میں اردو اور انگریزی پڑھ سکتا ہوں۔",
                "He cannot drive a bus." to "وہ بس نہیں چلا سکتا۔",
                "Can you repeat the last word?" to "کیا آپ آخری لفظ دہرا سکتے ہیں؟"
            ),
            "He can speaks نہیں ہوتا — ہمیشہ سادہ پہلی فارم 'He can speak' بولیں۔"
        ),
        g(
            15, 5,
            "Modal Verbs: Should & Must (Advice & Rules)",
            "مشورہ اور لازمی کام: Should اور Must",
            "should + V1 (helpful advice) | must + V1 (strong duty)",
            "Use 'should' to give polite advice ('چاہیے'). Use 'must' when something is very important or a strict rule ('ضرور چاہیے').",
            "نرم مشورہ دینے کے لیے should اور انتہائی ضروری کام یا قانون کے لیے must استعمال کریں۔",
            listOf(
                "You should sleep eight hours daily." to "آپ کو روزانہ آٹھ گھنٹے سونا چاہیے۔",
                "Students should revise new words." to "طلبہ کو نئے الفاظ دہرانے چاہئیں۔",
                "Passengers must wear a seatbelt." to "مسافروں کو لازمی سیٹ بیلٹ پہننی چاہیے۔"
            ),
            "Should میں 'l' خاموش (silent) ہوتا ہے، اس کا تلفظ 'شُڈ' ہے، 'شُلڈ' نہیں۔"
        )
    )
}
