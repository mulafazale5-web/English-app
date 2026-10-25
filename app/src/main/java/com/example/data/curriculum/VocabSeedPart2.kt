package com.example.data.curriculum

import com.example.data.local.VocabWordEntity

object VocabSeedPart2 {
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
        // Day 6 cont. & Day 7: Time, Weather & Nature (56–70)
        w(56, 6, "Time & Weather", "Punctual", "pungk-choo-ul", "Adjective", "وقت کا پابند", "Arriving or doing things at the agreed time.", "Being punctual creates a great impression.", "وقت کا پابند ہونا بہت اچھا تاثر ڈالتا ہے۔"),
        w(57, 6, "Time & Weather", "Tomorrow", "tuh-mor-oh", "Noun / Adverb", "آنے والا کل", "The day after today.", "We have a speaking test tomorrow morning.", "کل صبح ہمارا انگریزی بولنے کا ٹیسٹ ہے۔"),
        w(58, 6, "Time & Weather", "Yesterday", "yes-ter-day", "Noun / Adverb", "گزرا ہوا کل", "The day before today.", "It rained heavily in Islamabad yesterday.", "کل اسلام آباد میں تیز بارش ہوئی۔"),
        w(59, 6, "Time & Weather", "Weather", "weth-er", "Noun", "موسم", "The state of the air outside, such as sun, rain, or wind.", "The weather is pleasant and breezy today.", "آج موسم خوشگوار اور ہوا دار ہے۔"),
        w(60, 6, "Time & Weather", "Umbrella", "um-brel-uh", "Noun", "چھتری", "An object you hold over your head to stay dry in rain.", "Carry an umbrella because dark clouds are gathering.", "چھتری ساتھ رکھیں کیونکہ گہرے بادل چھا رہے ہیں۔"),
        w(61, 7, "Travel & Directions", "Journey", "jur-nee", "Noun", "سفر", "An act of traveling from one place to another.", "The train journey to Karachi was comfortable.", "کراچی تک ریل کا سفر آرام دہ تھا۔"),
        w(62, 7, "Travel & Directions", "Direction", "di-rek-shun", "Noun", "سمت / راستہ", "The path along which someone moves or points.", "We asked a shopkeeper for the right direction.", "ہم نے ایک دکاندار سے صحیح راستہ پوچھا۔"),
        w(63, 7, "Travel & Directions", "Straight", "strayt", "Adverb / Adjective", "سیدھا", "Continuing in one line without turning left or right.", "Walk straight for two blocks to reach the bank.", "بینک پہنچنے کے لیے دو گلیاں سیدھا چلیں۔"),
        w(64, 7, "Travel & Directions", "Corner", "kor-ner", "Noun", "کونا / موڑ", "The place where two streets or walls meet.", "There is a bookstore at the corner of the street.", "گلی کے موڑ پر کتابوں کی ایک دکان ہے۔"),
        w(65, 7, "Travel & Directions", "Distance", "dis-tuns", "Noun", "فاصلہ", "The amount of space between two places.", "The bus stop is within walking distance.", "بس اسٹاپ پیدل چلنے کے فاصلے پر ہے۔"),
        w(66, 7, "Travel & Directions", "Passenger", "pas-in-jer", "Noun", "مسافر", "A traveler riding in a bus, train, car, or plane.", "Every passenger wore a seatbelt during the ride.", "سفر کے دوران ہر مسافر نے سیٹ بیلٹ باندھی۔"),
        w(67, 7, "Travel & Directions", "Ticket", "tik-it", "Noun", "ٹکٹ", "A printed or digital slip showing you paid to travel or enter.", "Please buy your bus ticket before boarding.", "سوار ہونے سے پہلے براہ کرم اپنا بس ٹکٹ خرید لیں۔"),
        w(68, 7, "Travel & Directions", "Arrive", "uh-ryv", "Verb", "پہنچنا", "To reach the place you were traveling toward.", "What time does the bus arrive at the station?", "بس اسٹیشن پر کس وقت پہنچتی ہے؟"),
        w(69, 7, "Travel & Directions", "Traffic", "traf-ik", "Noun", "ٹریفک / گاڑیوں کی آمدورفت", "Cars, buses, and bikes moving along roads.", "Leave early to avoid heavy morning traffic.", "صبح کے رش سے بچنے کے لیے جلدی نکلیں۔"),
        w(70, 7, "Travel & Directions", "Bridge", "brij", "Noun", "پُل", "A structure built over a river or road so people can cross.", "Cross the pedestrian bridge carefully.", "پیدل چلنے والوں کا پُل احتیاط سے پار کریں۔"),

