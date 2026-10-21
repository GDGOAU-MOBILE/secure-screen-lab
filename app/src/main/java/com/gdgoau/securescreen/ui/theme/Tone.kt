package com.gdgoau.securescreen.ui.theme

import androidx.compose.ui.graphics.Color
import com.gdgoau.securescreen.Experiment

class Tone(val container: Color, val onContainer: Color, val accent: Color, val label: String)

private val Exposed = Tone(Color(0xFFFCE8E6), Color(0xFF5F1410), GoogleRed, "Exposed")
private val Protected = Tone(Color(0xFFE6F4EA), Color(0xFF0D3B1E), GoogleGreen, "Protected")

val Experiment.tone get() = if (secure) Protected else Exposed
