package com.gdgoau.securescreen.ui.body

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gdgoau.securescreen.rememberCaptureLog

@Composable
fun ObserverBody() {
    val log = rememberCaptureLog()
    val empty = if (SDK_INT >= 34) "No captures yet. Take a screenshot." else "Capture callbacks need Android 14 or newer."

    Text("${log.size} ${if (log.size == 1) "capture" else "captures"} observed", style = MaterialTheme.typography.titleLarge)
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(20.dp),
    ) {
        if (log.isEmpty()) Text(empty, color = MaterialTheme.colorScheme.onSurfaceVariant)
        log.take(5).forEach { Text(it, style = MaterialTheme.typography.bodyLarge) }
    }
}
