package com.gdgoau.securescreen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gdgoau.securescreen.ui.theme.tone
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gdgoau.securescreen.Experiment
import com.gdgoau.securescreen.R
import com.gdgoau.securescreen.SecureWindow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperimentScreen(experiment: Experiment, onBack: () -> Unit) {
    SecureWindow(enabled = experiment.secure)
    BackHandler(onBack = onBack)

    BottomSheetScaffold(
        sheetPeekHeight = 120.dp,
        sheetContent = { WhySheet(experiment) },
        topBar = {
            TopAppBar(
                title = { Text(experiment.title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(padding).padding(16.dp),
        ) {
            StatusBanner(experiment)
            Image(
                painter = painterResource(R.drawable.sample_art),
                contentDescription = "Sample artwork",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(3f / 2f).clip(MaterialTheme.shapes.extraLarge),
            )
            Text(
                text = "Take a screenshot, record the screen, then open recent apps.",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun StatusBanner(experiment: Experiment) {
    val tone = experiment.tone

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clip(MaterialTheme.shapes.extraLarge).background(tone.container).padding(16.dp),
    ) {
        IconBadge(experiment.icon, tone.accent)
        Column {
            Text(tone.label, style = MaterialTheme.typography.titleLarge, color = tone.onContainer)
            Text("FLAG_SECURE is ${if (experiment.secure) "on" else "off"}", color = tone.onContainer)
        }
    }
}
