package com.example.data.curriculum

import com.example.data.local.ConversationEntity

object ConversationsSeedPart2 {
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
        // Topic 4 cont.: Shopping (Dialogue 12)
        c(
            12, "Shopping",
            "Exchanging Headphones with a Receipt", "رسید کے ساتھ ہیڈ فون تبدیل کرانا",
            listOf(
                Triple("Customer", "Hello! I bought these headphones yesterday, but the left speaker has no sound.", "ہیلو! میں نے کل یہ ہیڈ فون خریدے تھے، مگر بائیں اسپیکر میں آواز نہیں ہے۔"),
                Triple("Cashier", "I am sorry for the trouble. Do you have the receipt with you?", "زحمت کے لیے معذرت خواہ ہوں۔ کیا آپ کے پاس رسید موجود ہے؟"),
                Triple("Customer", "Yes, here is the original box and the payment receipt.", "جی ہاں، یہ رہا اصلی ڈبہ اور ادائیگی کی رسید۔"),
                Triple("Cashier", "Thank you. Let me test a new pair for you right now.", "شکریہ۔ میں ابھی آپ کے لیے نیا جوڑا چیک کر دیتا ہوں۔"),
                Triple("Customer", "Both sides sound crystal clear on this pair. Thank you!", "اس جوڑے میں دونوں طرف آواز بالکل صاف ہے۔ شکریہ!"),
                Triple("Cashier", "You are welcome! It also carries a six-month warranty.", "خوش آمدید! اس کے ساتھ چھ ماہ کی وارنٹی بھی ہے۔")
            ),
            "Here is my receipt; could I exchange this item because it is not working?",
            "یہ رہی میری رسید؛ کیا میں یہ چیز بدل سکتا ہوں کیونکہ یہ کام نہیں کر رہی؟"
        ),

        // Topic 5: Asking for directions (Dialogues 13–15)
        c(
            13, "Asking for directions",
            "Finding the Metro Bus Station", "میٹرو بس اسٹیشن کا راستہ پوچھنا",
            listOf(
                Triple("Traveler", "Excuse me, could you show me the way to the metro station?", "معاف کیجیے، کیا آپ مجھے میٹرو اسٹیشن کا راستہ بتا سکتے ہیں؟"),
                Triple("Local", "Sure! Walk straight along this road until you reach the traffic light.", "ضرور! اس سڑک پر سیدھا چلتے جائیں یہاں تک کہ ٹریفک سگنل آ جائے۔"),
                Triple("Traveler", "Should I turn left or right at the traffic light?", "ٹریفک سگنل سے مجھے بائیں مڑنا چاہیے یا دائیں؟"),
                Triple("Local", "Turn left at the corner, and you will see the pedestrian bridge.", "موڑ سے بائیں مڑیں، تو آپ کو پیدل چلنے والوں کا پُل نظر آئے گا۔"),
                Triple("Traveler", "Is it within walking distance from here?", "کیا یہاں سے وہاں پیدل جایا جا سکتا ہے؟"),
                Triple("Local", "Yes, it takes only about seven minutes on foot.", "جی ہاں، پیدل صرف سات منٹ لگتے ہیں۔")
            ),
            "Excuse me, is the metro station within walking distance from here?",
            "معاف کیجیے، کیا میٹرو اسٹیشن یہاں سے پیدل چلنے کے فاصلے پر ہے؟"
        ),
        c(
            14, "Asking for directions",
            "Finding a Pharmacy Nearby", "قریبی دواخانہ تلاش کرنا",
            listOf(
                Triple("Visitor", "Pardon me, is there a pharmacy open near this hospital?", "معاف کیجیے گا، کیا اس اسپتال کے قریب کوئی دواخانہ کھلا ہے؟"),
                Triple("Guard", "Yes, there is a large medical store right across the street.", "جی ہاں، سڑک کے بالکل اس پار ایک بڑا میڈیکل اسٹور ہے۔"),
                Triple("Visitor", "How can I cross this busy road safely?", "میں یہ مصروف سڑک محفوظ طریقے سے کیسے پار کروں؟"),
                Triple("Guard", "Please use the zebra crossing near the main gate.", "براہ کرم مرکزی دروازے کے قریب زیبرا کراسنگ استعمال کریں۔"),
                Triple("Visitor", "Thank you very much for your clear guidance!", "آپ کی واضح رہنمائی کا بہت شکریہ!"),
                Triple("Guard", "No problem at all. Stay safe!", "کوئی بات نہیں۔ محفوظ رہیں!")
            ),
            "There is a pharmacy right across the street near the main gate.",
            "مرکزی دروازے کے قریب سڑک کے بالکل اس پار ایک فارمیسی موجود ہے۔"
        ),
        c(
            15, "Asking for directions",
            "Checking the Right Bus Route", "صحیح بس روٹ کی تصدیق کرنا",
            listOf(
                Triple("Passenger", "Excuse me driver, does this bus stop near Punjab University?", "معاف کیجیے ڈرائیور صاحب، کیا یہ بس پنجاب یونیورسٹی کے قریب رکتی ہے؟"),
                Triple("Driver", "No, this route goes to the railway station.", "نہیں، یہ روٹ ریلوے اسٹیشن کی طرف جاتا ہے۔"),
                Triple("Passenger", "Oh! Which bus should I take for the university campus?", "اوہ! یونیورسٹی کیمپس کے لیے مجھے کون سی بس لینی چاہیے؟"),
                Triple("Driver", "Take Bus Number Twelve from the opposite platform.", "سامنے والے پلیٹ فارم سے بارہ نمبر بس لیں۔"),
                Triple("Passenger", "How often does Bus Number Twelve arrive?", "بارہ نمبر بس کتنی دیر بعد آتی ہے؟"),
                Triple("Driver", "Every ten minutes. Look, one is arriving right now!", "ہر دس منٹ بعد۔ دیکھیے، ایک بس ابھی آ رہی ہے!")
            ),
            "Excuse me, which bus should I take to reach the university campus?",
            "معاف کیجیے، یونیورسٹی کیمپس پہنچنے کے لیے مجھے کون سی بس لینی چاہیے؟"
        ),