        // Day 8: Shopping & Money (71–80)
        w(71, 8, "Shopping & Money", "Price", "prys", "Noun", "قیمت", "The amount of money needed to buy something.", "What is the price of this winter jacket?", "اس سردیوں کی جیکٹ کی قیمت کیا ہے؟"),
        w(72, 8, "Shopping & Money", "Expensive", "ik-spen-siv", "Adjective", "مہنگا", "Costing a lot of money.", "This leather bag is too expensive for my budget.", "یہ چمڑے کا بیگ میرے بجٹ کے لحاظ سے بہت مہنگا ہے۔"),
        w(73, 8, "Shopping & Money", "Affordable", "uh-for-duh-bul", "Adjective", "مناسب قیمت والا", "Reasonably priced so most people can buy it.", "We found a clean and affordable notebook set.", "ہمیں صاف اور مناسب قیمت والا نوٹ بک سیٹ مل گیا۔"),
        w(74, 8, "Shopping & Money", "Discount", "dis-kownt", "Noun", "رعایت / کمی", "A reduction from the usual price.", "Students get a ten percent discount on books.", "طلبہ کو کتابوں پر دس فیصد رعایت ملتی ہے۔"),
        w(75, 8, "Shopping & Money", "Receipt", "ri-seet", "Noun", "رسید", "A paper slip proving you paid for an item.", "Keep the receipt in case you want to exchange the shirt.", "رسید سنبھال کر رکھیں اگر آپ قمیض تبدیل کرانا چاہیں۔"),
        w(76, 8, "Shopping & Money", "Customer", "kus-tuh-mer", "Noun", "خریدار / گاہک", "A person who buys goods or services from a shop.", "The shopkeeper greeted every customer politely.", "دکاندار نے ہر گاہک کا شائستگی سے استقبال کیا۔"),
        w(77, 8, "Shopping & Money", "Quality", "kwol-uh-tee", "Noun", "معیار / عمدگی", "How good or durable something is.", "Always check the fabric quality before buying clothes.", "کپڑے خریدنے سے پہلے ہمیشہ کپڑے کا معیار چیک کریں۔"),
        w(78, 8, "Shopping & Money", "Change", "chaynj", "Noun / Verb", "بقایا پیسے / بدلنا", "Coins or notes returned when you pay more than the price.", "The cashier handed me my change and smiled.", "کیشیئر نے مجھے بقایا پیسے دیے اور مسکرایا۔"),
        w(79, 8, "Shopping & Money", "Choose", "chooz", "Verb", "منتخب کرنا / چننا", "To pick one thing from two or more options.", "Which color did you choose for your shoes?", "آپ نے اپنے جوتوں کے لیے کون سا رنگ منتخب کیا؟"),
        w(80, 8, "Shopping & Money", "Save", "sayv", "Verb", "بچت کرنا / محفوظ کرنا", "To keep money or items for future use.", "I save a little pocket money every week.", "میں ہر ہفتے تھوڑی جیب خرچ کی بچت کرتا ہوں۔"),

