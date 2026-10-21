package com.gdgoau.securescreen.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gdgoau.securescreen.R

@OptIn(ExperimentalTextApi::class)
private fun googleSans(vararg weights: FontWeight) = FontFamily(
    weights.map { weight ->
        Font(R.font.google_sans_flex, weight, variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)))
    },
)

@OptIn(ExperimentalTextApi::class)
val CodeFont = FontFamily(
    Font(R.font.google_sans_code, FontWeight.Normal, variationSettings = FontVariation.Settings(FontVariation.weight(400))),
)

private val GoogleSans = googleSans(FontWeight.Normal, FontWeight.Medium, FontWeight.Bold)

val AppTypography = with(Typography()) {
    copy(
        displaySmall = displaySmall.copy(fontFamily = GoogleSans, fontWeight = FontWeight.Bold, letterSpacing = (-0.5).sp),
        headlineMedium = headlineMedium.copy(fontFamily = GoogleSans),
        titleMedium = titleMedium.copy(fontFamily = GoogleSans),
        titleLarge = titleLarge.copy(fontFamily = GoogleSans),
        bodyLarge = bodyLarge.copy(fontFamily = GoogleSans),
        bodyMedium = bodyMedium.copy(fontFamily = GoogleSans),
        labelLarge = labelLarge.copy(fontFamily = GoogleSans),
    )
}