        // Topic 6: Job interviews (Dialogues 16–18)
        c(
            16, "Job interviews",
            "Tell Me About Yourself (Fresh Graduate)", "ملازمت کے انٹرویو میں اپنا تعارف",
            listOf(
                Triple("Manager", "Good morning, Hamza! Thank you for coming on time. Tell me about yourself.", "صبح بخیر حمزہ! وقت پر آنے کا شکریہ۔ اپنے بارے میں کچھ بتائیے۔"),
                Triple("Hamza", "Good morning! Thank you for inviting me today. I recently graduated in Commerce.", "صبح بخیر! آج مدعو کرنے کا شکریہ۔ میں نے حال ہی میں کامرس میں گریجویشن مکمل کی ہے۔"),
                Triple("Manager", "What do you consider your greatest strength at work?", "آپ کام میں اپنی سب سے بڑی خوبی کس چیز کو سمجھتے ہیں؟"),
                Triple("Hamza", "I am punctual, responsible, and I pay close attention to details.", "میں وقت کا پابند اور ذمہ دار ہوں، اور باریکیوں پر گہری توجہ دیتا ہوں۔"),
                Triple("Manager", "Do you prefer working alone or in a team?", "آپ اکیلے کام کرنا پسند کرتے ہیں یا ٹیم کے ساتھ؟"),
                Triple("Hamza", "I can focus independently, and I also work very well in a team.", "میں انفرادی توجہ سے بھی کام کر سکتا ہوں اور ٹیم کے ساتھ مل کر بھی۔")
            ),
            "Thank you for inviting me today. I am punctual, responsible, and eager to learn.",
            "آج مدعو کرنے کا شکریہ۔ میں وقت کا پابند، ذمہ دار اور سیکھنے کا شوقین ہوں۔"
        ),
        c(
            17, "Job interviews",
            "Discussing Experience and Challenges", "تجربے اور مشکل صورتحال پر گفتگو",
            listOf(
                Triple("Interviewer", "Have you ever handled customer questions before, Rabia?", "رابعہ، کیا آپ نے پہلے کبھی گاہکوں کے سوالات ہینڈل کیے ہیں؟"),
                Triple("Rabia", "Yes, I helped at my uncle's bookstore for six months during college.", "جی ہاں، کالج کے دوران میں نے چھ ماہ اپنے انکل کی کتابوں کی دکان پر مدد کی۔"),
                Triple("Interviewer", "How do you react when a customer feels upset?", "جب کوئی گاہک پریشان یا ناراض ہو تو آپ کیا ردعمل دیتی ہیں؟"),
                Triple("Rabia", "I listen patiently, stay calm, and focus on finding a quick solution.", "میں صبر سے بات سنتی ہوں، پُرسکون رہتی ہوں اور فوری حل نکالنے پر توجہ دیتی ہوں۔"),
                Triple("Interviewer", "When would you be able to join our office?", "آپ ہمارے دفتر میں کب سے کام شروع کر سکیں گی؟"),
                Triple("Rabia", "I can start from next Monday. I look forward to hearing from you!", "میں اگلے پیر سے کام شروع کر سکتی ہوں۔ مجھے آپ کے جواب کا انتظار رہے گا!")
            ),
            "I listen patiently, stay calm, and focus on finding a practical solution.",
            "میں صبر سے بات سنتا ہوں، پُرسکون رہتا ہوں اور عملی حل ڈھونڈنے پر توجہ دیتا ہوں۔"
        ),
        c(
            18, "Job interviews",
            "Junior Software Intern Interview", "جونیئر سافٹ ویئر انٹرن شپ انٹرویو",
            listOf(
                Triple("Tech Lead", "Welcome Usman! What motivated you to apply for this Android internship?", "خوش آمدید عثمان! اینڈرائیڈ انٹرن شپ کے لیے درخواست دینے کا شوق کیسے پیدا ہوا؟"),
                Triple("Usman", "I love building helpful mobile apps that work smoothly offline.", "مجھے ایسی مفید موبائل ایپس بنانا پسند ہے جو آف لائن بھی روانی سے چلیں۔"),
                Triple("Tech Lead", "Which programming language have you practiced the most?", "آپ نے کس پروگرامنگ زبان کی سب سے زیادہ مشق کی ہے؟"),
                Triple("Usman", "I have built three beginner projects using Kotlin and Jetpack Compose.", "میں نے کوٹلن اور جیٹ پیک کمپوز استعمال کرتے ہوئے تین ابتدائی پروجیکٹس بنائے ہیں۔"),
                Triple("Tech Lead", "What do you do when you face an unfamiliar coding error?", "جب کوڈ میں کوئی نیا ایرر آ جائے تو آپ کیا کرتے ہیں؟"),
                Triple("Usman", "I read the error message carefully and break the problem into small steps.", "میں ایرر کا پیغام دھیان سے پڑھتا ہوں اور مسئلے کو چھوٹے حصوں میں تقسیم کرتا ہوں۔")
            ),
            "I have built beginner Android apps in Kotlin, and I am a quick learner.",
            "میں نے کوٹلن میں اینڈرائیڈ ایپس بنائی ہیں، اور میں جلدی سیکھنے والا طالب علم ہوں۔"
        ),

