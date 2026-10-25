package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.R

val EnglishFontFamily = FontFamily(
    Font(R.font.plus_jakarta_sans, FontWeight.Normal),
    Font(R.font.plus_jakarta_sans, FontWeight.Medium),
    Font(R.font.plus_jakarta_sans, FontWeight.SemiBold),
    Font(R.font.plus_jakarta_sans, FontWeight.Bold)
)

val UrduFontFamily = FontFamily(
    Font(R.font.noto_nastaliq_urdu, FontWeight.Normal),
    Font(R.font.noto_nastaliq_urdu, FontWeight.Bold)
)

fun buildScaledTypography(scale: Float = 1.0f): Typography = Typography(
    displaySmall = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = (32 * scale).sp,
        lineHeight = (40 * scale).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = (28 * scale).sp,
        lineHeight = (36 * scale).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = (24 * scale).sp,
        lineHeight = (32 * scale).sp
    ),
    headlineSmall = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = (20 * scale).sp,
        lineHeight = (28 * scale).sp
    ),
    titleLarge = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = (19 * scale).sp,
        lineHeight = (26 * scale).sp
    ),
    titleMedium = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = (17 * scale).sp,
        lineHeight = (24 * scale).sp
    ),
    titleSmall = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (15 * scale).sp,
        lineHeight = (22 * scale).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = (17 * scale).sp,
        lineHeight = (25 * scale).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = (15 * scale).sp,
        lineHeight = (22 * scale).sp
    ),
    bodySmall = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = (13 * scale).sp,
        lineHeight = (19 * scale).sp
    ),
    labelLarge = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = (14 * scale).sp,
        lineHeight = (20 * scale).sp
    ),
    labelMedium = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (12 * scale).sp,
        lineHeight = (16 * scale).sp
    ),
    labelSmall = TextStyle(
        fontFamily = EnglishFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (11 * scale).sp,
        lineHeight = (16 * scale).sp
    )
)

val Typography = buildScaledTypography(1.0f)
