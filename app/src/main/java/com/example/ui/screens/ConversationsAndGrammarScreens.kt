package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.local.ConversationEntity
import com.example.data.local.GrammarLessonEntity
import com.example.ui.components.SectionHeaderBox
import com.example.ui.components.UrduText

@Composable
fun ConversationsScreen(
    conversations: List<ConversationEntity>,
    selectedTopic: String,
    alwaysShowUrdu: Boolean,
    onSelectTopic: (String) -> Unit,
    onSpeakLine: (String) -> Unit,
    onPlayFullDialogue: (List<String>) -> Unit,
    onCompleteOrRepeat: (ConversationEntity) -> Unit,
    onPracticeSpeakingTopic: (String) -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    val topics = remember { listOf("All Topics") + CurriculumCatalog.speakingTopics }
    val filtered = remember(conversations, selectedTopic) {
        if (selectedTopic == "All Topics") conversations
        else conversations.filter { it.topic == selectedTopic }
    }
    var expandedId by remember { mutableStateOf<Int?>(conversations.firstOrNull()?.id) }
    val completedCount = conversations.count { it.isCompleted }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("conversations_screen_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionHeaderBox(
                titleEn = "Everyday Conversations (${conversations.size})",
                titleUr = "روزمرہ زندگی کے مکالمے",
                subtitleEn = "21 real-world dialogues across 7 practical topics with audio playback."
            )
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = { if (conversations.isEmpty()) 0f else completedCount.toFloat() / conversations.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$completedCount of ${conversations.size} dialogues practiced",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(topics) { topic ->
                    FilterChip(
                        selected = selectedTopic == topic,
                        onClick = { onSelectTopic(topic) },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("conv_topic_${topic.lowercase().replace(' ', '_')}"),
                        label = { Text(topic) }
                    )
                }
            }
        }

        items(filtered, key = { it.id }) { conv ->
            ConversationCard(
                conversation = conv,
                isExpanded = expandedId == conv.id,
                alwaysShowUrdu = alwaysShowUrdu,
                onToggleExpand = { expandedId = if (expandedId == conv.id) null else conv.id },
                onSpeakLine = onSpeakLine,
                onPlayFull = {
                    val lines = conv.parsedTurns().map { "${it.speaker} says: ${it.english}" }
                    onPlayFullDialogue(lines)
                },
                onCompleteOrRepeat = { onCompleteOrRepeat(conv) },
                onPracticeSpeaking = { onPracticeSpeakingTopic(conv.topic) }
            )
        }
    }
}