        // Topic 7: Programming and technology (Dialogues 19–21)
        c(
            19, "Programming and technology",
            "Fixing a Bug Together", "مل کر پروگرام کی خرابی (Bug) دور کرنا",
            listOf(
                Triple("Sara", "Hey Ali, do you have a minute to look at my Kotlin code?", "ہائے علی، کیا تمہارے پاس میرا کوٹلن کوڈ دیکھنے کے لیے ایک منٹ ہے؟"),
                Triple("Ali", "Sure Sara! What error are you seeing on the screen?", "ضرور سارہ! تمہیں اسکرین پر کیا خرابی نظر آ رہی ہے؟"),
                Triple("Sara", "When the app closes, the user's quiz score disappears.", "جب ایپ بند ہوتی ہے تو صارف کا کوئز اسکور غائب ہو جاتا ہے۔"),
                Triple("Ali", "Aha! You stored the score in memory instead of saving it in Room database.", "اوہ اچھا! تم نے اسکور کو روم ڈیٹا بیس میں محفوظ کرنے کے بجائے عارضی میموری میں رکھا ہے۔"),
                Triple("Sara", "Now it makes sense! Let me add a suspend insert function in the DAO.", "اب سمجھ آیا! میں ابھی DAO میں ڈیٹا محفوظ کرنے کا فنکشن شامل کرتی ہوں۔"),
                Triple("Ali", "Awesome! Let's test this feature again after compiling.", "شاندار! کمپائل کرنے کے بعد آؤ اس فیچر کو دوبارہ ٹیسٹ کریں۔")
            ),
            "Let's save user progress in the local database so it never disappears.",
            "آئیے صارف کی پیش رفت مقامی ڈیٹا بیس میں محفوظ کریں تاکہ یہ کبھی ضائع نہ ہو۔"
        ),
        c(
            20, "Programming and technology",
            "Helping Parents Use a Smartphone", "والدین کو اسمارٹ فون استعمال کرنا سکھانا",
            listOf(
                Triple("Father", "Beta, the text on my phone screen looks too small for my eyes.", "بیٹا، میرے فون کی اسکرین پر لکھائی میری آنکھوں کے لیے بہت چھوٹی لگ رہی ہے۔"),
                Triple("Daughter", "Let me help you, Abu! We can make the font size larger in Settings.", "میں مدد کرتی ہوں ابو! ہم سیٹنگز میں جا کر لکھائی کا سائز بڑا کر سکتے ہیں۔"),
                Triple("Father", "Can I also switch to a dark background at night?", "کیا میں رات کے وقت گہرے پس منظر (ڈارک تھیم) پر بھی لا سکتا ہوں؟"),
                Triple("Daughter", "Yes! Just tap Dark Theme right here—it is much gentler on the eyes.", "جی ہاں! یہاں ڈارک تھیم پر ٹیپ کریں—یہ آنکھوں کے لیے بہت آرام دہ ہے۔"),
                Triple("Father", "Wonderful! Does this dictionary app use mobile data?", "بہت خوب! کیا یہ ڈکشنری ایپ موبائل انٹرنیٹ استعمال کرتی ہے؟"),
                Triple("Daughter", "Not at all! Everything is stored on your device, so it works offline.", "بالکل نہیں! سب کچھ آپ کے فون میں محفوظ ہے، اس لیے یہ آف لائن کام کرتی ہے۔")
            ),
            "You can increase the font size and switch themes anytime in Settings.",
            "آپ سیٹنگز میں کسی بھی وقت لکھائی کا سائز بڑھا سکتے ہیں اور تھیم بدل سکتے ہیں۔"
        ),
        c(
            21, "Programming and technology",
            "Designing an Accessible Learning App", "سب کے لیے آسان تعلیمی ایپ ڈیزائن کرنا",
            listOf(
                Triple("Designer", "Who are we designing this English learning app for?", "ہم یہ انگریزی سیکھنے والی ایپ کن لوگوں کے لیے ڈیزائن کر رہے ہیں؟"),
                Triple("Developer", "We are building it for Urdu-speaking beginners who may not always have internet.", "ہم اسے ان اردو بولنے والے ابتدائی طلبہ کے لیے بنا رہے ہیں جن کے پاس ہمیشہ انٹرنیٹ نہیں ہوتا۔"),
                Triple("Designer", "Then we must keep the navigation simple and display Urdu clearly right-to-left.", "پھر ہمیں نیویگیشن سادہ رکھنی چاہیے اور اردو کو دائیں سے بائیں صاف دکھانا چاہیے۔"),
                Triple("Developer", "I completely agree. I also added Android Text-to-Speech so students can hear every sentence.", "میں مکمل متفق ہوں۔ میں نے اینڈرائیڈ ٹیکسٹ ٹو اسپیچ بھی شامل کیا ہے تاکہ طلبہ ہر جملہ سن سکیں۔"),
                Triple("Designer", "What if a device doesn't have an offline speech recognition engine installed?", "اگر کسی فون میں آف لائن آواز پہچاننے کا سسٹم انسٹال نہ ہو تو کیا ہوگا؟"),
                Triple("Developer", "The app checks honestly and explains how to enable offline voice packs instead of using internet secretly.", "ایپ ایمانداری سے چیک کرتی ہے اور خفیہ انٹرنیٹ استعمال کرنے کے بجائے آف لائن پیک انسٹال کرنے کا طریقہ بتاتی ہے۔")
            ),
            "Keep the user interface simple, accessible, and honest for every student.",
            "ہر طالب علم کے لیے ایپ کا انٹرفیس سادہ، قابلِ رسائی اور ایماندار رکھیں۔"
        )
    )
}
