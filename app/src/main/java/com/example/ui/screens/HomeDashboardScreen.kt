package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.OfflinePin
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.ui.CoachScreen
import com.example.ui.CoachUiState
import com.example.ui.DailyPlanBundle
import com.example.ui.components.SectionHeaderBox
import com.example.ui.components.UrduText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeDashboardScreen(
    uiState: CoachUiState,
    onNavigate: (CoachScreen, String?) -> Unit,
    onSelectPlanDay: (Int) -> Unit,
    onStartDailyQuiz: () -> Unit,
    onSpeakText: (String) -> Unit,
    onMarkDailySentencesDone: () -> Unit
) {
    val plan = uiState.dailyPlan
    var showFiveSentences by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_dashboard_list"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Hero Banner Card
        item {
            HeroCoachBanner(
                streak = uiState.settings.currentStreak,
                totalXp = uiState.settings.totalXp,
                onSearchClick = { onNavigate(CoachScreen.SEARCH, null) }
            )
        }

        // 2. Day Selector Bar (Repeat or Advance Days 1–10)
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Daily Learning Plan • Day ${plan.dayNumber}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${plan.completedStepsCount}/6 Done",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.testTag("day_selector_row")
                ) {
                    items((1..10).toList()) { day ->
                        FilterChip(
                            selected = plan.dayNumber == day,
                            onClick = { onSelectPlanDay(day) },
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("select_day_$day"),
                            label = { Text("Day $day") }
                        )
                    }
                }
            }
        }

        // 3. Today's Complete 6-Part Daily Plan Card
        item {
            DailyLearningPlanCard(
                plan = plan,
                alwaysShowUrdu = uiState.settings.alwaysShowUrdu,
                showFiveSentences = showFiveSentences,
                onToggleSentences = { showFiveSentences = !showFiveSentences },
                onOpenTodayWords = { onNavigate(CoachScreen.VOCABULARY, "TODAY") },
                onOpenTodayPhrases = { onNavigate(CoachScreen.PHRASES, "TODAY") },
                onOpenGrammar = { onNavigate(CoachScreen.GRAMMAR, null) },
                onStartMiniQuiz = onStartDailyQuiz,
                onOpenSpeaking = { onNavigate(CoachScreen.SPEAKING, null) },
                onSpeakSentence = onSpeakText,
                onMarkSentencesReviewed = onMarkDailySentencesDone
            )
        }

        // 4. All Learning Modules Grid
        item {
            SectionHeaderBox(
                titleEn = "Explore All Lessons",
                titleUr = "تمام تعلیمی حصے",
                subtitleEn = "100% offline • Progress saved automatically on your phone"
            )
        }

        item {
            val learnedWords = uiState.words.count { it.isLearned }
            val learnedPhrases = uiState.phrases.count { it.isLearned }
            val doneGrammar = uiState.grammarLessons.count { it.isCompleted }
            val doneConvs = uiState.conversations.count { it.isCompleted }
            val savedTotal = uiState.savedWords.size + uiState.savedPhrases.size

            val modules = listOf(
                ModuleTileData(
                    CoachScreen.VOCABULARY,
                    "Daily Vocabulary",
                    "روزمرہ الفاظ",
                    "$learnedWords / ${uiState.words.size} learned",
                    Icons.AutoMirrored.Filled.MenuBook,
                    "module_vocabulary"
                ),
                ModuleTileData(
                    CoachScreen.PHRASES,
                    "Phrases & Idioms",
                    "جملے اور محاورے",
                    "$learnedPhrases / ${uiState.phrases.size} mastered",
                    Icons.Filled.FormatQuote,
                    "module_phrases"
                ),
                ModuleTileData(
                    CoachScreen.CONVERSATIONS,
                    "Conversations",
                    "روزمرہ گفتگو",
                    "$doneConvs / ${uiState.conversations.size} dialogues",
                    Icons.AutoMirrored.Filled.Chat,
                    "module_conversations"
                ),
                ModuleTileData(
                    CoachScreen.GRAMMAR,
                    "Grammar Lessons",
                    "گرامر کے اسباق",
                    "$doneGrammar / ${uiState.grammarLessons.size} completed",
                    Icons.Filled.AutoStories,
                    "module_grammar"
                ),
                ModuleTileData(
                    CoachScreen.QUIZ,
                    "Quiz & Tests",
                    "کوئز اور ٹیسٹ",
                    "${uiState.quizResults.size} tests • ${uiState.incorrectAnswers.size} to review",
                    Icons.Filled.Quiz,
                    "module_quiz"
                ),
                ModuleTileData(
                    CoachScreen.SPEAKING,
                    "Speaking Practice",
                    "بولنے کی مشق",
                    "7 real-life topics + Audio",
                    Icons.Filled.Mic,
                    "module_speaking"
                ),
                ModuleTileData(
                    CoachScreen.SAVED,
                    "Saved Words",
                    "محفوظ الفاظ",
                    "$savedTotal items bookmarked",
                    Icons.Filled.Bookmark,
                    "module_saved"
                ),
                ModuleTileData(
                    CoachScreen.PROGRESS,
                    "My Progress",
                    "میری پیش رفت",
                    "Streak ${uiState.settings.currentStreak}d • ${uiState.settings.totalXp} XP",
                    Icons.Filled.BarChart,
                    "module_progress"
                ),
                ModuleTileData(
                    CoachScreen.SETTINGS,
                    "Settings",
                    "ترتیبات",
                    "Theme, font size & audio",
                    Icons.Filled.Settings,
                    "module_settings"
                )
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 2,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                modules.forEach { mod ->
                    ModuleQuickCard(
                        data = mod,
                        onClick = { onNavigate(mod.screen, "ALL") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

private data class ModuleTileData(
    val screen: CoachScreen,
    val titleEn: String,
    val titleUr: String,
    val subtitle: String,
    val icon: ImageVector,
    val tag: String
)

@Composable
private fun HeroCoachBanner(
    streak: Int,
    totalXp: Int,
    onSearchClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.img_hero_coach),
                contentDescription = stringResource(R.string.cd_hero_banner),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(215.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(215.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0x990B1942),
                                Color(0xE60F256E)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(50)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.OfflinePin,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "100% Offline Coach",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White
                                )
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            StatPill(
                                icon = Icons.Filled.LocalFireDepartment,
                                text = "$streak Day Streak",
                                iconTint = Color(0xFFFBBF24)
                            )
                            StatPill(
                                icon = Icons.Filled.Stars,
                                text = "$totalXp XP",
                                iconTint = Color(0xFF7DD3FC)
                            )
                        }
                    }

                    Column {
                        Text(
                            text = "Learn English Confidently",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        UrduText(
                            text = "آسان اردو رہنمائی کے ساتھ روزانہ انگریزی سیکھیں",
                            fontSizeSp = 15f,
                            color = Color(0xFFE0F2FE)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .clickable(onClick = onSearchClick)
                                .testTag("home_search_bar"),
                            color = Color.White,
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = stringResource(R.string.cd_open_search),
                                    tint = Color(0xFF1E40AF)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Search 110+ words, phrases, or Urdu…",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF475569)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatPill(icon: ImageVector, text: String, iconTint: Color) {
    Surface(
        color = Color(0xCC0F172A),
        shape = RoundedCornerShape(50)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text, style = MaterialTheme.typography.labelMedium, color = Color.White)
        }
    }
}

@Composable
private fun DailyLearningPlanCard(
    plan: DailyPlanBundle,
    alwaysShowUrdu: Boolean,
    showFiveSentences: Boolean,
    onToggleSentences: () -> Unit,
    onOpenTodayWords: () -> Unit,
    onOpenTodayPhrases: () -> Unit,
    onOpenGrammar: () -> Unit,
    onStartMiniQuiz: () -> Unit,
    onOpenSpeaking: () -> Unit,
    onSpeakSentence: (String) -> Unit,
    onMarkSentencesReviewed: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("daily_plan_card"),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Today's 6-Step Coach Plan",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Complete each bite-sized step at your own pace",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Box(modifier = Modifier.width(120.dp)) {
                    UrduText(
                        text = "آج کا تعلیمی منصوبہ",
                        fontSizeSp = 15f,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = { plan.completedStepsCount / 6f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(modifier = Modifier.height(12.dp))

            PlanStepRow(
                title = "1. 10 New English Words",
                subtitle = "${plan.wordsLearnedCount}/${plan.words.size} marked learned",
                done = plan.words.isNotEmpty() && plan.wordsLearnedCount >= plan.words.size,
                tag = "plan_step_words",
                onClick = onOpenTodayWords
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

            PlanStepRow(
                title = "2. 10 Useful English Phrases",
                subtitle = "${plan.phrasesLearnedCount}/${plan.phrases.size} mastered",
                done = plan.phrases.isNotEmpty() && plan.phrasesLearnedCount >= plan.phrases.size,
                tag = "plan_step_phrases",
                onClick = onOpenTodayPhrases
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

            PlanStepRow(
                title = "3. 5 Natural Example Sentences",
                subtitle = if (showFiveSentences) "Tap to collapse sentences" else "Tap to read & listen aloud",
                done = plan.sentencesReviewedToday,
                tag = "plan_step_sentences",
                onClick = onToggleSentences
            )

            AnimatedVisibility(visible = showFiveSentences) {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        plan.fiveExampleSentences.forEachIndexed { idx, s ->
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${idx + 1}. ${s.english}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(onClick = { onSpeakSentence(s.english) }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                            contentDescription = "Listen sentence ${idx + 1}",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                                if (alwaysShowUrdu || showFiveSentences) {
                                    UrduText(
                                        text = s.urdu,
                                        fontSizeSp = 15f,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                        Button(
                            onClick = onMarkSentencesReviewed,
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .testTag("mark_sentences_done_button")
                        ) {
                            Text(if (plan.sentencesReviewedToday) "5 Sentences Practiced ✓" else "Mark 5 Sentences Practiced (+15 XP)")
                        }
                    }
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

            PlanStepRow(
                title = "4. Short Grammar Lesson",
                subtitle = plan.grammarLesson?.titleEn ?: "Essential English Grammar",
                done = plan.grammarDone,
                tag = "plan_step_grammar",
                onClick = onOpenGrammar
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

            PlanStepRow(
                title = "5. Daily Mini Quiz",
                subtitle = "6 quick multiple-choice questions",
                done = plan.miniQuizCompletedToday,
                tag = "plan_step_quiz",
                onClick = onStartMiniQuiz
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

            PlanStepRow(
                title = "6. Short Speaking Exercise",
                subtitle = plan.speakingConversation?.titleEn ?: "Read & speak aloud",
                done = plan.speakingDoneToday,
                tag = "plan_step_speaking",
                onClick = onOpenSpeaking
            )
        }
    }
}

@Composable
private fun PlanStepRow(
    title: String,
    subtitle: String,
    done: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .minimumInteractiveComponentSize()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp)
            .testTag(tag),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (done) Icons.Filled.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
            contentDescription = if (done) "Completed" else "Pending",
            tint = if (done) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Open step",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun ModuleQuickCard(
    data: ModuleTileData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier.testTag(data.tag),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = data.icon,
                        contentDescription = data.titleEn,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(22.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = data.titleEn,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            UrduText(
                text = data.titleUr,
                fontSizeSp = 15f,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = data.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
