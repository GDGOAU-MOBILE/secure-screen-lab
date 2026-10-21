package com.gdgoau.securescreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gdgoau.securescreen.Experiment
import com.gdgoau.securescreen.ui.theme.CodeFont
import com.gdgoau.securescreen.ui.theme.Tone
import com.gdgoau.securescreen.ui.theme.tone

@Composable
fun WhySheet(experiment: Experiment) {
    val tone = experiment.tone
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.verticalScroll(rememberScrollState()).navigationBarsPadding().padding(horizontal = 24.dp),
    ) {
        Column {
            Text("Why is this happening?", style = MaterialTheme.typography.titleLarge)
            Text("Drag up to learn", style = MaterialTheme.typography.bodyMedium, color = experiment.tone.accent)
        }
        Section("Try it", tone) {
            experiment.steps.forEachIndexed { index, step -> Step(index + 1, step, tone) }
        }
        Section("What you will see", tone) { Text(experiment.result, style = MaterialTheme.typography.bodyLarge) }
        Section("Why", tone) { Text(experiment.why, style = MaterialTheme.typography.bodyLarge) }
        Section("The code", tone) { CodeBlock(experiment.code) }
    }
}

@Composable
private fun Section(title: String, tone: Tone, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = MaterialTheme.typography.labelLarge, color = tone.accent)
        content()
    }
}

@Composable
private fun Step(number: Int, text: String, tone: Tone) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(28.dp).clip(CircleShape).background(tone.container),
        ) {
            Text("$number", style = MaterialTheme.typography.labelLarge, color = tone.onContainer)
        }
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun CodeBlock(code: String) {
    Text(
        text = code,
        fontFamily = CodeFont,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(16.dp),
    )
}
