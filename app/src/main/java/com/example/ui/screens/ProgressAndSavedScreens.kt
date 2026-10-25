package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.local.PhraseItemEntity
import com.example.data.local.VocabWordEntity
import com.example.ui.CoachScreen
import com.example.ui.CoachUiState
import com.example.ui.components.PhraseItemCard
import com.example.ui.components.SectionHeaderBox
import com.example.ui.components.UrduText
import com.example.ui.components.VocabWordCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProgressScreen(
    uiState: CoachUiState,
    onNavigate: (CoachScreen) -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    val wordsLearned = uiState.words.count { it.isLearned }
    val phrasesLearned = uiState.phrases.count { it.isLearned }
    val grammarDone = uiState.grammarLessons.count { it.isCompleted }
    val convDone = uiState.conversations.count { it.isCompleted }

    val avgQuizPct = remember(uiState.quizResults) {
        if (uiState.quizResults.isEmpty()) 0
        else {
            val sumPct = uiState.quizResults.sumOf {
                if (it.totalQuestions == 0) 0 else (it.score * 100) / it.totalQuestions
            }
            sumPct / uiState.quizResults.size
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("progress_screen_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            SectionHeaderBox(
                titleEn = "My Learning Progress",
                titleUr = "میری تعلیمی پیش رفت",
                subtitleEn = "Stored safely offline on your device—never resets when you close the app."
            )
        }

        // Streak & XP Hero Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("progress_streak_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                shape = MaterialTheme.shapes.large
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatColumn(
                        icon = Icons.Filled.LocalFireDepartment,
                        value = "${uiState.settings.currentStreak} Days",
                        label = "Current Streak"
                    )
                    StatColumn(
                        icon = Icons.Filled.WorkspacePremium,
                        value = "${uiState.settings.bestStreak} Days",
                        label = "Best Streak"
                    )
                    StatColumn(
                        icon = Icons.Filled.Stars,
                        value = "${uiState.settings.totalXp} XP",
                        label = "Total Score"
                    )
                }
            }
        }

        // Breakdown Progress Bars
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Course Mastery Overview",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    MasteryRow("Vocabulary Words", wordsLearned, uiState.words.size)
                    MasteryRow("Phrases & Idioms", phrasesLearned, uiState.phrases.size)
                    MasteryRow("Grammar Lessons", grammarDone, uiState.grammarLessons.size)
                    MasteryRow("Conversation Dialogues", convDone, uiState.conversations.size)
                }
            }
        }

        // Quiz Accuracy & Mistake Review
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Quiz Performance",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${uiState.quizResults.size} quizzes taken • Average: $avgQuizPct%",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    if (uiState.incorrectAnswers.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = { onNavigate(CoachScreen.QUIZ) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                        ) {
                            Text("Review ${uiState.incorrectAnswers.size} Incorrect Answers")
                        }
                    }
                }
            }
        }

        if (uiState.quizResults.isNotEmpty()) {
            item {
                Text(
                    text = "Recent Quiz History",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            items(uiState.quizResults.take(10), key = { it.id }) { res ->
                val formattedDate = remember(res.timestamp) {
                    SimpleDateFormat("MMM d, h:mm a", Locale.US).format(Date(res.timestamp))
                }
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = res.quizType.replace('_', ' '),
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = formattedDate,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = "${res.score}/${res.totalQuestions}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatColumn(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun MasteryRow(label: String, done: Int, total: Int) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "$done / $total",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { if (total == 0) 0f else done.toFloat() / total },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun SavedWordsScreen(
    savedWords: List<VocabWordEntity>,
    savedPhrases: List<PhraseItemEntity>,
    alwaysShowUrdu: Boolean,
    onSpeakText: (String) -> Unit,
    onToggleWordLearned: (VocabWordEntity) -> Unit,
    onToggleWordSaved: (VocabWordEntity) -> Unit,
    onTogglePhraseLearned: (PhraseItemEntity) -> Unit,
    onTogglePhraseSaved: (PhraseItemEntity) -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    var selectedTab by remember { mutableStateOf("WORDS") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("saved_words_screen_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionHeaderBox(
                titleEn = "Saved Words & Phrases",
                titleUr = "محفوظ کردہ الفاظ اور جملے",
                subtitleEn = "Your personal revision bookmark list for quick daily practice."
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                FilterChip(
                    selected = selectedTab == "WORDS",
                    onClick = { selectedTab = "WORDS" },
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("saved_tab_words"),
                    label = { Text("Saved Words (${savedWords.size})") }
                )
                FilterChip(
                    selected = selectedTab == "PHRASES",
                    onClick = { selectedTab = "PHRASES" },
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("saved_tab_phrases"),
                    label = { Text("Favorite Phrases (${savedPhrases.size})") }
                )
            }
        }

        if (selectedTab == "WORDS") {
            if (savedWords.isEmpty()) {
                item {
                    EmptySavedStateCard(
                        en = "No vocabulary words saved yet. Tap the bookmark icon on any word card to save it here!",
                        ur = "ابھی تک کوئی لفظ محفوظ نہیں کیا گیا۔ کسی بھی لفظ کے کارڈ پر بُک مارک دبا کر یہاں محفوظ کریں!"
                    )
                }
            } else {
                items(savedWords, key = { it.id }) { word ->
                    VocabWordCard(
                        word = word,
                        alwaysShowUrdu = alwaysShowUrdu,
                        onSpeakWord = onSpeakText,
                        onSpeakSentence = onSpeakText,
                        onToggleLearned = { onToggleWordLearned(word) },
                        onToggleSaved = { onToggleWordSaved(word) }
                    )
                }
            }
        } else {
            if (savedPhrases.isEmpty()) {
                item {
                    EmptySavedStateCard(
                        en = "No favorite phrases saved yet. Tap the bookmark icon on any phrase or idiom to collect it here!",
                        ur = "ابھی تک کوئی پسندیدہ جملہ محفوظ نہیں کیا گیا۔ کسی بھی جملے پر بُک مارک دبا کر محفوظ کریں!"
                    )
                }
            } else {
                items(savedPhrases, key = { it.id }) { phrase ->
                    PhraseItemCard(
                        phrase = phrase,
                        alwaysShowUrdu = alwaysShowUrdu,
                        onSpeakPhrase = onSpeakText,
                        onToggleLearned = { onTogglePhraseLearned(phrase) },
                        onToggleSaved = { onTogglePhraseSaved(phrase) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptySavedStateCard(en: String, ur: String) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.BookmarkBorder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(44.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = en, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(6.dp))
            UrduText(text = ur, fontSizeSp = 16f, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
