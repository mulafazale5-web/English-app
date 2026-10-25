package com.example.data.curriculum

import com.example.data.local.VocabWordEntity

object VocabSeedPart1 {
    private fun w(
        id: Int,
        day: Int,
        cat: String,
        en: String,
        pron: String,
        pos: String,
        ur: String,
        exp: String,
        exEn: String,
        exUr: String
    ) = VocabWordEntity(
        id = id,
        dayNumber = day,
        category = cat,
        english = en,
        pronunciation = pron,
        partOfSpeech = pos,
        urdu = ur,
        explanation = exp,
        exampleEn = exEn,
        exampleUr = exUr
    )

    val words: List<VocabWordEntity> = listOf(
        // Day 1: Greetings & Politeness (1–10)
        w(1, 1, "Greetings & Basics", "Welcome", "wel-kum", "Verb / Noun", "خوش آمدید", "A friendly greeting when someone arrives.", "Welcome to our English class!", "ہماری انگلش کلاس میں خوش آمدید!"),
        w(2, 1, "Greetings & Basics", "Polite", "puh-lyt", "Adjective", "شائستہ / بااخلاق", "Showing good manners and respect toward others.", "Ali is always polite to his teachers.", "علی ہمیشہ اپنے اساتذہ کے ساتھ شائستہ رہتا ہے۔"),
        w(3, 1, "Greetings & Basics", "Grateful", "grayt-ful", "Adjective", "شکر گزار", "Feeling or showing thanks for kindness.", "I am grateful for your help today.", "میں آج آپ کی مدد کے لیے شکر گزار ہوں۔"),
        w(4, 1, "Greetings & Basics", "Neighbor", "nay-ber", "Noun", "پڑوسی / ہمسایہ", "A person who lives near your home.", "Our new neighbor brought us sweet dates.", "ہمارے نئے پڑوسی ہمارے لیے میٹھی کھجوریں لائے۔"),
        w(5, 1, "Greetings & Basics", "Introduce", "in-truh-doos", "Verb", "تعارف کرانا", "To tell someone your name or another person's name for the first time.", "Let me introduce my younger brother.", "مجھے اپنے چھوٹے بھائی کا تعارف کرانے دیں۔"),
        w(6, 1, "Greetings & Basics", "Respect", "ri-spekt", "Noun / Verb", "عزت / احترام", "Polite care and honor shown to someone.", "We should speak to elders with respect.", "ہمیں بڑوں سے احترام کے ساتھ بات کرنی چاہیے۔"),
        w(7, 1, "Greetings & Basics", "Excuse", "ik-skyoos", "Verb / Noun", "معذرت / وجہ", "To ask politely for forgiveness or attention.", "Please excuse me for being five minutes late.", "پانچ منٹ تاخیر سے آنے پر براہ کرم مجھے معاف کیجیے۔"),
        w(8, 1, "Greetings & Basics", "Guest", "gest", "Noun", "مہمان", "A person invited to visit your home or event.", "We prepared warm tea for our guest.", "ہم نے اپنے مہمان کے لیے گرم چائے تیار کی۔"),
        w(9, 1, "Greetings & Basics", "Conversation", "kon-ver-say-shun", "Noun", "گفتگو / بات چیت", "An informal talk between two or more people.", "I had a pleasant conversation with Sara.", "میری سارہ کے ساتھ خوشگوار گفتگو ہوئی۔"),
        w(10, 1, "Greetings & Basics", "Smile", "smyl", "Verb / Noun", "مسکراہٹ / مسکرانا", "A happy expression made with the corners of your mouth.", "A warm smile makes everyone feel comfortable.", "ایک گرمجوش مسکراہٹ سب کو پُرسکون محسوس کراتی ہے۔"),

        // Day 2: Family & People (11–20)
        w(11, 2, "Family & People", "Parents", "pair-unts", "Noun", "والدین", "A person's mother and father.", "My parents wake up early every morning.", "میرے والدین ہر صبح جلدی جاگتے ہیں۔"),
        w(12, 2, "Family & People", "Sibling", "sib-ling", "Noun", "بہن یا بھائی", "A brother or a sister.", "How many siblings do you have?", "آپ کے کتنے بہن بھائی ہیں؟"),
        w(13, 2, "Family & People", "Relative", "rel-uh-tiv", "Noun", "رشتہ دار", "A member of your extended family such as an aunt or cousin.", "Many relatives visited us on Eid.", "عید پر بہت سے رشتہ دار ہم سے ملنے آئے۔"),
        w(14, 2, "Family & People", "Childhood", "chyld-hud", "Noun", "بچپن", "The time of life when a person is a child.", "I spent my childhood in Lahore.", "میں نے اپنا بچپن لاہور میں گزارا۔"),
        w(15, 2, "Family & People", "Honest", "on-ist", "Adjective", "ایماندار / سچا", "Telling the truth and never cheating.", "Everyone trusts Bilal because he is honest.", "سب بلال پر بھروسہ کرتے ہیں کیونکہ وہ ایماندار ہے۔"),
        w(16, 2, "Family & People", "Generous", "jen-er-us", "Adjective", "سخی / فیاض", "Happy to share time, help, or money with others.", "My grandmother is very kind and generous.", "میری دادی بہت مہربان اور سخی ہیں۔"),
        w(17, 2, "Family & People", "Patient", "pay-shunt", "Adjective", "صابر / تحمل مزاج", "Able to wait calmly without getting angry.", "A good teacher is always patient with beginners.", "ایک اچھا استاد نئے سیکھنے والوں کے ساتھ ہمیشہ صبر سے کام لیتا ہے۔"),
        w(18, 2, "Family & People", "Support", "suh-port", "Verb / Noun", "سہارا دینا / مدد", "To help and encourage someone in life.", "Good friends support each other in hard times.", "اچھے دوست مشکل وقت میں ایک دوسرے کا ساتھ دیتے ہیں۔"),
        w(19, 2, "Family & People", "Together", "tuh-geth-er", "Adverb", "ایک ساتھ / مل کر", "With each other in one place or group.", "Our family eats dinner together at eight.", "ہمارا خاندان آٹھ بجے ایک ساتھ رات کا کھانا کھاتا ہے۔"),
        w(20, 2, "Family & People", "Trust", "trust", "Verb / Noun", "بھروسہ / اعتماد", "Strong belief that someone is reliable and truthful.", "It takes time to build real trust.", "حقیقی بھروسہ بنانے میں وقت لگتا ہے۔"),

        // Day 3: Home & Daily Life (21–30)
        w(21, 3, "Home & Daily Life", "Routine", "roo-teen", "Noun", "روزمرہ کا معمول", "The regular way you do things every day.", "Reading ten words is part of my morning routine.", "دس الفاظ پڑھنا میرے صبح کے معمول کا حصہ ہے۔"),
        w(22, 3, "Home & Daily Life", "Kitchen", "kich-un", "Noun", "باورچی خانہ", "The room where food is cooked.", "Something delicious is cooking in the kitchen.", "باورچی خانے میں کوئی لذیذ چیز پک رہی ہے۔"),
        w(23, 3, "Home & Daily Life", "Clean", "kleen", "Adjective / Verb", "صاف / صاف کرنا", "Free from dirt or mess.", "Please keep your study desk clean.", "براہ کرم اپنی پڑھائی کی میز صاف رکھیں۔"),
        w(24, 3, "Home & Daily Life", "Comfortable", "kumf-ter-bul", "Adjective", "آرام دہ", "Giving physical ease and relaxation.", "This wooden chair is surprisingly comfortable.", "یہ لکڑی کی کرسی حیرت انگیز طور پر آرام دہ ہے۔"),
        w(25, 3, "Home & Daily Life", "Window", "win-doh", "Noun", "کھڑکی", "An opening in a wall with glass that lets in light and air.", "Open the window to let in fresh air.", "تازہ ہوا آنے کے لیے کھڑکی کھول دیں۔"),
        w(26, 3, "Home & Daily Life", "Prepare", "pri-pair", "Verb", "تیار کرنا", "To make something ready for use or action.", "I prepare my school bag before going to sleep.", "میں سونے سے پہلے اپنا اسکول بیگ تیار کرتا ہوں۔"),
        w(27, 3, "Home & Daily Life", "blanket", "blang-kit", "Noun", "کمبل", "A warm cover used on a bed in cold weather.", "Take a warm blanket tonight because it is cold.", "آج رات گرم کمبل لے لیں کیونکہ سردی ہے۔").copy(english = "Blanket"),
        w(28, 3, "Home & Daily Life", "Repair", "ri-pair", "Verb", "مرمت کرنا", "To fix something that is broken.", "My father helped me repair my bicycle.", "میرے والد نے میری سائیکل مرمت کرنے میں مدد کی۔"),
        w(29, 3, "Home & Daily Life", "Quiet", "kwy-ut", "Adjective", "خاموش / پُرسکون", "Making very little or no noise.", "I study best in a quiet room.", "میں خاموش کمرے میں سب سے اچھی پڑھائی کرتا ہوں۔"),
        w(30, 3, "Home & Daily Life", "Habit", "hab-it", "Noun", "عادت", "Something you do regularly, often without thinking.", "Speaking English daily is a great habit.", "روزانہ انگریزی بولنا ایک بہترین عادت ہے۔"),

        // Day 4: Food & Dining (31–40)
        w(31, 4, "Food & Dining", "Breakfast", "brek-fust", "Noun", "ناشتہ", "The first meal of the day eaten in the morning.", "Never skip a healthy breakfast before school.", "اسکول جانے سے پہلے صحت بخش ناشتہ کبھی نہ چھوڑیں۔"),
        w(32, 4, "Food & Dining", "Delicious", "di-lish-us", "Adjective", "لذیذ / مزیدار", "Having a very pleasant taste or smell.", "My mother makes delicious chicken biryani.", "میری امی بہت لذیذ چکن بریانی بناتی ہیں۔"),
        w(33, 4, "Food & Dining", "Fresh", "fresh", "Adjective", "تازہ", "Newly made, picked, or clean; not old.", "We bought fresh mangoes from the market.", "ہم نے بازار سے تازہ آم خریدے۔"),
        w(34, 4, "Food & Dining", "Hungry", "hung-gree", "Adjective", "بھوکا", "Feeling the need to eat food.", "Are you hungry after playing cricket?", "کیا کرکٹ کھیلنے کے بعد آپ کو بھوک لگی ہے؟"),
        w(35, 4, "Food & Dining", "Thirsty", "thur-stee", "Adjective", "پیاسا", "Feeling the need to drink water or liquids.", "Drink cold water if you feel thirsty.", "اگر آپ کو پیاس لگے تو ٹھنڈا پانی پی لیں۔"),
        w(36, 4, "Food & Dining", "Vegetables", "vej-tuh-bulz", "Noun", "سبزیاں", "Plants or parts of plants eaten as food.", "Green vegetables give our body important vitamins.", "ہری سبزیاں ہمارے جسم کو اہم وٹامنز دیتی ہیں۔"),
        w(37, 4, "Food & Dining", "Recipe", "res-uh-pee", "Noun", "پکانے کی ترکیب", "A set of instructions for cooking a dish.", "Can you share the recipe for this soup?", "کیا آپ اس سوپ کی ترکیب بتا سکتے ہیں؟"),
        w(38, 4, "Food & Dining", "Spicy", "spy-see", "Adjective", "مصالحہ دار / تیکھا", "Flavored with hot spices like chili.", "Is this curry too spicy for you?", "کیا یہ سالن آپ کے لیے زیادہ مرچوں والا ہے؟"),
        w(39, 4, "Food & Dining", "Share", "shair", "Verb", "بانٹنا / شریک کرنا", "To divide and give part of something to others.", "Let us share these sandwiches at lunch.", "آئیے دوپہر کے کھانے میں یہ سینڈوچ بانٹ کر کھائیں۔"),
        w(40, 4, "Food & Dining", "Healthy", "hel-thee", "Adjective", "صحت مند / صحت بخش", "Good for your body and mind.", "Fruit is a healthy snack between meals.", "کھانوں کے درمیان پھل ایک صحت بخش غذا ہے۔"),

        // Day 5: School & Study (41–55)
        w(41, 5, "School & Study", "Knowledge", "nol-ij", "Noun", "علم / واقفیت", "Information and understanding gained through learning.", "Reading books increases your knowledge.", "کتابیں پڑھنے سے آپ کے علم میں اضافہ ہوتا ہے۔"),
        w(42, 5, "School & Study", "Practice", "prak-tis", "Verb / Noun", "مشق / مشق کرنا", "Doing an activity repeatedly to improve your skill.", "Daily practice makes English pronunciation clear.", "روزانہ کی مشق انگریزی تلفظ کو صاف بناتی ہے۔"),
        w(43, 5, "School & Study", "Mistake", "mi-stayk", "Noun", "غلطی", "Something done wrong unintentionally.", "Do not be afraid of making a mistake while speaking.", "بولتے وقت غلطی کرنے سے مت گھبرائیں۔"),
        w(44, 5, "School & Study", "Understand", "un-der-stand", "Verb", "سمجھنا", "To know the meaning of words, lessons, or ideas.", "Did you understand today's grammar rule?", "کیا آپ کو آج کا گرامر کا اصول سمجھ آیا؟"),
        w(45, 5, "School & Study", "Explain", "ik-splayn", "Verb", "وضاحت کرنا / سمجھانا", "To make an idea clear and easy to understand.", "Could you explain this sentence again?", "کیا آپ اس جملے کی دوبارہ وضاحت کر سکتے ہیں؟"),
        w(46, 5, "School & Study", "Improve", "im-proov", "Verb", "بہتر بنانا / سدھارنا", "To become better than before.", "My English vocabulary improves every week.", "میرا انگریزی ذخیرہ الفاظ ہر ہفتے بہتر ہوتا ہے۔"),
        w(47, 5, "School & Study", "Attention", "uh-ten-shun", "Noun", "توجہ / دھیان", "Careful listening or watching.", "Pay close attention to how each word sounds.", "غور سے توجہ دیں کہ ہر لفظ کی آواز کیسی ہے۔"),
        w(48, 5, "School & Study", "Homework", "hohm-wurk", "Noun", "گھر کا تعلیمی کام", "School tasks given to students to finish at home.", "I completed my math homework before evening.", "میں نے شام سے پہلے اپنا ریاضی کا ہوم ورک مکمل کر لیا۔"),
        w(49, 5, "School & Study", "Library", "ly-brer-ee", "Noun", "کتب خانہ / لائبریری", "A quiet building or room full of books to read or borrow.", "We borrowed two storybooks from the school library.", "ہم نے اسکول کی لائبریری سے کہانیوں کی دو کتابیں لیں۔"),
        w(50, 5, "School & Study", "Dictionary", "dik-shuh-ner-ee", "Noun", "لغت / ڈکشنری", "A book or app that lists words and their meanings.", "Look up unfamiliar words in a dictionary.", "نئے الفاظ کے معنی لغت میں تلاش کریں۔"),
        w(51, 6, "School & Study", "Remember", "ri-mem-ber", "Verb", "یاد رکھنا", "To keep information in your memory.", "Write down new phrases so you remember them.", "نئے جملے لکھ لیں تاکہ آپ انہیں یاد رکھ سکیں۔"),
        w(52, 6, "School & Study", "Confident", "kon-fi-dunt", "Adjective", "پُراعتماد", "Feeling sure about your own ability.", "Regular speaking practice makes students confident.", "باقاعدہ بولنے کی مشق طلبہ کو پُراعتماد بناتی ہے۔"),
        w(53, 6, "School & Study", "Question", "kwes-chun", "Noun", "سوال", "A sentence asked to get information.", "Always ask a question when you are unsure.", "جب آپ کو یقین نہ ہو تو ہمیشہ سوال پوچھیں۔"),
        w(54, 6, "School & Study", "Answer", "an-ser", "Noun / Verb", "جواب / جواب دینا", "A reply to a question.", "She gave the right answer in the quiz.", "اس نے کوئز میں درست جواب دیا۔"),
        w(55, 6, "School & Study", "Success", "suk-ses", "Noun", "کامیابی", "Achieving a goal you worked hard for.", "Hard work and patience lead to success.", "محنت اور صبر کامیابی کی طرف لے جاتے ہیں۔")
    )
}
