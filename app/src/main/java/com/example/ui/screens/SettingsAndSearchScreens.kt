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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.local.PhraseItemEntity
import com.example.data.local.UserSettingsEntity
import com.example.data.local.VocabWordEntity
import com.example.ui.CoachUiState
import com.example.ui.components.PhraseItemCard
import com.example.ui.components.SectionHeaderBox
import com.example.ui.components.UrduText
import com.example.ui.components.VocabWordCard

@Composable
fun SettingsScreen(
    settings: UserSettingsEntity,
    onUpdateTheme: (String) -> Unit,
    onUpdateTextScale: (String) -> Unit,
    onUpdateAlwaysShowUrdu: (Boolean) -> Unit,
    onUpdateSpeechRate: (Float) -> Unit,
    onTestSpeech: () -> Unit,
    onResetProgress: () -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    var showResetConfirm by remember { mutableStateOf(false) }

    if (showResetConfirm) {
        AlertDialog(
            onDismissRequest = { showResetConfirm = false },
            title = { Text("Reset Learning Progress?") },
            text = {
                Text("This will clear your learned checkmarks, saved bookmarks, quiz scores, and XP. All 110+ bundled lessons will remain available offline.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showResetConfirm = false
                        onResetProgress()
                    },
                    modifier = Modifier.testTag("confirm_reset_button")
                ) {
                    Text("Reset Everything", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirm = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("settings_screen_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            SectionHeaderBox(
                titleEn = "Settings & Accessibility",
                titleUr = "ایپ کی ترتیبات",
                subtitleEn = "Customize theme, text readability, Urdu translations, and audio speed."
            )
        }

        // Theme Mode
        item {
            SettingsCard(title = "Color Theme (رنگین تھیم)") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("LIGHT" to "Light (Blue/White)", "DARK" to "Dark", "SYSTEM" to "System").forEach { (key, label) ->
                        FilterChip(
                            selected = settings.themeMode == key,
                            onClick = { onUpdateTheme(key) },
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("theme_option_$key"),
                            label = { Text(label) }
                        )
                    }
                }
            }
        }

        // Text Readability Size
        item {
            SettingsCard(title = "Text Size (لکھائی کا سائز)") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("NORMAL" to "Normal", "LARGE" to "Large", "XLARGE" to "Extra Large").forEach { (key, label) ->
                        FilterChip(
                            selected = settings.textScaleMode == key,
                            onClick = { onUpdateTextScale(key) },
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("font_scale_$key"),
                            label = { Text(label) }
                        )
                    }
                }
            }
        }

        // Urdu Translation Behavior
        item {
            SettingsCard(title = "Urdu Translation Display (اردو ترجمہ)") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Always Show Urdu Meanings",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Turn off if you prefer active-recall (tap each card to reveal Urdu).",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Switch(
                        checked = settings.alwaysShowUrdu,
                        onCheckedChange = onUpdateAlwaysShowUrdu,
                        modifier = Modifier.testTag("switch_always_show_urdu")
                    )
                }
            }
        }

        // Audio Playback Speed
        item {
            SettingsCard(title = "Voice Playback Speed (آواز کی رفتار)") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(0.75f to "Slow (0.75x)", 0.95f to "Normal (1.0x)", 1.15f to "Brisk (1.15x)").forEach { (rate, label) ->
                        FilterChip(
                            selected = kotlin.math.abs(settings.speechRate - rate) < 0.05f,
                            onClick = { onUpdateSpeechRate(rate) },
                            modifier = Modifier.minimumInteractiveComponentSize(),
                            label = { Text(label) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                FilledTonalButton(
                    onClick = onTestSpeech,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("test_tts_button")
                ) {
                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Test English Audio Voice")
                }
            }
        }

        // Offline Architecture Transparency Card
        item {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "100% Offline & Private Architecture",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "All 110 vocabulary words, 105 phrases & idioms, 30 grammar lessons, and 21 dialogues are bundled inside the app and stored in a local Room SQLite database. No account, API key, or internet connection is required. Cloud AI free-form chat is intentionally not simulated offline.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UrduText(
                        text = "یہ ایپ مکمل طور پر آف لائن کام کرتی ہے اور آپ کا تعلیمی ریکارڈ آپ کے فون میں محفوظ رہتا ہے۔",
                        fontSizeSp = 15f
                    )
                }
            }
        }

        // Optional Progress Reset
        item {
            OutlinedButton(
                onClick = { showResetConfirm = true },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .testTag("reset_progress_button")
            ) {
                Icon(Icons.Filled.DeleteForever, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reset Learning Progress")
            }
        }
    }
}

