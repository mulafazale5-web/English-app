package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import com.example.data.local.PhraseItemEntity
import com.example.data.local.VocabWordEntity
import com.example.ui.components.PhraseItemCard
import com.example.ui.components.SectionHeaderBox
import com.example.ui.components.VocabWordCard

@Composable
fun VocabularyScreen(
    words: List<VocabWordEntity>,
    currentPlanDay: Int,
    activeFilter: String,
    alwaysShowUrdu: Boolean,
    onFilterChange: (String) -> Unit,
    onSpeakText: (String) -> Unit,
    onToggleLearned: (VocabWordEntity) -> Unit,
    onToggleSaved: (VocabWordEntity) -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    var localQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val categories = remember(words) {
        listOf("All") + words.map { it.category }.distinct()
    }

    val filteredWords = remember(words, activeFilter, currentPlanDay, localQuery, selectedCategory) {
        words.filter { w ->
            val matchesMode = when (activeFilter) {
                "TODAY" -> w.dayNumber == currentPlanDay
                "UNLEARNED" -> !w.isLearned
                "LEARNED" -> w.isLearned
                else -> true
            }
            val matchesCat = selectedCategory == "All" || w.category == selectedCategory
            val q = localQuery.trim()
            val matchesSearch = q.isEmpty() ||
                w.english.contains(q, ignoreCase = true) ||
                w.urdu.contains(q) ||
                w.explanation.contains(q, ignoreCase = true) ||
                w.exampleEn.contains(q, ignoreCase = true)
            matchesMode && matchesCat && matchesSearch
        }
    }

    val learnedCount = words.count { it.isLearned }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("vocabulary_screen_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionHeaderBox(
                titleEn = "Daily Vocabulary (${words.size} Words)",
                titleUr = "روزمرہ الفاظ کا ذخیرہ",
                subtitleEn = "Tap the Urdu box to reveal translations, listen via TTS, and mark words learned."
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mastery Progress",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "$learnedCount / ${words.size} Learned",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { if (words.isEmpty()) 0f else learnedCount.toFloat() / words.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape)
            )
        }

        item {
            OutlinedTextField(
                value = localQuery,
                onValueChange = { localQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("vocab_search_input"),
                placeholder = { Text("Filter words in English or Urdu…") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (localQuery.isNotEmpty()) {
                        IconButton(onClick = { localQuery = "" }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear search")
                        }
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )
        }

        item {
            val filters = listOf(
                "TODAY" to "Day $currentPlanDay (10)",
                "ALL" to "All (${words.size})",
                "UNLEARNED" to "To Learn",
                "LEARNED" to "Learned ($learnedCount)"
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filters) { (key, label) ->
                    FilterChip(
                        selected = activeFilter == key,
                        onClick = { onFilterChange(key) },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("vocab_filter_$key"),
                        label = { Text(label) }
                    )
                }
            }
        }

        if (activeFilter != "TODAY") {
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(categories) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            modifier = Modifier.minimumInteractiveComponentSize(),
                            label = { Text(cat) }
                        )
                    }
                }
            }
        }

        if (filteredWords.isEmpty()) {
            item {
                Text(
                    text = "No words match this filter yet. Try switching to 'All'!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 24.dp)
                )
            }
        } else {
            items(filteredWords, key = { it.id }) { word ->
                VocabWordCard(
                    word = word,
                    alwaysShowUrdu = alwaysShowUrdu,
                    onSpeakWord = onSpeakText,
                    onSpeakSentence = onSpeakText,
                    onToggleLearned = { onToggleLearned(word) },
                    onToggleSaved = { onToggleSaved(word) }
                )
            }
        }
    }
}

@Composable
fun PhrasesScreen(
    phrases: List<PhraseItemEntity>,
    currentPlanDay: Int,
    activeFilter: String,
    alwaysShowUrdu: Boolean,
    onFilterChange: (String) -> Unit,
    onSpeakText: (String) -> Unit,
    onToggleLearned: (PhraseItemEntity) -> Unit,
    onToggleSaved: (PhraseItemEntity) -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    var localQuery by remember { mutableStateOf("") }

    val filteredPhrases = remember(phrases, activeFilter, currentPlanDay, localQuery) {
        phrases.filter { p ->
            val matchesMode = when (activeFilter) {
                "TODAY" -> p.dayNumber == currentPlanDay
                "IDIOMS" -> p.isIdiom
                "LEARNED" -> p.isLearned
                else -> true
            }
            val q = localQuery.trim()
            val matchesSearch = q.isEmpty() ||
                p.english.contains(q, ignoreCase = true) ||
                p.urdu.contains(q) ||
                p.explanation.contains(q, ignoreCase = true) ||
                p.category.contains(q, ignoreCase = true)
            matchesMode && matchesSearch
        }
    }

    val masteredCount = phrases.count { it.isLearned }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("phrases_screen_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionHeaderBox(
                titleEn = "Phrases & Idioms (${phrases.size})",
                titleUr = "روزمرہ جملے اور محاورے",
                subtitleEn = "Natural spoken English expressions and beginner idioms with Urdu meanings."
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Phrases Mastered", style = MaterialTheme.typography.labelLarge)
                Text(
                    text = "$masteredCount / ${phrases.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { if (phrases.isEmpty()) 0f else masteredCount.toFloat() / phrases.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape)
            )
        }

        item {
            OutlinedTextField(
                value = localQuery,
                onValueChange = { localQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("phrases_search_input"),
                placeholder = { Text("Search phrases or idioms…") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (localQuery.isNotEmpty()) {
                        IconButton(onClick = { localQuery = "" }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear search")
                        }
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )
        }

        item {
            val filters = listOf(
                "TODAY" to "Day $currentPlanDay",
                "ALL" to "All (${phrases.size})",
                "IDIOMS" to "Idioms (${phrases.count { it.isIdiom }})",
                "LEARNED" to "Mastered ($masteredCount)"
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filters) { (key, label) ->
                    FilterChip(
                        selected = activeFilter == key,
                        onClick = { onFilterChange(key) },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("phrase_filter_$key"),
                        label = { Text(label) }
                    )
                }
            }
        }

        items(filteredPhrases, key = { it.id }) { phrase ->
            PhraseItemCard(
                phrase = phrase,
                alwaysShowUrdu = alwaysShowUrdu,
                onSpeakPhrase = onSpeakText,
                onToggleLearned = { onToggleLearned(phrase) },
                onToggleSaved = { onToggleSaved(phrase) }
            )
        }
    }
}
