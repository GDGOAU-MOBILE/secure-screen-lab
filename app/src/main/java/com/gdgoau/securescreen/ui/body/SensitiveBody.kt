package com.gdgoau.securescreen.ui.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gdgoau.securescreen.ui.theme.GoogleBlue

private const val SAMPLE_CARD = "4111 1111 1111 1111"

@Composable
fun SensitiveBody() {
    var revealed by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.extraLarge)
            .background(GoogleBlue)
            .padding(24.dp),
    ) {
        Text("Sample account, not real data", style = MaterialTheme.typography.labelLarge, color = Color.White)
        Text(
            text = if (revealed) SAMPLE_CARD else "•••• •••• •••• 1111",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Balance: NGN 1,250,000.00", color = Color.White, modifier = Modifier.weight(1f))
            TextButton(onClick = { revealed = !revealed }) {
                Text(if (revealed) "Hide" else "Reveal", color = Color.White)
            }
        }
    }
}