        // Day 9: Health & Feelings (81–90)
        w(81, 9, "Health & Feelings", "Exercise", "ek-ser-syz", "Noun / Verb", "ورزش / مشق", "Physical activity done to stay strong and healthy.", "Twenty minutes of walking is great daily exercise.", "بیس منٹ پیدل چلنا روزانہ کی بہترین ورزش ہے۔"),
        w(82, 9, "Health & Feelings", "Medicine", "med-uh-sin", "Noun", "دوا / دوائی", "Something taken to treat an illness.", "Take your medicine after eating dinner.", "رات کا کھانا کھانے کے بعد اپنی دوا لیں۔"),
        w(83, 9, "Health & Feelings", "Tired", "ty-erd", "Adjective", "تھکا ہوا", "Needing rest or sleep after effort.", "I feel tired after a long day at college.", "کالج میں لمبے دن کے بعد میں تھکن محسوس کر رہا ہوں۔"),
        w(84, 9, "Health & Feelings", "Excited", "ik-sy-tid", "Adjective", "پُرجوش / خوش", "Very enthusiastic and eager about something good.", "Hamza is excited about his first job interview.", "حمزہ اپنے پہلے جاب انٹرویو کے بارے میں بہت پُرجوش ہے۔"),
        w(85, 9, "Health & Feelings", "Calm", "kahm", "Adjective", "پُرسکون / مطمئن", "Peaceful and free from worry or anger.", "Take a deep breath and stay calm during exams.", "امتحان کے دوران گہری سانس لیں اور پُرسکون رہیں۔"),
        w(86, 9, "Health & Feelings", "Nervous", "nur-vus", "Adjective", "گھبرایا ہوا", "Slightly worried or afraid before an important event.", "It is natural to feel nervous on stage.", "اسٹیج پر تھوڑی گھبراہٹ محسوس ہونا فطری بات ہے۔"),
        w(87, 9, "Health & Feelings", "Rest", "rest", "Verb / Noun", "آرام / آرام کرنا", "To relax and stop working so your energy returns.", "You should rest at home until your fever goes away.", "بخار اترنے تک آپ کو گھر پر آرام کرنا چاہیے۔"),
        w(88, 9, "Health & Feelings", "Strength", "strength", "Noun", "طاقت / مضبوطی", "Physical energy or strong inner character.", "Healthy food gives you strength to work well.", "صحت بخش کھانا آپ کو اچھا کام کرنے کی طاقت دیتا ہے۔"),
        w(89, 9, "Health & Feelings", "Careful", "kair-ful", "Adjective", "محتاط / ہوشیار", "Giving close attention to avoid danger or mistakes.", "Be careful while crossing the busy main road.", "مصروف سڑک پار کرتے وقت محتاط رہیں۔"),
        w(90, 9, "Health & Feelings", "Cheerful", "cheer-ful", "Adjective", "خوش مزاج / ہنس مکھ", "Noticeably happy and positive.", "Her cheerful voice brightened the whole classroom.", "اس کی خوش مزاج آواز نے پوری کلاس کو خوشگوار بنا دیا۔"),

        // Day 10: Work & Career (91–100)
        w(91, 10, "Work & Career", "Opportunity", "op-er-too-nuh-tee", "Noun", "موقع", "A good chance to do something valuable.", "Learning English opens many career opportunities.", "انگریزی سیکھنا ملازمت کے بہت سے مواقع کھولتا ہے۔"),
        w(92, 10, "Work & Career", "Experience", "ik-speer-ee-uns", "Noun", "تجربہ", "Skill gained by actually doing work over time.", "Do you have any experience in customer service?", "کیا آپ کو کسٹمر سروس میں کوئی تجربہ ہے؟"),
        w(93, 10, "Work & Career", "Skill", "skil", "Noun", "مہارت / ہنر", "The ability to do an activity well.", "Clear communication is an essential workplace skill.", "صاف بات چیت کام کی جگہ کی ایک اہم مہارت ہے۔"),
        w(94, 10, "Work & Career", "Goal", "gohl", "Noun", "مقصد / ہدف", "Something you aim to achieve in the future.", "My goal is to speak fluent English within six months.", "میرا ہدف چھ ماہ میں روانی سے انگریزی بولنا ہے۔"),
        w(95, 10, "Work & Career", "Responsible", "ri-spon-suh-bul", "Adjective", "ذمہ دار", "Trusted to complete duties reliably.", "Managers appreciate team members who are responsible.", "مینیجرز ذمہ دار ٹیم ممبران کی قدر کرتے ہیں۔"),
        w(96, 10, "Work & Career", "Schedule", "skej-ool", "Noun", "نظام الاوقات / شیڈول", "A plan showing times for tasks or meetings.", "Let me check my schedule for Tuesday afternoon.", "مجھے منگل کی دوپہر کے لیے اپنا شیڈول دیکھنے دیں۔"),
        w(97, 10, "Work & Career", "Colleague", "kol-eeg", "Noun", "ساتھی کارکن", "A person you work with at a job or office.", "My colleague showed me how to use the printer.", "میرے ساتھی کارکن نے مجھے پرنٹر استعمال کرنے کا طریقہ دکھایا۔"),
        w(98, 10, "Work & Career", "Interview", "in-ter-vyoo", "Noun", "انٹرویو / بالمشافہ گفتگو", "A formal meeting where questions are asked for a job.", "Dress neatly and arrive early for your interview.", "اپنے انٹرویو کے لیے صاف لباس پہنیں اور جلدی پہنچیں۔"),
        w(99, 10, "Work & Career", "Effort", "ef-ert", "Noun", "کوشش / محنت", "Energy and determination put into doing a task.", "Your daily effort will bring great results soon.", "آپ کی روزانہ کی کوشش جلد بہترین نتائج لائے گی۔"),
        w(100, 10, "Work & Career", "Achieve", "uh-cheev", "Verb", "حاصل کرنا / پانا", "To successfully reach a goal through work.", "You can achieve anything when you study consistently.", "مسلسل پڑھائی سے آپ کچھ بھی حاصل کر سکتے ہیں۔"),

