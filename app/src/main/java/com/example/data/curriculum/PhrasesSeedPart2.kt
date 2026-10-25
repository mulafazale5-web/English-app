package com.example.data.curriculum

import com.example.data.local.PhraseItemEntity

object PhrasesSeedPart2 {
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
        // Day 6: Asking for Help & Feelings (56–65)
        p(56, 6, "Asking for Help", "Could you give me a hand?", "کیا آپ میری تھوڑی مدد کر سکتے ہیں؟", "Natural friendly phrase asking for physical or practical help.", "This box is heavy; could you give me a hand?", "یہ ڈبہ بھاری ہے؛ کیا آپ میری تھوڑی مدد کر سکتے ہیں؟", idiom = true),
        p(57, 6, "Asking for Help", "Do you have a minute?", "کیا آپ کے پاس ایک منٹ کا وقت ہے؟", "Polite way to ask if someone is free to talk.", "Hi Nadia, do you have a minute to check my essay?", "ہائے نادیہ، کیا آپ کے پاس میرا مضمون دیکھنے کے لیے ایک منٹ ہے؟"),
        p(58, 6, "Asking for Help", "I need your advice on something.", "مجھے کسی بات پر آپ کے مشورے کی ضرورت ہے۔", "Used when asking someone wiser for guidance.", "Uncle, I need your advice on choosing my college subjects.", "انکل، مجھے کالج کے مضامین چننے پر آپ کے مشورے کی ضرورت ہے۔"),
        p(59, 6, "Asking for Help", "Please don't hesitate to ask.", "پوچھنے میں بالکل ہچکچاہٹ محسوس نہ کریں۔", "Warmly invites someone to ask for help anytime.", "If you need anything, please don't hesitate to ask.", "اگر کسی چیز کی ضرورت ہو تو پوچھنے میں ہچکچاہٹ نہ کریں۔"),
        p(60, 6, "Asking for Help", "Let me check and get back to you.", "مجھے چیک کر کے آپ کو بتانے دیں۔", "Professional way to say you will find the answer soon.", "I don't know the exact date; let me check and get back to you.", "مجھے حتمی تاریخ معلوم نہیں؛ چیک کر کے آپ کو بتاتا ہوں۔"),
        p(61, 6, "Asking for Help", "I am feeling much better today.", "میں آج کافی بہتر محسوس کر رہا ہوں۔", "Said when recovering from sickness or tiredness.", "The fever is gone; I am feeling much better today.", "بخار اتر گیا ہے؛ میں آج کافی بہتر محسوس کر رہا ہوں۔"),
        p(62, 6, "Asking for Help", "Don't lose hope.", "امید کا دامن ہاتھ سے نہ چھوڑیں۔", "Encouraging phrase for someone facing difficulty.", "Keep studying; don't lose hope after one low score.", "پڑھتے رہیں؛ ایک کم نمبر کے بعد امید نہ چھوڑیں۔"),
        p(63, 6, "Asking for Help", "Everything will work out fine.", "سب کچھ ٹھیک ہو جائے گا۔", "Comforting expression during stressful moments.", "Stay calm—everything will work out fine.", "پُرسکون رہیں—سب کچھ ٹھیک ہو جائے گا۔"),
        p(64, 6, "Asking for Help", "I didn't mean to interrupt.", "میرا مقصد بات کاٹنا نہیں تھا۔", "Polite apology if you spoke while someone else was talking.", "Sorry, I didn't mean to interrupt—please continue.", "معذرت، میرا مقصد بات کاٹنا نہیں تھا—براہ کرم جاری رکھیں۔"),
        p(65, 6, "Asking for Help", "Thank you for your patience.", "آپ کے صبر و تحمل کا شکریہ۔", "Polite way to thank someone instead of apologizing repeatedly.", "Thank you for your patience while I looked for the file.", "فائل ڈھونڈنے کے دوران آپ کے صبر کا شکریہ۔"),

