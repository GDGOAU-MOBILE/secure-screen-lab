package com.gdgoau.securescreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gdgoau.securescreen.Experiment
import com.gdgoau.securescreen.ui.theme.tone

private val CardShape = RoundedCornerShape(topStart = 40.dp, topEnd = 12.dp, bottomEnd = 40.dp, bottomStart = 12.dp)

@Composable
fun ExperimentList(onSelect: (Experiment) -> Unit) {
    Scaffold { padding ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(padding),
        ) {
            item {
                Column(Modifier.padding(bottom = 16.dp)) {
                    Text("Secure Screen Lab", style = MaterialTheme.typography.displaySmall)
                    Text(
                        text = "How Android decides who gets to see your screen.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            items(Experiment.entries) { ExperimentCard(it, onClick = { onSelect(it) }) }
        }
    }
}

@Composable
private fun ExperimentCard(experiment: Experiment, onClick: () -> Unit) {
    val tone = experiment.tone

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(CardShape)
            .background(tone.container)
            .clickable(onClick = onClick)
            .padding(24.dp),
    ) {
        IconBadge(experiment.icon, tone.accent)
        Column(Modifier.weight(1f)) {
            Text(experiment.title, style = MaterialTheme.typography.titleLarge, color = tone.onContainer)
            Text(experiment.summary, style = MaterialTheme.typography.bodyMedium, color = tone.onContainer)
        }
        Text(experiment.number, style = MaterialTheme.typography.displaySmall, color = tone.accent)
    }
}