        // Day 11: Programming & Technology (101–110)
        w(101, 11, "Technology & Coding", "Software", "soft-wair", "Noun", "سافٹ ویئر / کمپیوٹر پروگرام", "Programs and apps that run on a computer or phone.", "Usman wants to become a mobile software developer.", "عثمان موبائل سافٹ ویئر ڈویلپر بننا چاہتا ہے۔"),
        w(102, 11, "Technology & Coding", "Device", "di-vys", "Noun", "آلہ / ڈیوائس", "A piece of electronic equipment like a phone or tablet.", "This app works offline on any Android device.", "یہ ایپ کسی بھی اینڈرائیڈ ڈیوائس پر آف لائن کام کرتی ہے۔"),
        w(103, 11, "Technology & Coding", "Screen", "skreen", "Noun", "اسکرین", "The flat glass surface that displays text and pictures.", "Adjust the brightness of your phone screen at night.", "رات کو اپنے فون کی اسکرین کی روشنی مناسب رکھیں۔"),
        w(104, 11, "Technology & Coding", "Keyboard", "kee-bord", "Noun", "کی بورڈ", "A set of keys used for typing letters and numbers.", "I switched my keyboard between English and Urdu.", "میں نے اپنا کی بورڈ انگریزی اور اردو کے درمیان تبدیل کیا۔"),
        w(105, 11, "Technology & Coding", "Search", "surch", "Verb / Noun", "تلاش کرنا / تلاش", "To look carefully for information or an item.", "You can search any grammar lesson without internet.", "آپ انٹرنیٹ کے بغیر کوئی بھی گرامر کا سبق تلاش کر سکتے ہیں۔"),
        w(106, 11, "Technology & Coding", "Update", "up-dayt", "Verb / Noun", "جدید بنانا / اپ ڈیٹ", "To add the newest improvements or information.", "Remember to save your file after you update it.", "فائل کو اپ ڈیٹ کرنے کے بعد محفوظ کرنا یاد رکھیں۔"),
        w(107, 11, "Technology & Coding", "Battery", "bat-er-ee", "Noun", "بیٹری", "The part inside a phone or laptop that stores power.", "My laptop battery lasts for eight hours.", "میرے لیپ ٹاپ کی بیٹری آٹھ گھنٹے چلتی ہے۔"),
        w(108, 11, "Technology & Coding", "Code", "kohd", "Noun / Verb", "پروگرامنگ کوڈ", "Instructions written by programmers for computers.", "Clean code is easy for other developers to read.", "صاف کوڈ دوسرے ڈویلپرز کے لیے پڑھنا آسان ہوتا ہے۔"),
        w(109, 11, "Technology & Coding", "Error", "er-er", "Noun", "خرابی / غلطی", "A mistake in a program or calculation that stops it working.", "Read the error message carefully to fix the bug.", "مسئلہ حل کرنے کے لیے ایرر کا پیغام دھیان سے پڑھیں۔"),
        w(110, 11, "Technology & Coding", "Solution", "suh-loo-shun", "Noun", "حل", "The correct way to solve a problem.", "Every coding problem has a logical solution.", "پروگرامنگ کے ہر مسئلے کا ایک منطقی حل ہوتا ہے۔")
    )
}
