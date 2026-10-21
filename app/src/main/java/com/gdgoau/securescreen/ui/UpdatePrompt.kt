package com.gdgoau.securescreen.ui

import android.content.Context
import android.content.Intent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.gdgoau.securescreen.update.Release
import com.gdgoau.securescreen.update.UpdateChecker
import com.gdgoau.securescreen.update.isNewer

private const val SKIPPED = "skipped_version"

@Composable
fun UpdatePrompt() {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("update", Context.MODE_PRIVATE) }
    var dismissed by remember { mutableStateOf(false) }
    val release by produceState<Release?>(null) {
        value = UpdateChecker.latest()?.takeIf {
            isNewer(it.version, context.versionName()) && it.version != prefs.getString(SKIPPED, null)
        }
    }
    val update = release?.takeUnless { dismissed } ?: return

    AlertDialog(
        onDismissRequest = {
            prefs.edit().putString(SKIPPED, update.version).apply()
            dismissed = true
        },
        title = { Text("Update available") },
        text = { Text("Version ${update.version} is out. Update now or skip this version.") },
        confirmButton = {
            TextButton(onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, update.url.toUri()))
                dismissed = true
            }) { Text("Update") }
        },
        dismissButton = {
            TextButton(onClick = {
                prefs.edit().putString(SKIPPED, update.version).apply()
                dismissed = true
            }) { Text("Skip") }
        },
    )
}

private fun Context.versionName() = packageManager.getPackageInfo(packageName, 0).versionName.orEmpty()
