package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = RoyalBluePrimary,
    onPrimary = OnRoyalBluePrimary,
    primaryContainer = RoyalBlueContainer,
    onPrimaryContainer = OnRoyalBlueContainer,
    secondary = SkyBlueSecondary,
    onSecondary = OnSkyBlueSecondary,
    secondaryContainer = SkyBlueContainer,
    onSecondaryContainer = OnSkyBlueContainer,
    tertiary = AmberTertiary,
    onTertiary = OnAmberTertiary,
    tertiaryContainer = AmberContainer,
    onTertiaryContainer = OnAmberContainer,
    background = LightBackground,
    onBackground = OnLightBackground,
    surface = LightSurface,
    onSurface = OnLightSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = OnLightSurfaceVariant,
    outline = LightOutline,
    error = ErrorRed,
    onError = OnErrorRed,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkBluePrimary,
    onPrimary = OnDarkBluePrimary,
    primaryContainer = DarkBlueContainer,
    onPrimaryContainer = OnDarkBlueContainer,
    secondary = DarkSkySecondary,
    onSecondary = OnDarkSkySecondary,
    secondaryContainer = DarkSkyContainer,
    onSecondaryContainer = OnDarkSkyContainer,
    tertiary = DarkAmberTertiary,
    onTertiary = OnDarkAmberTertiary,
    tertiaryContainer = DarkAmberContainer,
    onTertiaryContainer = OnDarkAmberContainer,
    background = DarkBackground,
    onBackground = OnDarkBackground,
    surface = DarkSurface,
    onSurface = OnDarkSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = OnDarkSurfaceVariant,
    outline = DarkOutline,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark
)

val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

val LocalTextScale = staticCompositionLocalOf { 1.0f }

@Composable
fun EnglishCoachTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    textScale: Float = 1.0f,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val typography = buildScaledTypography(textScale)

    CompositionLocalProvider(LocalTextScale provides textScale) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = AppShapes,
            content = content
        )
    }
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    EnglishCoachTheme(darkTheme = darkTheme, content = content)
}