@Composable
private fun SettingsCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
fun OfflineSearchScreen(
    uiState: CoachUiState,
    query: String,
    onQueryChange: (String) -> Unit,
    onSpeakText: (String) -> Unit,
    onToggleWordLearned: (VocabWordEntity) -> Unit,
    onToggleWordSaved: (VocabWordEntity) -> Unit,
    onTogglePhraseLearned: (PhraseItemEntity) -> Unit,
    onTogglePhraseSaved: (PhraseItemEntity) -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    val q = query.trim()

    val matchingWords = remember(uiState.words, q) {
        if (q.isEmpty()) emptyList()
        else uiState.words.filter {
            it.english.contains(q, ignoreCase = true) ||
                it.urdu.contains(q) ||
                it.explanation.contains(q, ignoreCase = true) ||
                it.exampleEn.contains(q, ignoreCase = true)
        }
    }

    val matchingPhrases = remember(uiState.phrases, q) {
        if (q.isEmpty()) emptyList()
        else uiState.phrases.filter {
            it.english.contains(q, ignoreCase = true) ||
                it.urdu.contains(q) ||
                it.explanation.contains(q, ignoreCase = true)
        }
    }

    val matchingGrammar = remember(uiState.grammarLessons, q) {
        if (q.isEmpty()) emptyList()
        else uiState.grammarLessons.filter {
            it.titleEn.contains(q, ignoreCase = true) ||
                it.titleUr.contains(q) ||
                it.explanationEn.contains(q, ignoreCase = true) ||
                it.formula.contains(q, ignoreCase = true)
        }
    }

    val matchingConvs = remember(uiState.conversations, q) {
        if (q.isEmpty()) emptyList()
        else uiState.conversations.filter {
            it.titleEn.contains(q, ignoreCase = true) ||
                it.titleUr.contains(q) ||
                it.topic.contains(q, ignoreCase = true) ||
                it.turnsPacked.contains(q, ignoreCase = true)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("offline_search_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionHeaderBox(
                titleEn = "Search All Lessons Offline",
                titleUr = "آف لائن تلاش",
                subtitleEn = "Type any English word, topic, or Urdu word (مثلاً: خوش آمدید، سفر، مہمان)."
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("global_offline_search_input"),
                placeholder = { Text("Search words, phrases, grammar, dialogues…") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear search")
                        }
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )
        }

        if (q.isEmpty()) {
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Try searching: 'polite', 'interview', 'ticket', 'idiom', 'present', or 'خوش'.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            val totalHits = matchingWords.size + matchingPhrases.size + matchingGrammar.size + matchingConvs.size
            item {
                Text(
                    text = "Found $totalHits offline matches for \"$q\"",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(matchingWords, key = { "sw_${it.id}" }) { word ->
                VocabWordCard(
                    word = word,
                    alwaysShowUrdu = true,
                    onSpeakWord = onSpeakText,
                    onSpeakSentence = onSpeakText,
                    onToggleLearned = { onToggleWordLearned(word) },
                    onToggleSaved = { onToggleWordSaved(word) }
                )
            }

            items(matchingPhrases, key = { "sp_${it.id}" }) { phrase ->
                PhraseItemCard(
                    phrase = phrase,
                    alwaysShowUrdu = true,
                    onSpeakPhrase = onSpeakText,
                    onToggleLearned = { onTogglePhraseLearned(phrase) },
                    onToggleSaved = { onTogglePhraseSaved(phrase) }
                )
            }

            items(matchingGrammar, key = { "sg_${it.id}" }) { g ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Grammar Lesson ${g.id}: ${g.titleEn}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                        UrduText(g.titleUr, fontSizeSp = 15f)
                        Text("Formula: ${g.formula}", style = MaterialTheme.typography.labelLarge)
                        Text(g.explanationEn, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            items(matchingConvs, key = { "sc_${it.id}" }) { c ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Conversation (${c.topic}): ${c.titleEn}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                        UrduText(c.titleUr, fontSizeSp = 15f)
                        Text("Speaking Prompt: \"${c.speakingPromptEn}\"", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