@Composable
private fun ConversationCard(
    conversation: ConversationEntity,
    isExpanded: Boolean,
    alwaysShowUrdu: Boolean,
    onToggleExpand: () -> Unit,
    onSpeakLine: (String) -> Unit,
    onPlayFull: () -> Unit,
    onCompleteOrRepeat: () -> Unit,
    onPracticeSpeaking: () -> Unit
) {
    var showTranslations by remember(conversation.id, alwaysShowUrdu) { mutableStateOf(alwaysShowUrdu) }
    val turns = remember(conversation.turnsPacked) { conversation.parsedTurns() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("conversation_card_${conversation.id}"),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = 1.dp,
            color = if (conversation.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggleExpand),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = conversation.topic + if (conversation.timesPracticed > 0) " • Practiced ${conversation.timesPracticed}x" else "",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = conversation.titleEn,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    UrduText(
                        text = conversation.titleUr,
                        fontSizeSp = 16f,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) "Collapse dialogue" else "Expand dialogue",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilledTonalButton(
                            onClick = onPlayFull,
                            modifier = Modifier
                                .weight(1f)
                                .minimumInteractiveComponentSize()
                                .testTag("play_dialogue_${conversation.id}")
                        ) {
                            Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Play All")
                        }
                        OutlinedButton(
                            onClick = { showTranslations = !showTranslations },
                            modifier = Modifier
                                .weight(1f)
                                .minimumInteractiveComponentSize()
                                .testTag("toggle_dialogue_urdu_${conversation.id}")
                        ) {
                            Icon(
                                imageVector = if (showTranslations) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (showTranslations) "Hide Urdu" else "Show Urdu")
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    turns.forEachIndexed { idx, turn ->
                        val isFirstSpeaker = idx % 2 == 0
                        Surface(
                            color = if (isFirstSpeaker) {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant
                            },
                            shape = RoundedCornerShape(
                                topStart = 14.dp,
                                topEnd = 14.dp,
                                bottomEnd = if (isFirstSpeaker) 14.dp else 4.dp,
                                bottomStart = if (isFirstSpeaker) 4.dp else 14.dp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = turn.speaker,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    IconButton(
                                        onClick = { onSpeakLine(turn.english) },
                                        modifier = Modifier.size(34.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                            contentDescription = "Listen to ${turn.speaker}",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = turn.english,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                AnimatedVisibility(visible = showTranslations) {
                                    UrduText(
                                        text = turn.urdu,
                                        fontSizeSp = 15f,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = onPracticeSpeaking,
                            modifier = Modifier
                                .weight(1f)
                                .minimumInteractiveComponentSize()
                        ) {
                            Icon(Icons.Filled.Mic, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Speak")
                        }
                        Button(
                            onClick = onCompleteOrRepeat,
                            modifier = Modifier
                                .weight(1.3f)
                                .minimumInteractiveComponentSize()
                                .testTag("complete_dialogue_${conversation.id}")
                        ) {
                            Icon(
                                imageVector = if (conversation.isCompleted) Icons.Filled.Replay else Icons.Filled.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (conversation.isCompleted) "Repeat (+20 XP)" else "Mark Done (+20 XP)")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GrammarScreen(
    lessons: List<GrammarLessonEntity>,
    currentPlanDay: Int,
    onSpeakExample: (String) -> Unit,
    onCompleteOrRepeatLesson: (GrammarLessonEntity) -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    var query by remember { mutableStateOf("") }
    var expandedLessonId by remember { mutableStateOf<Int?>(currentPlanDay) }

    val filtered = remember(lessons, query) {
        val q = query.trim()
        if (q.isEmpty()) lessons
        else lessons.filter {
            it.titleEn.contains(q, ignoreCase = true) ||
                it.titleUr.contains(q) ||
                it.explanationEn.contains(q, ignoreCase = true) ||
                it.formula.contains(q, ignoreCase = true)
        }
    }

    val completedCount = lessons.count { it.isCompleted }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("grammar_screen_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionHeaderBox(
                titleEn = "Grammar Lessons (${lessons.size})",
                titleUr = "آسان انگریزی گرامر",
                subtitleEn = "30 clear beginner rules explained in plain English and Urdu with formulas."
            )
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = { if (lessons.isEmpty()) 0f else completedCount.toFloat() / lessons.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$completedCount / ${lessons.size} Grammar Lessons Completed",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("grammar_search_input"),
                placeholder = { Text("Search tenses, pronouns, articles…") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )
        }

        items(filtered, key = { it.id }) { lesson ->
            GrammarLessonCard(
                lesson = lesson,
                isExpanded = expandedLessonId == lesson.id,
                onToggleExpand = { expandedLessonId = if (expandedLessonId == lesson.id) null else lesson.id },
                onSpeakExample = onSpeakExample,
                onCompleteOrRepeat = { onCompleteOrRepeatLesson(lesson) }
            )
        }
    }
}

@Composable
private fun GrammarLessonCard(
    lesson: GrammarLessonEntity,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onSpeakExample: (String) -> Unit,
    onCompleteOrRepeat: () -> Unit
) {
    val examples = remember(lesson.examplesPacked) { lesson.parsedExamples() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("grammar_card_${lesson.id}"),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = 1.dp,
            color = if (lesson.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggleExpand),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = if (lesson.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Lesson ${lesson.id}" + if (lesson.timesRepeated > 0) " • Repeated ${lesson.timesRepeated}x" else "",
                                style = MaterialTheme.typography.labelMedium,
                                color = if (lesson.isCompleted) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = lesson.titleEn,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    UrduText(
                        text = lesson.titleUr,
                        fontSizeSp = 16f,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = "Expand grammar lesson",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Pattern Formula",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = lesson.formula,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = lesson.explanationEn,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UrduText(
                        text = lesson.explanationUr,
                        fontSizeSp = 16f,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Natural Examples",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    examples.forEach { ex ->
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = ex.english,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(
                                        onClick = { onSpeakExample(ex.english) },
                                        modifier = Modifier.size(34.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                            contentDescription = "Listen example",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                                UrduText(
                                    text = ex.urdu,
                                    fontSizeSp = 15f,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Coach Tip for Urdu Speakers
                    Surface(
                        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f),
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Lightbulb,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Coach Tip for Urdu Speakers",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            UrduText(
                                text = lesson.urduTip,
                                fontSizeSp = 15f,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onCompleteOrRepeat,
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("complete_grammar_${lesson.id}")
                    ) {
                        Icon(
                            imageVector = if (lesson.isCompleted) Icons.Filled.Replay else Icons.Filled.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            if (lesson.isCompleted) "Repeat Grammar Lesson (+20 XP)"
                            else "Mark Lesson Completed (+20 XP)"
                        )
                    }
                }
            }
        }
    }
}
