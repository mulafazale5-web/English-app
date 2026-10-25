package com.example.data.curriculum

import com.example.data.local.ConversationEntity

object ConversationsSeedPart1 {
    private fun c(
        id: Int,
        topic: String,
        titleEn: String,
        titleUr: String,
        turns: List<Triple<String, String, String>>,
        promptEn: String,
        promptUr: String
    ) = ConversationEntity(
        id = id,
        topic = topic,
        titleEn = titleEn,
        titleUr = titleUr,
        turnsPacked = turns.joinToString("||") { "${it.first}::${it.second}::${it.third}" },
        speakingPromptEn = promptEn,
        speakingPromptUr = promptUr
    )

    val conversations: List<ConversationEntity> = listOf(
        // Topic 1: Introducing yourself (Dialogues 1–3)
        c(
            1, "Introducing yourself",
            "Meeting a New Classmate", "نئے ہم جماعت سے پہلی ملاقات",
            listOf(
                Triple("Hassan", "Hello! My name is Hassan. Is this seat free?", "ہیلو! میرا نام حسن ہے۔ کیا یہ نشست خالی ہے؟"),
                Triple("Bilal", "Hi Hassan! Yes, please sit down. I am Bilal.", "ہائے حسن! جی ہاں، براہ کرم بیٹھ جائیں۔ میں بلال ہوں۔"),
                Triple("Hassan", "Nice to meet you, Bilal. Where are you from?", "آپ سے مل کر خوشی ہوئی بلال۔ آپ کا تعلق کہاں سے ہے؟"),
                Triple("Bilal", "I am from Faisalabad, and this is my first week here.", "میرا تعلق فیصل آباد سے ہے اور یہاں میرا پہلا ہفتہ ہے۔"),
                Triple("Hassan", "Welcome! I am from Rawalpindi. Let's practice English together.", "خوش آمدید! میں راولپنڈی سے ہوں۔ آئیے مل کر انگریزی کی مشق کریں۔"),
                Triple("Bilal", "That sounds great! Thank you for being so friendly.", "یہ تو بہت اچھی بات ہے! اتنی اپنائیت دکھانے کا شکریہ۔")
            ),
            "Hello! My name is Hassan, and I enjoy learning English every day.",
            "ہیلو! میرا نام حسن ہے، اور مجھے ہر روز انگریزی سیکھنا پسند ہے۔"
        ),
        c(
            2, "Introducing yourself",
            "Greeting a New Neighbor", "نئے پڑوسی سے تعارف",
            listOf(
                Triple("Amina", "Good evening! Welcome to our street. I am Amina from next door.", "شام بخیر! ہماری گلی میں خوش آمدید۔ میں ساتھ والے گھر سے آمنہ ہوں۔"),
                Triple("Sana", "Good evening, Amina! Thank you so much. My name is Sana.", "شام بخیر آمنہ! آپ کا بے حد شکریہ۔ میرا نام ثنا ہے۔"),
                Triple("Amina", "Did your family move in today?", "کیا آپ کی فیملی آج ہی یہاں منتقل ہوئی ہے؟"),
                Triple("Sana", "Yes, we arrived this morning. We are still unpacking boxes.", "جی ہاں، ہم آج صبح پہنچے ہیں۔ ہم ابھی سامان کھول رہے ہیں۔"),
                Triple("Amina", "Here is some warm tea for your family. Please don't hesitate to ask for help.", "آپ کے گھر والوں کے لیے یہ گرم چائے ہے۔ مدد مانگنے میں بالکل ہچکچاہٹ نہ کریں۔"),
                Triple("Sana", "You are very generous! I really appreciate your kindness.", "آپ بہت سخی ہیں! میں واقعی آپ کی مہربانی کی قدر کرتی ہوں۔")
            ),
            "Good evening! Welcome to our neighborhood. Please let us know if you need help.",
            "شام بخیر! ہمارے محلے میں خوش آمدید۔ اگر مدد درکار ہو تو ضرور بتائیے۔"
        ),
        c(
            3, "Introducing yourself",
            "Introducing Yourself at a Workshop", "ورکشاپ میں اپنا تعارف کرانا",
            listOf(
                Triple("Coach", "Welcome everyone! Could you briefly introduce yourself?", "سب کو خوش آمدید! کیا آپ مختصراً اپنا تعارف کرا سکتے ہیں؟"),
                Triple("Zain", "Good morning! My name is Zain, and I am a college student.", "صبح بخیر! میرا نام زین ہے اور میں کالج کا طالب علم ہوں۔"),
                Triple("Coach", "Nice to meet you, Zain. Why did you join this workshop?", "آپ سے مل کر خوشی ہوئی زین۔ آپ نے یہ ورکشاپ کیوں جوائن کی؟"),
                Triple("Zain", "I want to build my confidence in spoken English.", "میں انگریزی بولنے میں اپنا اعتماد بڑھانا چاہتا ہوں۔"),
                Triple("Coach", "What do you enjoy doing in your free time?", "آپ اپنے فارغ وقت میں کیا کرنا پسند کرتے ہیں؟"),
                Triple("Zain", "In my free time, I play cricket and read technology blogs.", "فارغ وقت میں میں کرکٹ کھیلتا ہوں اور ٹیکنالوجی مضامین پڑھتا ہوں۔")
            ),
            "My name is Zain, and I want to improve my spoken English step by step.",
            "میرا نام زین ہے، اور میں قدم بہ قدم اپنی انگریزی بہتر بنانا چاہتا ہوں۔"
        ),

        // Topic 2: Talking to friends (Dialogues 4–6)
        c(
            4, "Talking to friends",
            "Catching Up After Exams", "امتحانات کے بعد دوست سے ملاقات",
            listOf(
                Triple("Umar", "Hey Saad, long time no see! How were your final exams?", "ارے سعد، بہت دنوں بعد ملے! تمہارے سالانہ امتحانات کیسے ہوئے؟"),
                Triple("Saad", "Hi Umar! Everything went well, thank goodness. What about yours?", "ہائے عمر! شکر ہے سب اچھا ہو گیا۔ تمہارے پرچے کیسے رہے؟"),
                Triple("Umar", "Mathematics was a bit tough, but the English paper was a piece of cake.", "ریاضی تھوڑا مشکل تھا، مگر انگریزی کا پرچہ بہت آسان رہا۔"),
                Triple("Saad", "Are you free this Saturday evening?", "کیا تم اس ہفتے کی شام کو فارغ ہو؟"),
                Triple("Umar", "Yes, I am! Let's go to the park and have tea together.", "ہاں بالکل! چلو پارک چلتے ہیں اور اکٹھے چائے پیتے ہیں۔"),
                Triple("Saad", "Perfect! I will meet you at five o'clock.", "بہترین! میں تم سے پانچ بجے ملوں گا۔")
            ),
            "Hey friend, long time no see! Let's meet at the park at five o'clock.",
            "ارے دوست، بہت دنوں بعد ملے! چلو پانچ بجے پارک میں ملتے ہیں۔"
        ),
        c(
            5, "Talking to friends",
            "Checking on a Sick Friend", "بیمار دوست کی خیریت پوچھنا",
            listOf(
                Triple("Hira", "Hi Maryam, I missed you in class today. Are you alright?", "ہائے مریم، آج کلاس میں تمہاری کمی محسوس ہوئی۔ کیا تم ٹھیک ہو؟"),
                Triple("Maryam", "Hi Hira. I stayed home because I felt a bit under the weather.", "ہائے حرا۔ میں گھر پر رہی کیونکہ میری طبیعت کچھ ناساز تھی۔"),
                Triple("Hira", "Oh, I am sorry to hear that. Did you take medicine?", "اوہ، یہ سن کر افسوس ہوا۔ کیا تم نے دوا لی؟"),
                Triple("Maryam", "Yes, I took medicine and rested. I am feeling much better now.", "ہاں، میں نے دوا لی اور آرام کیا۔ اب میں کافی بہتر محسوس کر رہی ہوں۔"),
                Triple("Hira", "Don't worry about today's notes—I wrote everything clearly for you.", "آج کے نوٹس کی فکر نہ کرو—میں نے تمہارے لیے سب صاف لکھ لیا ہے۔"),
                Triple("Maryam", "Thank you so much, Hira! You are a true friend.", "بے حد شکریہ حرا! تم واقعی ایک سچی دوست ہو۔")
            ),
            "I am feeling much better today after taking rest and warm soup.",
            "آرام کرنے اور گرم سوپ پینے کے بعد آج میں کافی بہتر محسوس کر رہا ہوں۔"
        ),
        c(
            6, "Talking to friends",
            "Sharing Daily Study Habits", "پڑھائی کی عادات پر دوستانہ گفتگو",
            listOf(
                Triple("Kamran", "Your English pronunciation sounds much clearer lately, Danish!", "دانش، آج کل تمہارا انگریزی تلفظ بہت صاف سنائی دیتا ہے!"),
                Triple("Danish", "Thanks Kamran! I practice for fifteen minutes every morning.", "شکریہ کامران! میں ہر صبح پندرہ منٹ مشق کرتا ہوں۔"),
                Triple("Kamran", "Really? How do you remember new words without forgetting?", "واقعی؟ تم نئے الفاظ بھولے بغیر کیسے یاد رکھتے ہو؟"),
                Triple("Danish", "I read natural sentences aloud instead of memorizing single words.", "میں اکیلے الفاظ رٹنے کے بجائے پورے جملے بلند آواز سے پڑھتا ہوں۔"),
                Triple("Kamran", "That makes sense! Can I join your daily practice challenge?", "یہ بات سمجھ میں آتی ہے! کیا میں تمہارے روزانہ کے چیلنج میں شامل ہو سکتا ہوں؟"),
                Triple("Danish", "Of course! Practicing with a friend makes learning twice as fun.", "بالکل! دوست کے ساتھ مشق کرنے سے سیکھنے کا مزہ دوگنا ہو جاتا ہے۔")
            ),
            "I read natural English sentences aloud every morning to build confidence.",
            "میں اعتماد بڑھانے کے لیے ہر صبح انگریزی جملے بلند آواز سے پڑھتا ہوں۔"
        ),

        // Topic 3: School conversations (Dialogues 7–9)
        c(
            7, "School conversations",
            "Asking the Teacher to Explain", "استاد سے دوبارہ سمجھانے کی درخواست",
            listOf(
                Triple("Student", "Excuse me Teacher, may I ask a quick question?", "معاف کیجیے استاد محترم، کیا میں ایک مختصر سوال پوچھ سکتا ہوں؟"),
                Triple("Teacher", "Yes, certainly, Adeel. Which part was confusing?", "جی بالکل عدیل۔ کون سا حصہ سمجھنے میں مشکل لگا؟"),
                Triple("Student", "I understood 'since' and 'for', but could you give one more example?", "مجھے 'since' اور 'for' سمجھ آیا، مگر کیا آپ ایک اور مثال دے سکتے ہیں؟"),
                Triple("Teacher", "Sure! Use 'since Monday' for a starting point, and 'for three days' for duration.", "ضرور! آغاز کے وقت کے لیے 'since Monday' اور مدت کے لیے 'for three days' لگائیں۔"),
                Triple("Student", "Oh, now it makes sense! Let me write that down in my notebook.", "اوہ، اب بات سمجھ آ گئی! مجھے یہ اپنی نوٹ بک میں لکھ لینے دیں۔"),
                Triple("Teacher", "Good job asking! Never hesitate to raise your hand.", "پوچھنے پر شاباش! ہاتھ اٹھانے میں کبھی ہچکچاہٹ نہ کریں۔")
            ),
            "Excuse me Teacher, could you explain this grammar rule with an example?",
            "معاف کیجیے استاد محترم، کیا آپ ایک مثال کے ساتھ یہ اصول سمجھا سکتے ہیں؟"
        ),
        c(
            8, "School conversations",
            "Borrowing a Book at the Library", "لائبریری سے کتاب حاصل کرنا",
            listOf(
                Triple("Nadia", "Good morning! I am looking for beginner English storybooks.", "صبح بخیر! میں ابتدائی انگریزی کہانیوں کی کتابیں تلاش کر رہی ہوں۔"),
                Triple("Librarian", "Good morning! They are on the second shelf near the window.", "صبح بخیر! وہ کھڑکی کے قریب دوسری الماری پر رکھی ہیں۔"),
                Triple("Nadia", "How many books can I borrow at one time?", "میں ایک وقت میں کتنی کتابیں لے سکتی ہوں؟"),
                Triple("Librarian", "You can borrow two books for fourteen days with your student card.", "آپ اپنے اسٹوڈنٹ کارڈ پر چودہ دن کے لیے دو کتابیں لے سکتی ہیں۔"),
                Triple("Nadia", "Great! Here is my student card and these two short novels.", "بہت خوب! یہ رہا میرا کارڈ اور یہ دو مختصر ناول۔"),
                Triple("Librarian", "Please return them by next Tuesday. Happy reading!", "براہ کرم انہیں اگلے منگل تک واپس کر دیں۔ مطالعہ مبارک ہو!")
            ),
            "Good morning! How many books can I borrow with my student card?",
            "صبح بخیر! میں اپنے اسٹوڈنٹ کارڈ پر کتنی کتابیں لے سکتا ہوں؟"
        ),
        c(
            9, "School conversations",
            "Planning a Group Science Project", "سائنس پروجیکٹ کی گروپ منصوبہ بندی",
            listOf(
                Triple("Farhan", "Our science presentation is next Friday. How should we divide the work?", "ہماری سائنس پریزنٹیشن اگلے جمعہ کو ہے۔ کام کیسے بانٹنا چاہیے؟"),
                Triple("Ayesha", "I can draw the clean diagrams and prepare the chart.", "میں صاف خاکے بنا سکتی ہوں اور چارٹ تیار کر سکتی ہوں۔"),
                Triple("Farhan", "That is great! I will write the summary points in simple English.", "یہ بہت اچھا ہے! میں آسان انگریزی میں اہم نکات لکھ لوں گا۔"),
                Triple("Ayesha", "When should we rehearse speaking our parts aloud?", "ہمیں اپنے حصے بولنے کی ریہرسل کب کرنی چاہیے؟"),
                Triple("Farhan", "Let's meet in the library on Wednesday after lunch.", "آؤ بدھ کو دوپہر کے کھانے کے بعد لائبریری میں ملتے ہیں۔"),
                Triple("Ayesha", "Agreed! If we practice together, we will feel calm on stage.", "متفق! اگر ہم مل کر مشق کریں گے تو اسٹیج پر پُرسکون رہیں گے۔")
            ),
            "Let's divide the project tasks fairly and practice our presentation together.",
            "آئیے پروجیکٹ کے کام منصفانہ بانٹ لیں اور مل کر پریزنٹیشن کی مشق کریں۔"
        ),

        // Topic 4: Shopping (Dialogues 10–11 here, 12 in Part 2)
        c(
            10, "Shopping",
            "Buying a Winter Jacket", "سردیوں کی جیکٹ خریدنا",
            listOf(
                Triple("Shopkeeper", "Welcome! How can I help you today?", "خوش آمدید! آج میں آپ کی کیا مدد کر سکتا ہوں؟"),
                Triple("Customer", "Hello! I am looking for a warm navy blue jacket.", "ہیلو! میں ایک گرم گہرے نیلے رنگ کی جیکٹ تلاش کر رہا ہوں۔"),
                Triple("Shopkeeper", "We have this cotton-lined jacket. What size do you wear?", "ہمارے پاس یہ سوتی استر والی جیکٹ ہے۔ آپ کون سا سائز پہنتے ہیں؟"),
                Triple("Customer", "I usually wear medium. How much does this cost?", "میں عموماً میڈیم پہنتا ہوں۔ اس کی قیمت کتنی ہے؟"),
                Triple("Shopkeeper", "It is three thousand rupees, and there is a ten percent winter discount.", "یہ تین ہزار روپے کی ہے، اور اس پر دس فیصد رعایت بھی ہے۔"),
                Triple("Customer", "The quality feels comfortable and affordable. I will take this one!", "معیار آرام دہ اور قیمت مناسب لگ رہی ہے۔ میں یہ لے لوں گا!")
            ),
            "Excuse me, do you have this blue jacket in medium size?",
            "معاف کیجیے، کیا یہ نیلی جیکٹ آپ کے پاس درمیانے سائز میں ہے؟"
        ),
        c(
            11, "Shopping",
            "At the Grocery & Fruit Market", "پھلوں اور سبزیوں کی خریداری",
            listOf(
                Triple("Buyer", "Good morning! Are these apples fresh today?", "صبح بخیر! کیا یہ سیب آج کے تازہ ہیں؟"),
                Triple("Vendor", "Yes brother, they arrived from Swat this morning.", "جی بھائی، یہ آج صبح ہی سوات سے آئے ہیں۔"),
                Triple("Buyer", "What is the price per kilogram?", "فی کلو قیمت کیا ہے؟"),
                Triple("Vendor", "Two hundred and fifty rupees per kilo.", "دو سو پچاس روپے فی کلو۔"),
                Triple("Buyer", "Please pack two kilos of apples and one kilo of tomatoes.", "براہ کرم دو کلو سیب اور ایک کلو ٹماٹر تول دیں۔"),
                Triple("Vendor", "Here is your bag and your fifty rupees change. Thank you!", "یہ لیجیے آپ کا تھیلا اور پچاس روپے بقایا۔ شکریہ!")
            ),
            "Are these fruits fresh today, and what is the price per kilogram?",
            "کیا یہ پھل آج تازہ ہیں، اور فی کلو قیمت کیا ہے؟"
        )
    )
}