        // Day 7: Job Interviews & Career (66–75)
        p(66, 7, "Job Interviews", "Thank you for inviting me today.", "آج مجھے بلانے کے لیے آپ کا شکریہ۔", "Great opening sentence at the start of an interview.", "Good morning! Thank you for inviting me today.", "صبح بخیر! آج مجھے مدعو کرنے کا شکریہ۔"),
        p(67, 7, "Job Interviews", "I am a quick learner.", "میں نئی چیزیں جلدی سیکھ لیتا ہوں۔", "Positive strength to share in beginner job interviews.", "Even as a fresh graduate, I am a quick learner.", "نیا گریجویٹ ہونے کے باوجود میں جلدی سیکھنے والا انسان ہوں۔"),
        p(68, 7, "Job Interviews", "I work well in a team.", "میں ٹیم کے ساتھ مل کر اچھا کام کرتا ہوں۔", "Shows employers that you cooperate well with others.", "During college projects, I learned that I work well in a team.", "کالج پروجیکٹس میں میں نے سیکھا کہ میں ٹیم میں اچھا کام کرتا ہوں۔"),
        p(69, 7, "Job Interviews", "My greatest strength is consistency.", "میری سب سے بڑی طاقت مستقل مزاجی ہے۔", "Clear way to describe your best professional quality.", "My greatest strength is consistency in completing daily tasks.", "میری سب سے بڑی طاقت روزمرہ کاموں میں مستقل مزاجی ہے۔"),
        p(70, 7, "Job Interviews", "I am eager to grow my skills.", "میں اپنی مہارتیں بڑھانے کا بے حد شوق رکھتا ہوں۔", "Shows motivation and positive attitude.", "I joined this internship because I am eager to grow my skills.", "میں نے یہ انٹرن شپ اس لیے لی کیونکہ میں ہنر بڑھانے کا شوقین ہوں۔"),
        p(71, 7, "Job Interviews", "I pay attention to small details.", "میں چھوٹی باریکیوں پر بھی توجہ دیتا ہوں۔", "Shows carefulness and high quality work.", "When checking accounts, I pay attention to small details.", "حساب کتاب چیک کرتے وقت میں چھوٹی باریکیوں پر توجہ دیتا ہوں۔"),
        p(72, 7, "Job Interviews", "Could you tell me about the daily role?", "کیا آپ مجھے روزمرہ کی ذمہ داریوں کے بارے میں بتا سکتے ہیں؟", "Smart question to ask the interviewer at the end.", "Could you tell me about the daily role of a junior trainee?", "کیا آپ جونیئر ٹرینی کی روزمرہ ذمہ داریوں کے بارے میں بتا سکتے ہیں؟"),
        p(73, 7, "Job Interviews", "I can start from next Monday.", "میں اگلے پیر سے کام شروع کر سکتا ہوں۔", "Clear answer when asked about your availability.", "If selected, I can start from next Monday.", "منتخب ہونے کی صورت میں، میں اگلے پیر سے کام شروع کر سکتا ہوں۔"),
        p(74, 7, "Job Interviews", "I handle deadlines responsibly.", "میں مقررہ وقت پر کام ذمہ داری سے مکمل کرتا ہوں۔", "Reassures managers that you finish tasks on time.", "Even under pressure, I handle deadlines responsibly.", "دباؤ میں بھی میں مقررہ وقت پر کام ذمہ داری سے مکمل کرتا ہوں۔"),
        p(75, 7, "Job Interviews", "I look forward to hearing from you.", "مجھے آپ کے جواب کا انتظار رہے گا۔", "Polite closing sentence at the end of an interview or email.", "Thank you for your time; I look forward to hearing from you.", "آپ کے وقت کا شکریہ؛ مجھے آپ کے جواب کا انتظار رہے گا۔"),

