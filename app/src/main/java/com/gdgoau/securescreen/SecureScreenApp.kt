package com.gdgoau.securescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.gdgoau.securescreen.ui.ExperimentList
import com.gdgoau.securescreen.ui.ExperimentScreen
import com.gdgoau.securescreen.ui.theme.AppTheme

@Composable
fun SecureScreenApp() {
    var selected by rememberSaveable { mutableStateOf<Experiment?>(null) }

    AppTheme {
        when (val experiment = selected) {
            null -> ExperimentList(onSelect = { selected = it })
            else -> ExperimentScreen(experiment, onBack = { selected = null })
        }
    }
}
