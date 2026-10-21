package com.gdgoau.securescreen

import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_SecurePlayback
import android.media.MediaCodecList
import android.media.MediaDrm
import java.util.UUID

private val Widevine = UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L)

data class DrmInfo(val widevineLevel: String?, val secureDecoder: Boolean)

@Suppress("DEPRECATION")
fun readDrmInfo(): DrmInfo {
    val level = runCatching {
        val drm = MediaDrm(Widevine)
        try {
            drm.getPropertyString("securityLevel")
        } finally {
            drm.release()
        }
    }.getOrNull()

    val secureDecoder = MediaCodecList(MediaCodecList.ALL_CODECS).codecInfos.any { codec ->
        !codec.isEncoder && runCatching {
            codec.getCapabilitiesForType("video/avc").isFeatureSupported(FEATURE_SecurePlayback)
        }.getOrDefault(false)
    }
    return DrmInfo(level, secureDecoder)
}