        // Day 8: Technology & Programming (76–85)
        p(76, 8, "Tech & Coding", "My battery is running low.", "میری بیٹری ختم ہونے والی ہے۔", "Everyday phrase for phones or laptops.", "Let me plug in my charger; my battery is running low.", "مجھے چارجر لگانے دیں؛ میری بیٹری ختم ہونے والی ہے۔"),
        p(77, 8, "Tech & Coding", "Does this app work offline?", "کیا یہ ایپ انٹرنیٹ کے بغیر کام کرتی ہے؟", "Useful question when downloading mobile apps.", "Yes, English Daily Coach works completely offline!", "جی ہاں، انگلش ڈیلی کوچ مکمل طور پر آف لائن کام کرتی ہے!"),
        p(78, 8, "Tech & Coding", "I am learning to write code.", "میں پروگرامنگ کوڈ لکھنا سیکھ رہا ہوں۔", "Used by students starting software development.", "I am learning to write code in Kotlin and Python.", "میں کوٹلن اور پائیتھون میں کوڈ لکھنا سیکھ رہا ہوں۔"),
        p(79, 8, "Tech & Coding", "Let's test this feature.", "آئیے اس فیچر کو ٹیسٹ کر کے دیکھیں۔", "Common software engineering team phrase.", "Before releasing the app, let's test this feature carefully.", "ایپ جاری کرنے سے پہلے آئیے اس فیچر کو احتیاط سے چیک کریں۔"),
        p(80, 8, "Tech & Coding", "I fixed the bug in the program.", "میں نے پروگرام کی خرابی دور کر دی۔", "Means you found and solved a software mistake.", "After reading the logs, I fixed the bug in the program.", "لاگز پڑھنے کے بعد میں نے پروگرام کی خرابی دور کر دی۔"),
        p(81, 8, "Tech & Coding", "Please back up your important files.", "براہ کرم اپنی اہم فائلوں کا بیک اپ رکھ لیں۔", "Advice to save copies of data safely.", "Before resetting your laptop, please back up your important files.", "لیپ ٹاپ ری سیٹ کرنے سے پہلے اہم فائلوں کا بیک اپ لے لیں۔"),
        p(82, 8, "Tech & Coding", "Have you tried restarting it?", "کیا آپ نے اسے دوبارہ بند کر کے چلانے کی کوشش کی؟", "Classic troubleshooting step for devices.", "If the screen freezes, have you tried restarting it?", "اگر اسکرین رک جائے تو کیا آپ نے ری اسٹارٹ کر کے دیکھا؟"),
        p(83, 8, "Tech & Coding", "Let's break the problem into small steps.", "آئیے مسئلے کو چھوٹے حصوں میں تقسیم کریں۔", "Core programming mindset phrase.", "Don't panic; let's break the problem into small steps.", "گھبرائیں نہیں؛ آئیے مسئلے کو چھوٹے مراحل میں تقسیم کریں۔"),
        p(84, 8, "Tech & Coding", "Can you share your screen?", "کیا آپ اپنی اسکرین دکھا سکتے ہیں؟", "Common phrase in online classes or study groups.", "Can you share your screen so we can read the code together?", "کیا آپ اسکرین دکھا سکتے ہیں تاکہ ہم مل کر کوڈ پڑھ سکیں؟"),
        p(85, 8, "Tech & Coding", "Keep the user interface simple.", "یوزر انٹرفیس کو سادہ اور آسان رکھیں۔", "Design rule for building helpful apps.", "Beginners learn faster when we keep the user interface simple.", "نئے طلبہ تیزی سے سیکھتے ہیں جب انٹرفیس سادہ رکھا جائے۔"),

        // Day 9: Beginner Idioms Part 1 (86–95)
        p(86, 9, "Idioms Made Simple", "A piece of cake", "بہت ہی آسان کام", "Idiom meaning something is very easy to do.", "After practicing all week, the quiz was a piece of cake.", "پورا ہفتہ مشق کرنے کے بعد کوئز بہت ہی آسان رہا۔", idiom = true),
        p(87, 9, "Idioms Made Simple", "Practice makes perfect", "مشق انسان کو کامل بناتی ہے", "Proverb meaning regular practice leads to mastery.", "Keep reading aloud every day—practice makes perfect.", "روزانہ بلند آواز سے پڑھتے رہیں—مشق انسان کو کامل بناتی ہے۔", idiom = true),
        p(88, 9, "Idioms Made Simple", "Break the ice", "ابتدائی جھجک ختم کر کے بات شروع کرنا", "Idiom meaning to make strangers feel relaxed in conversation.", "Asking about someone's hometown is a nice way to break the ice.", "کسی کے شہر کے بارے میں پوچھنا جھجک ختم کرنے کا اچھا طریقہ ہے۔", idiom = true),
        p(89, 9, "Idioms Made Simple", "Once in a blue moon", "کبھی کبھار / عید کا چاند ہونا", "Idiom meaning something happens very rarely.", "It snows in this valley only once in a blue moon.", "اس وادی میں برف باری بہت ہی شاذ و نادر ہوتی ہے۔", idiom = true),
        p(90, 9, "Idioms Made Simple", "Under the weather", "طبیعت کچھ ناساز ہونا", "Idiom meaning feeling slightly sick or tired.", "Zain stayed home today because he felt under the weather.", "زین آج گھر پر رہا کیونکہ اس کی طبیعت کچھ ناساز تھی۔", idiom = true),
        p(91, 9, "Idioms Made Simple", "Better late than never", "بالکل نہ ہونے سے دیر سے ہونا بہتر ہے", "Proverb encouraging people that starting late is still good.", "You started learning English at thirty—better late than never!", "آپ نے تیس سال کی عمر میں انگریزی شروع کی—نہ کرنے سے دیر بہتر ہے!", idiom = true),
        p(92, 9, "Idioms Made Simple", "Step by step", "قدم بہ قدم / آہستہ آہستہ", "Doing something gradually in clear stages.", "You will master grammar step by step.", "آپ قدم بہ قدم گرامر پر عبور حاصل کر لیں گے۔", idiom = true),
        p(93, 9, "Idioms Made Simple", "Keep an eye on", "نگرانی رکھنا / دھیان رکھنا", "Idiom meaning to watch and take care of something.", "Could you keep an eye on my bag for two minutes?", "کیا آپ دو منٹ میرے بیگ پر نظر رکھ سکتے ہیں؟", idiom = true),
        p(94, 9, "Idioms Made Simple", "Hit the books", "خوب دل لگا کر پڑھائی شروع کرنا", "Idiom meaning to study seriously for exams.", "Exams start next week, so it is time to hit the books.", "اگلے ہفتے امتحانات ہیں، اب خوب پڑھائی کا وقت ہے۔", idiom = true),
        p(95, 9, "Idioms Made Simple", "In the same boat", "ایک ہی جیسی مشکل صورتحال میں ہونا", "Idiom meaning facing the same challenge together.", "All beginners feel shy speaking—we are all in the same boat.", "تمام نئے طلبہ بولتے ہوئے جھجکتے ہیں—ہم سب ایک ہی صورتحال میں ہیں۔", idiom = true),

