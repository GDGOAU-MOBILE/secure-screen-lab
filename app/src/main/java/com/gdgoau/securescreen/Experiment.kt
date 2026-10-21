package com.gdgoau.securescreen

import androidx.annotation.DrawableRes

enum class Experiment(
    val number: String,
    val title: String,
    val summary: String,
    val secure: Boolean,
    @DrawableRes val icon: Int,
    val steps: List<String>,
    val result: String,
    val why: String,
    val code: String,
) {
    Normal(
        number = "01",
        title = "The Normal Screen",
        summary = "No protection. Capture works.",
        secure = false,
        icon = R.drawable.ic_screenshot_monitor,
        steps = listOf(
            "Take a screenshot with power and volume down.",
            "Start the screen recorder from quick settings.",
            "Open recent apps and look at this app's preview.",
        ),
        result = "All three show the artwork exactly as it appears.",
        why = "The system draws every window into the screenshot buffer. " +
            "Nothing marks this window as private, so nothing is left out.",
        code = "window.clearFlags(FLAG_SECURE)",
    ),
    Secure(
        number = "02",
        title = "The Secure Screen",
        summary = "FLAG_SECURE blocks capture.",
        secure = true,
        icon = R.drawable.ic_shield_lock,
        steps = listOf(
            "Take a screenshot with power and volume down.",
            "Start the screen recorder from quick settings.",
            "Open recent apps and look at this app's preview.",
        ),
        result = "The screenshot is blocked or black, the recording shows black, " +
            "and the recents card is blank.",
        why = "FLAG_SECURE marks the window as secure. The system compositor skips it " +
            "when building screenshots, recordings and recents thumbnails.",
        code = "window.addFlags(FLAG_SECURE)\n// on leaving the screen\nwindow.clearFlags(FLAG_SECURE)",
    ),
    Observer(
        number = "03",
        title = "The Capture Observer",
        summary = "The app is told. It cannot stop it.",
        secure = false,
        icon = R.drawable.ic_radar,
        steps = listOf(
            "Keep this screen open.",
            "Take a screenshot with power and volume down.",
            "Start a screen recording, then check the log.",
        ),
        result = "The log gains a line for each capture, and the screenshot is still saved. " +
            "The app is only told, never asked.",
        why = "Android notifies the app after a screenshot (Android 14) or when recording starts " +
            "(Android 15). The callback carries no image and cannot cancel anything. " +
            "Detection is not prevention.",
        code = "activity.registerScreenCaptureCallback(executor) {\n    log(\"Screenshot taken\")\n}",
    ),
    Sensitive(
        number = "04",
        title = "The Sensitive Screen",
        summary = "Protection that follows the screen.",
        secure = true,
        icon = R.drawable.ic_lock_person,
        steps = listOf(
            "Tap Reveal on the card, then take a screenshot.",
            "Go back to the list and take another screenshot.",
            "Open this screen again and compare.",
        ),
        result = "Capture is blocked while this screen is open and works again on the list.",
        why = "The flag belongs to this screen. It is added when the screen appears and cleared " +
            "when it leaves, so protection never leaks onto screens with nothing to hide.",
        code = "DisposableEffect(Unit) {\n    window.addFlags(FLAG_SECURE)\n    onDispose { window.clearFlags(FLAG_SECURE) }\n}",
    ),
    Drm(
        number = "05",
        title = "The DRM Deep Dive",
        summary = "Why a flag is not a DRM system.",
        secure = false,
        icon = R.drawable.ic_encrypted,
        steps = listOf(
            "Read the Widevine level reported below.",
            "Compare it with another phone.",
            "Try recording a streaming app on this device.",
        ),
        result = "L1 means decryption and decoding happen in trusted hardware. " +
            "L3 is software only, so streaming apps limit quality.",
        why = "FLAG_SECURE hides a window, but the video bytes still pass through normal memory. " +
            "Widevine L1 keeps them encrypted until a secure decoder writes to a protected " +
            "display path that apps and recorders cannot reach.",
        code = "MediaDrm(WIDEVINE_UUID)\n    .getPropertyString(\"securityLevel\")",
    ),
}
