package com.gdgoau.securescreen.update

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

data class Release(val version: String, val url: String)

object UpdateChecker {
    private const val LATEST = "https://api.github.com/repos/GDGOAU-MOBILE/secure-screen-lab/releases/latest"
    private const val TIMEOUT_MS = 5_000

    suspend fun latest(): Release? = withContext(Dispatchers.IO) {
        runCatching {
            val connection = URL(LATEST).openConnection().apply {
                connectTimeout = TIMEOUT_MS
                readTimeout = TIMEOUT_MS
            }
            val json = JSONObject(connection.getInputStream().bufferedReader().readText())
            Release(json.getString("tag_name").removePrefix("v"), json.getString("html_url"))
        }.getOrNull()
    }
}

fun isNewer(candidate: String, current: String): Boolean {
    val a = candidate.split(".").map { it.toIntOrNull() ?: 0 }
    val b = current.split(".").map { it.toIntOrNull() ?: 0 }
    for (i in 0 until maxOf(a.size, b.size)) {
        val diff = a.getOrElse(i) { 0 } - b.getOrElse(i) { 0 }
        if (diff != 0) return diff > 0
    }
    return false
}
