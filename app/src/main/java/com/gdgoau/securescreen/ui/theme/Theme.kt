package com.gdgoau.securescreen.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val GoogleBlue = Color(0xFF1A73E8)
val GoogleRed = Color(0xFFD93025)
val GoogleGreen = Color(0xFF188038)

private val Colors = lightColorScheme(
    primary = GoogleBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD3E3FD),
    onPrimaryContainer = Color(0xFF041E49),
    background = Color(0xFFF8FAFD),
    surface = Color(0xFFF8FAFD),
    surfaceContainer = Color(0xFFE9EEF6),
    onSurface = Color(0xFF1F1F1F),
    onSurfaceVariant = Color(0xFF444746),
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Colors, typography = AppTypography, content = content)
}