        // Day 10: Beginner Idioms & Everyday Expressions Part 2 (96–105)
        p(96, 10, "Idioms Made Simple", "Call it a day", "آج کا کام یہیں ختم کرنا", "Idiom meaning to stop working for the rest of the day.", "We studied three chapters; let's call it a day.", "ہم نے تین ابواب پڑھ لیے؛ چلیں آج کا کام یہیں ختم کرتے ہیں۔", idiom = true),
        p(97, 10, "Idioms Made Simple", "Make up your mind", "پکا فیصلہ کرنا", "Idiom meaning to choose clearly and stop hesitating.", "Did you make up your mind about which course to join?", "کیا آپ نے فیصلہ کر لیا کہ کون سا کورس لینا ہے؟", idiom = true),
        p(98, 10, "Idioms Made Simple", "On time", "بالکل مقررہ وقت پر", "Neither late nor absent; punctual.", "The train left Lahore station right on time.", "ٹرین لاہور اسٹیشن سے بالکل وقت پر روانہ ہوئی۔", idiom = true),
        p(99, 10, "Idioms Made Simple", "By heart", "زبانی یاد کرنا", "Idiom meaning memorized completely without looking.", "My sister knows twenty English poems by heart.", "میری بہن کو انگریزی کی بیس نظمیں زبانی یاد ہیں۔", idiom = true),
        p(100, 10, "Idioms Made Simple", "Learn the ropes", "کام کے بنیادی طریقے سیکھنا", "Idiom meaning to learn how a new job or task works.", "It took me two weeks to learn the ropes at the office.", "دفتر میں کام کے طریقے سیکھنے میں مجھے دو ہفتے لگے۔", idiom = true),
        p(101, 10, "Opinions & Daily Use", "In my opinion...", "میری رائے میں۔۔۔", "Polite way to share your personal view.", "In my opinion, listening daily improves speaking fast.", "میری رائے میں روزانہ سننے سے بولنے میں تیزی سے بہتری آتی ہے۔"),
        p(102, 10, "Opinions & Daily Use", "As far as I know...", "جہاں تک مجھے معلوم ہے۔۔۔", "Used when sharing facts you believe are true.", "As far as I know, the library opens at nine o'clock.", "جہاں تک مجھے معلوم ہے، لائبریری نو بجے کھلتی ہے۔"),
        p(103, 10, "Opinions & Daily Use", "It depends on the weather.", "یہ موسم پر منحصر ہے۔", "Shows that a plan may change based on conditions.", "Will we play outside? — It depends on the weather.", "کیا ہم باہر کھیلیں گے؟ — یہ موسم پر منحصر ہے۔"),
        p(104, 10, "Opinions & Daily Use", "Let me think about it.", "مجھے اس بارے میں سوچنے کا وقت دیں۔", "Polite way to ask for time before deciding.", "That is an interesting offer; let me think about it.", "یہ ایک دلچسپ پیشکش ہے؛ مجھے اس پر سوچنے دیں۔"),
        p(105, 10, "Opinions & Daily Use", "Keep up the great work!", "یہ بہترین محنت جاری رکھیں!", "Warm praise to motivate someone doing well.", "You completed ten days in a row—keep up the great work!", "آپ نے لگاتار دس دن مکمل کیے—یہ بہترین محنت جاری رکھیں!", idiom = true)
    )
}
