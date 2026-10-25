package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.PhraseItemEntity
import com.example.data.local.VocabWordEntity
import com.example.ui.theme.LocalTextScale
import com.example.ui.theme.UrduFontFamily

@Composable
fun UrduText(
    text: String,
    modifier: Modifier = Modifier,
    fontSizeSp: Float = 18f,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign = TextAlign.Right
) {
    val scale = LocalTextScale.current
    val scaledSize: TextUnit = (fontSizeSp * scale).sp
    val scaledLineHeight: TextUnit = (fontSizeSp * 2.0f * scale).sp

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Text(
            text = text,
            modifier = modifier.fillMaxWidth(),
            fontFamily = UrduFontFamily,
            fontWeight = fontWeight,
            fontSize = scaledSize,
            lineHeight = scaledLineHeight,
            color = color,
            textAlign = textAlign
        )
    }
}

@Composable
fun VocabWordCard(
    word: VocabWordEntity,
    alwaysShowUrdu: Boolean,
    onSpeakWord: (String) -> Unit,
    onSpeakSentence: (String) -> Unit,
    onToggleLearned: () -> Unit,
    onToggleSaved: () -> Unit,
    modifier: Modifier = Modifier
) {
    var localRevealed by remember(word.id, alwaysShowUrdu) { mutableStateOf(alwaysShowUrdu) }
    val showUrdu = alwaysShowUrdu || localRevealed

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("vocab_card_${word.id}"),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = if (word.isLearned) {
                MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (word.isLearned) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "${word.partOfSpeech} • Day ${word.dayNumber}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledTonalIconButton(
                        onClick = { onSpeakWord(word.english) },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("speak_word_${word.id}")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = stringResource(R.string.cd_listen_audio)
                        )
                    }
                    IconButton(
                        onClick = onToggleSaved,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("save_word_${word.id}")
                    ) {
                        Icon(
                            imageVector = if (word.isSaved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = stringResource(R.string.cd_save_bookmark),
                            tint = if (word.isSaved) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = word.english,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "/${word.pronunciation}/",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Urdu Translation Box (Tap to Reveal or Always Shown)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { localRevealed = !localRevealed }
                    .testTag("reveal_word_urdu_${word.id}"),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (showUrdu) "Urdu Meaning (اردو معنی)" else "Tap to Reveal Urdu Meaning",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            imageVector = if (showUrdu) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = "Toggle Urdu translation",
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    AnimatedVisibility(visible = showUrdu) {
                        Column {
                            Spacer(modifier = Modifier.height(4.dp))
                            UrduText(
                                text = word.urdu,
                                fontSizeSp = 20f,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = word.explanation,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Example Sentence",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(
                            onClick = { onSpeakSentence(word.exampleEn) },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = "Listen to example sentence",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Text(
                        text = "\"${word.exampleEn}\"",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    AnimatedVisibility(visible = showUrdu) {
                        UrduText(
                            text = word.exampleUr,
                            fontSizeSp = 16f,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FilterChip(
                    selected = word.isLearned,
                    onClick = onToggleLearned,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("learned_word_${word.id}"),
                    label = {
                        Text(if (word.isLearned) "Learned (+10 XP)" else "Mark as Learned")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = if (word.isLearned) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                            contentDescription = stringResource(R.string.cd_mark_learned),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun PhraseItemCard(
    phrase: PhraseItemEntity,
    alwaysShowUrdu: Boolean,
    onSpeakPhrase: (String) -> Unit,
    onToggleLearned: () -> Unit,
    onToggleSaved: () -> Unit,
    modifier: Modifier = Modifier
) {
    var localRevealed by remember(phrase.id, alwaysShowUrdu) { mutableStateOf(alwaysShowUrdu) }
    val showUrdu = alwaysShowUrdu || localRevealed

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("phrase_card_${phrase.id}"),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = if (phrase.isLearned) {
                MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (phrase.isLearned) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    color = if (phrase.isIdiom) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = if (phrase.isIdiom) "Idiom • Day ${phrase.dayNumber}" else "${phrase.category} • Day ${phrase.dayNumber}",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (phrase.isIdiom) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledTonalIconButton(
                        onClick = { onSpeakPhrase(phrase.english) },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("speak_phrase_${phrase.id}")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = stringResource(R.string.cd_listen_audio)
                        )
                    }
                    IconButton(
                        onClick = onToggleSaved,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("save_phrase_${phrase.id}")
                    ) {
                        Icon(
                            imageVector = if (phrase.isSaved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = stringResource(R.string.cd_save_bookmark),
                            tint = if (phrase.isSaved) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = phrase.english,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { localRevealed = !localRevealed }
                    .testTag("reveal_phrase_urdu_${phrase.id}"),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (showUrdu) "Urdu Translation (اردو ترجمہ)" else "Tap to Reveal Urdu Meaning",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            imageVector = if (showUrdu) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = "Reveal translation",
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    AnimatedVisibility(visible = showUrdu) {
                        Column {
                            Spacer(modifier = Modifier.height(4.dp))
                            UrduText(
                                text = phrase.urdu,
                                fontSizeSp = 19f,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = phrase.explanation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.30f),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "In Conversation:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(
                            onClick = { onSpeakPhrase(phrase.exampleEn) },
                            modifier = Modifier.size(34.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = "Listen to phrase example",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    Text(
                        text = "\"${phrase.exampleEn}\"",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    AnimatedVisibility(visible = showUrdu) {
                        UrduText(
                            text = phrase.exampleUr,
                            fontSizeSp = 15f,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FilterChip(
                    selected = phrase.isLearned,
                    onClick = onToggleLearned,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("learned_phrase_${phrase.id}"),
                    label = { Text(if (phrase.isLearned) "Mastered (+10 XP)" else "Mark Mastered") },
                    leadingIcon = {
                        Icon(
                            imageVector = if (phrase.isLearned) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                            contentDescription = stringResource(R.string.cd_mark_learned),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun SectionHeaderBox(
    titleEn: String,
    titleUr: String,
    subtitleEn: String? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = titleEn,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(0.9f)) {
                UrduText(
                    text = titleUr,
                    fontSizeSp = 17f,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        if (subtitleEn != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitleEn,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
