package com.gdgoau.securescreen.ui.theme

import androidx.compose.ui.graphics.Color
import com.gdgoau.securescreen.Experiment

class Tone(val container: Color, val onContainer: Color, val accent: Color, val label: String)

private val Exposed = Tone(Color(0xFFFCE8E6), Color(0xFF5F1410), GoogleRed, "Exposed")
private val Protected = Tone(Color(0xFFE6F4EA), Color(0xFF0D3B1E), GoogleGreen, "Protected")

private val Watching = Tone(Color(0xFFD3E3FD), Color(0xFF041E49), GoogleBlue, "Watching")
private val Inspecting = Tone(Color(0xFFFEF7E0), Color(0xFF3D2A00), Color(0xFFB06000), "Inspecting")

val Experiment.tone
    get() = when {
        this == Experiment.Observer -> Watching
        this == Experiment.Drm -> Inspecting
        secure -> Protected
        else -> Exposed
    }
