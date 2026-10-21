package com.gdgoau.securescreen

import android.view.WindowManager.LayoutParams.FLAG_SECURE
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

/** Applies FLAG_SECURE while this composable is on screen and clears it on exit. */
@Composable
fun SecureWindow(enabled: Boolean) {
    val window = checkNotNull(LocalActivity.current).window

    DisposableEffect(enabled) {
        if (enabled) window.addFlags(FLAG_SECURE)
        onDispose { window.clearFlags(FLAG_SECURE) }
    }
}
