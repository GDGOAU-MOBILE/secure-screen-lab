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
}
