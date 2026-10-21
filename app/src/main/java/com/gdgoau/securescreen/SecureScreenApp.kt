package com.gdgoau.securescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.gdgoau.securescreen.ui.ExperimentList
import com.gdgoau.securescreen.ui.ExperimentScreen
import com.gdgoau.securescreen.ui.SplashScreen
import com.gdgoau.securescreen.ui.UpdatePrompt
import com.gdgoau.securescreen.ui.theme.AppTheme
import kotlinx.coroutines.delay

private const val SPLASH_MS = 1_500L

@Composable
fun SecureScreenApp() {
    var selected by rememberSaveable { mutableStateOf<Experiment?>(null) }
    var splashing by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(SPLASH_MS)
        splashing = false
    }

    AppTheme {
        when {
            splashing -> SplashScreen()
            selected == null -> ExperimentList(onSelect = { selected = it })
            else -> ExperimentScreen(checkNotNull(selected), onBack = { selected = null })
        }
        if (!splashing) UpdatePrompt()
    }
}
