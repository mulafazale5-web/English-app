package com.example.data.curriculum

import com.example.data.local.PhraseItemEntity

object PhrasesSeedPart1 {
    private fun p(
        id: Int,
        day: Int,
        cat: String,
        en: String,
        ur: String,
        exp: String,
        exEn: String,
        exUr: String,
        idiom: Boolean = false
    ) = PhraseItemEntity(
        id = id,
        dayNumber = day,
        category = cat,
        english = en,
        urdu = ur,
        explanation = exp,
        exampleEn = exEn,
        exampleUr = exUr,
        isIdiom = idiom
    )

    val phrases: List<PhraseItemEntity> = listOf(
        // Day 1: Polite Everyday Expressions (1–11)
        p(1, 1, "Polite Expressions", "Nice to meet you.", "آپ سے مل کر خوشی ہوئی۔", "Said when meeting someone for the first time.", "Hello Ayesha, nice to meet you.", "ہیلو عائشہ، آپ سے مل کر خوشی ہوئی۔"),
        p(2, 1, "Polite Expressions", "How is your day going?", "آپ کا دن کیسا گزر رہا ہے؟", "A friendly way to ask how someone is doing today.", "Good afternoon! How is your day going?", "سہ پہر بخیر! آپ کا دن کیسا گزر رہا ہے؟"),
        p(3, 1, "Polite Expressions", "Thank you so much.", "آپ کا بے حد شکریہ۔", "Stronger and warmer than a simple thank you.", "Thank you so much for carrying my books.", "میری کتابیں اٹھانے کے لیے آپ کا بے حد شکریہ۔"),
        p(4, 1, "Polite Expressions", "You are very welcome.", "آپ کو خوش آمدید / کوئی بات نہیں۔", "Polite response when someone thanks you.", "Thank you for the tea! — You are very welcome.", "چائے کا شکریہ! — کوئی بات نہیں، خوشی ہوئی۔"),
        p(5, 1, "Polite Expressions", "Excuse me for a moment.", "ایک لمحے کے لیے معذرت چاہتا ہوں۔", "Used politely before stepping away briefly.", "Excuse me for a moment, I need to answer this call.", "ایک لمحے کے لیے معذرت، مجھے یہ کال سننی ہے۔"),
        p(6, 1, "Polite Expressions", "I really appreciate it.", "میں واقعی اس کی قدر کرتا ہوں۔", "Shows genuine gratitude for someone's effort.", "You waited for me—I really appreciate it.", "آپ نے میرا انتظار کیا، میں واقعی اس کی قدر کرتا ہوں۔"),
        p(7, 1, "Polite Expressions", "No problem at all.", "بالکل کوئی مسئلہ نہیں۔", "Reassures someone that helping or forgiving was easy.", "Sorry for the delay! — No problem at all.", "تاخیر کے لیے معذرت! — بالکل کوئی مسئلہ نہیں۔"),
        p(8, 1, "Polite Expressions", "After you, please.", "پہلے آپ تشریف لے جائیے۔", "Polite gesture letting someone walk through a door first.", "After you, please, the classroom door is open.", "پہلے آپ تشریف لے جائیے، کلاس کا دروازہ کھلا ہے۔"),
        p(9, 1, "Polite Expressions", "Have a wonderful day!", "آپ کا دن بہت اچھا گزرے!", "Warm goodbye wish when leaving someone.", "See you tomorrow, have a wonderful day!", "کل ملیں گے، آپ کا دن بہت اچھا گزرے!"),
        p(10, 1, "Polite Expressions", "Take care of yourself.", "اپنا خیال رکھیے گا۔", "Caring goodbye phrase for friends or family.", "Drive safely and take care of yourself.", "احتیاط سے گاڑی چلائیں اور اپنا خیال رکھیے گا۔"),
        p(11, 1, "Polite Expressions", "May I come in?", "کیا میں اندر آ سکتا ہوں؟", "Polite way to ask permission before entering a room.", "Good morning Sir, may I come in?", "صبح بخیر سر، کیا میں اندر آ سکتا ہوں؟"),

        // Day 2: Daily Small Talk (12–22)
        p(12, 2, "Daily Small Talk", "Long time no see!", "بہت دنوں بعد ملاقات ہوئی!", "Friendly greeting when you haven't seen someone for a while.", "Hey Tariq, long time no see!", "ارے طارق، بہت دنوں بعد ملاقات ہوئی!"),
        p(13, 2, "Daily Small Talk", "What have you been up to?", "آج کل آپ کیا کر رہے ہیں؟", "Casual way to ask about someone's recent life.", "Hi Hina! What have you been up to lately?", "ہائے حنا! آج کل آپ کیا کر رہی ہیں؟"),
        p(14, 2, "Daily Small Talk", "Everything is going well.", "سب کچھ ٹھیک چل رہا ہے۔", "Positive reply when someone asks how life or work is.", "Thanks for asking, everything is going well.", "پوچھنے کا شکریہ، سب کچھ ٹھیک چل رہا ہے۔"),
        p(15, 2, "Daily Small Talk", "Where are you from?", "آپ کا تعلق کہاں سے ہے؟", "Used to ask about someone's hometown or country.", "Where are you from originally? — I am from Multan.", "آپ کا تعلق کہاں سے ہے؟ — میں ملتان سے ہوں۔"),
        p(16, 2, "Daily Small Talk", "What do you do for work?", "آپ کیا کام کرتے ہیں؟", "Polite way to ask about someone's profession.", "What do you do for work? — I teach mathematics.", "آپ کیا کام کرتے ہیں؟ — میں ریاضی پڑھاتا ہوں۔"),
        p(17, 2, "Daily Small Talk", "In my free time...", "اپنے فارغ وقت میں۔۔۔", "Used to talk about hobbies you enjoy.", "In my free time, I enjoy reading English stories.", "اپنے فارغ وقت میں مجھے انگریزی کہانیاں پڑھنا پسند ہے۔"),
        p(18, 2, "Daily Small Talk", "That sounds great!", "یہ تو بہت اچھی بات لگ رہی ہے!", "Enthusiastic response to good news or an idea.", "Let's study at the park! — That sounds great!", "آؤ پارک میں پڑھتے ہیں! — یہ تو بہت اچھی بات ہے!"),
        p(19, 2, "Daily Small Talk", "I completely agree.", "میں مکمل طور پر متفق ہوں۔", "Shows strong agreement with someone's opinion.", "Health is more important than wealth. — I completely agree.", "صحت دولت سے زیادہ اہم ہے۔ — میں مکمل متفق ہوں۔"),
        p(20, 2, "Daily Small Talk", "Let's stay in touch.", "آئیے رابطے میں رہتے ہیں۔", "Said when parting so you continue talking in the future.", "It was lovely talking to you, let's stay in touch.", "آپ سے بات کر کے اچھا لگا، آئیے رابطے میں رہتے ہیں۔"),
        p(21, 2, "Daily Small Talk", "See you soon!", "جلد ملاقات ہوگی!", "Friendly farewell when you expect to meet again.", "I have to catch my bus now. See you soon!", "مجھے اب بس پکڑنی ہے۔ جلد ملاقات ہوگی!"),
        p(22, 2, "Daily Small Talk", "How is your family?", "آپ کے گھر والے کیسے ہیں؟", "Warm cultural question asking about someone's family.", "Hello Uncle, how is your family doing?", "السلام علیکم انکل، آپ کے گھر والے کیسے ہیں؟"),

        // Day 3: Classroom & English Learning (23–33)
        p(23, 3, "Classroom & Study", "Could you speak more slowly?", "کیا آپ تھوڑا آہستہ بول سکتے ہیں؟", "Helpful request when someone speaks English too fast.", "I am learning English; could you speak more slowly?", "میں انگریزی سیکھ رہا ہوں؛ کیا آپ تھوڑا آہستہ بول سکتے ہیں؟"),
        p(24, 3, "Classroom & Study", "Could you repeat that, please?", "براہ کرم کیا آپ وہ بات دہرا سکتے ہیں؟", "Polite way to ask someone to say something again.", "Sorry, I missed the last word. Could you repeat that, please?", "معاف کیجیے میں آخری لفظ نہیں سن سکا۔ کیا آپ دہرا سکتے ہیں؟"),
        p(25, 3, "Classroom & Study", "What does this word mean?", "اس لفظ کا کیا مطلب ہے؟", "Used to ask the meaning of an unfamiliar word.", "Teacher, what does this word mean in simple English?", "استاد محترم، آسان انگریزی میں اس لفظ کا کیا مطلب ہے؟"),
        p(26, 3, "Classroom & Study", "How do you pronounce this?", "آپ اس کا تلفظ کیسے ادا کرتے ہیں؟", "Used when you want to hear the right sound of a word.", "I can spell it, but how do you pronounce this?", "میں اس کے ہجے کر سکتا ہوں، مگر اس کا تلفظ کیسے کرتے ہیں؟"),
        p(27, 3, "Classroom & Study", "How do you spell that?", "اس کے ہجے (اسپیلنگ) کیا ہیں؟", "Used to ask for the letters of a name or word.", "Your surname is unique; how do you spell that?", "آپ کا خاندانی نام منفرد ہے؛ اس کے ہجے کیا ہیں؟"),
        p(28, 3, "Classroom & Study", "Can you give me an example?", "کیا آپ مجھے ایک مثال دے سکتے ہیں؟", "Great way to understand grammar rules in action.", "I understand the rule, but can you give me an example?", "مجھے اصول سمجھ آ گیا، لیکن کیا آپ ایک مثال دے سکتے ہیں؟"),
        p(29, 3, "Classroom & Study", "I am still learning English.", "میں ابھی انگریزی سیکھ رہا ہوں۔", "Helpful phrase that makes listeners patient and supportive.", "Please forgive small mistakes; I am still learning English.", "چھوٹی غلطیاں معاف کیجیے؛ میں ابھی انگریزی سیکھ رہا ہوں۔"),
        p(30, 3, "Classroom & Study", "Now it makes sense!", "اب بات سمجھ میں آ گئی!", "Said when a confusing topic suddenly becomes clear.", "Oh, we add 'es' here! Now it makes sense!", "اوہ، یہاں ہم 'es' لگاتے ہیں! اب بات سمجھ آ گئی!"),
        p(31, 3, "Classroom & Study", "Let me write that down.", "مجھے یہ لکھ لینے دیں۔", "Used when taking notes to remember something important.", "That is a useful phrase—let me write that down.", "یہ ایک مفید جملہ ہے—مجھے یہ لکھ لینے دیں۔"),
        p(32, 3, "Classroom & Study", "Is this sentence correct?", "کیا یہ جملہ درست ہے؟", "Used to check your grammar with a teacher or friend.", "I wrote my introduction; is this sentence correct?", "میں نے اپنا تعارف لکھا ہے؛ کیا یہ جملہ درست ہے؟"),
        p(33, 3, "Classroom & Study", "Let's practice together.", "آئیے مل کر مشق کریں۔", "Encourages a classmate or friend to practice dialogues.", "Speaking is easier with a partner—let's practice together.", "ساتھی کے ساتھ بولنا آسان ہے—آئیے مل کر مشق کریں۔"),

        // Day 4: Shopping & Bargaining (34–44)
        p(34, 4, "Shopping & Daily Errands", "How much does this cost?", "اس کی قیمت کتنی ہے؟", "Standard natural way to ask the price of an item.", "Excuse me, how much does this backpack cost?", "معاف کیجیے، اس بیگ کی قیمت کتنی ہے؟"),
        p(35, 4, "Shopping & Daily Errands", "I am just looking, thank you.", "شکریہ، میں ابھی صرف دیکھ رہا ہوں۔", "Polite reply to a salesperson when you aren't ready to buy.", "Can I help you? — I am just looking, thank you.", "کیا میں مدد کروں؟ — شکریہ، میں ابھی صرف دیکھ رہا ہوں۔"),
        p(36, 4, "Shopping & Daily Errands", "Do you have this in medium size?", "کیا یہ آپ کے پاس درمیانے سائز میں ہے؟", "Used when shopping for clothes or shoes.", "I like this blue shirt; do you have this in medium size?", "مجھے یہ نیلی قمیض پسند ہے؛ کیا یہ درمیانے سائز میں ہے؟"),
        p(37, 4, "Shopping & Daily Errands", "Can you offer a better price?", "کیا آپ کچھ مناسب قیمت لگا سکتے ہیں؟", "Polite way to ask for a discount in a market.", "I am buying two pairs; can you offer a better price?", "میں دو جوڑے خرید رہا ہوں؛ کیا آپ مناسب قیمت لگا سکتے ہیں؟"),
        p(38, 4, "Shopping & Daily Errands", "I will take this one.", "میں یہ والا لے لوں گا۔", "Said when you have decided to buy an item.", "The cotton quality is nice; I will take this one.", "سوتی کپڑے کا معیار اچھا ہے؛ میں یہ والا لے لوں گا۔"),
        p(39, 4, "Shopping & Daily Errands", "Can I pay in cash?", "کیا میں نقد ادائیگی کر سکتا ہوں؟", "Asks whether cash notes are accepted.", "The card machine is slow—can I pay in cash?", "کارڈ مشین سست ہے—کیا میں نقد ادائیگی کر سکتا ہوں؟"),
        p(40, 4, "Shopping & Daily Errands", "May I have the receipt, please?", "براہ کرم کیا مجھے رسید مل سکتی ہے؟", "Polite way to request your payment slip.", "Here is the money. May I have the receipt, please?", "یہ لیجیے پیسے۔ براہ کرم کیا مجھے رسید مل سکتی ہے؟"),
        p(41, 4, "Shopping & Daily Errands", "Where is the fitting room?", "کپڑے چیک کرنے کا کمرہ کہاں ہے؟", "Used in clothing stores before buying garments.", "Excuse me, where is the fitting room?", "معاف کیجیے، کپڑے بدل کر دیکھنے کا کمرہ کہاں ہے؟"),
        p(42, 4, "Shopping & Daily Errands", "Is there a warranty on this?", "کیا اس چیز کی کوئی گارنٹی ہے؟", "Important question when buying electronics or watches.", "Before I buy these headphones, is there a warranty on this?", "ہیڈ فون خریدنے سے پہلے، کیا اس کی کوئی وارنٹی ہے؟"),
        p(43, 4, "Shopping & Daily Errands", "What time do you close?", "آپ کی دکان کس وقت بند ہوتی ہے؟", "Asks a shop's closing hour.", "I will return in the evening; what time do you close?", "میں شام کو واپس آؤں گا؛ آپ کی دکان کب بند ہوتی ہے؟"),
        p(44, 4, "Shopping & Daily Errands", "Keep the change.", "بقایا پیسے آپ رکھ لیں۔", "Generous phrase when leaving a small tip.", "Here is five hundred rupees—keep the change.", "یہ پانچ سو روپے ہیں—بقایا پیسے آپ رکھ لیں۔"),

        // Day 5: Directions & Travel (45–55)
        p(45, 5, "Directions & Travel", "Could you show me the way?", "کیا آپ مجھے راستہ بتا سکتے ہیں؟", "Polite way to ask for directions on the street.", "I am new here; could you show me the way to the metro?", "میں یہاں نیا ہوں؛ کیا آپ میٹرو کا راستہ بتا سکتے ہیں؟"),
        p(46, 5, "Directions & Travel", "How far is it from here?", "یہاں سے وہ کتنی دور ہے؟", "Asks about the distance to a destination.", "How far is the hospital from here?", "اسپتال یہاں سے کتنی دور ہے؟"),
        p(47, 5, "Directions & Travel", "Turn left at the traffic light.", "ٹریفک سگنل سے بائیں مڑ جائیں۔", "Clear direction instruction.", "Go straight and turn left at the traffic light.", "سیدھا جائیں اور ٹریفک سگنل سے بائیں مڑ جائیں۔"),
        p(48, 5, "Directions & Travel", "It is right across the street.", "یہ بالکل سڑک کے اس پار ہے۔", "Means something is directly on the opposite side.", "The pharmacy is right across the street.", "فارمیسی بالکل سڑک کے اس پار ہے۔"),
        p(49, 5, "Directions & Travel", "Does this bus go downtown?", "کیا یہ بس شہر کے مرکز جاتی ہے؟", "Confirms a bus route before getting on.", "Excuse me driver, does this bus go downtown?", "معاف کیجیے ڈرائیور صاحب، کیا یہ بس شہر کے مرکز جاتی ہے؟"),
        p(50, 5, "Directions & Travel", "Please let me off at the next stop.", "براہ کرم مجھے اگلے اسٹاپ پر اتار دیں۔", "Useful phrase for buses, vans, or taxis.", "My college is nearby; please let me off at the next stop.", "میرا کالج قریب ہے؛ مجھے اگلے اسٹاپ پر اتار دیں۔"),
        p(51, 5, "Directions & Travel", "I think I took a wrong turn.", "میرا خیال ہے میں غلط موڑ مڑ گیا ہوں۔", "Used when you realize you went the wrong way.", "Wait a second, I think I took a wrong turn.", "ایک سیکنڈ رکیں، میرا خیال ہے میں غلط موڑ مڑ گیا ہوں۔"),
        p(52, 5, "Directions & Travel", "Is it within walking distance?", "کیا وہاں پیدل جایا جا سکتا ہے؟", "Checks if you can walk instead of taking transport.", "Is the museum within walking distance?", "کیا عجائب گھر پیدل چلنے کے فاصلے پر ہے؟"),
        p(53, 5, "Directions & Travel", "I am on my way.", "میں راستے میں ہوں۔", "Tells someone you have already left and are coming.", "Don't worry, I am on my way and will reach in ten minutes.", "فکر نہ کریں، میں راستے میں ہوں اور دس منٹ میں پہنچوں گا۔"),
        p(54, 5, "Directions & Travel", "Where can I find a taxi?", "مجھے ٹیکسی کہاں سے مل سکتی ہے؟", "Asks locals for a taxi stand.", "It is getting late; where can I find a taxi?", "دیر ہو رہی ہے؛ مجھے ٹیکسی کہاں سے مل سکتی ہے؟"),
        p(55, 5, "Directions & Travel", "Have a safe journey!", "آپ کا سفر محفوظ رہے!", "Warm wish for someone traveling.", "Call us when you reach—have a safe journey!", "پہنچ کر ہمیں فون کریں—آپ کا سفر محفوظ رہے!")
    )
}
