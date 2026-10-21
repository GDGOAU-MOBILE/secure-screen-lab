package com.gdgoau.securescreen

import android.app.Activity
import android.os.Build.VERSION.SDK_INT
import android.view.WindowManager
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.function.Consumer

private val Clock = DateTimeFormatter.ofPattern("HH:mm:ss")

/** Newest-first log of capture events the OS reports while this composable is shown. */
@Composable
fun rememberCaptureLog(): List<String> {
    val activity = checkNotNull(LocalActivity.current)
    val log = remember { mutableStateListOf<String>() }

    DisposableEffect(activity) {
        val record = { event: String -> log.add(0, "${LocalTime.now().format(Clock)}  $event") }
        val stops = buildList {
            if (SDK_INT >= 34) add(activity.observeScreenshots { record("Screenshot taken") })
            if (SDK_INT >= 35) add(activity.observeRecording { record("Screen recording started") })
        }
        onDispose { stops.forEach { it() } }
    }
    return log
}

@RequiresApi(34)
private fun Activity.observeScreenshots(onEvent: () -> Unit): () -> Unit {
    val callback = Activity.ScreenCaptureCallback { onEvent() }
    registerScreenCaptureCallback(mainExecutor, callback)
    return { unregisterScreenCaptureCallback(callback) }
}

@RequiresApi(35)
private fun Activity.observeRecording(onEvent: () -> Unit): () -> Unit {
    val callback = Consumer<Int> { if (it == WindowManager.SCREEN_RECORDING_STATE_VISIBLE) onEvent() }
    windowManager.addScreenRecordingCallback(mainExecutor, callback)
    return { windowManager.removeScreenRecordingCallback(callback) }
}
