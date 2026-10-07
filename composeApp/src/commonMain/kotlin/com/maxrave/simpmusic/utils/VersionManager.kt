package com.maxrave.simpmusic.utils

import com.maxrave.simpmusic.BuildKonfig

object VersionManager {
    private var versionName: String? = null

    fun initialize() {
        if (versionName == null) {
            versionName =
                try {
                    BuildKonfig.versionName
                } catch (_: Exception) {
                    String()
                }
        }
    }

    fun getVersionName(): String = removeDevSuffix(versionName ?: String())

    private fun removeDevSuffix(versionName: String): String {
        return if (versionName.endsWith("-dev")) {
            versionName.replace("-dev", "")
        } else {
            versionName
        }
    }

    /**
     * Determines whether [latestVersionStr] is strictly newer than [currentVersionStr].
     * Accurately compares semantic versions (2.2.0), build numbers (-b147), and timestamps.
     * Prevents prompting for updates when the current build is equal or newer.
     */
    fun isNewerVersion(latestVersionStr: String, currentVersionStr: String = getVersionName()): Boolean {
        val latest = latestVersionStr.removePrefix("v").trim()
        val current = currentVersionStr.removePrefix("v").trim()

        if (latest.isEmpty() || current.isEmpty()) return false
        if (latest.equals(current, ignoreCase = true)) return false

        // Compare base semver numbers: e.g. "2.2.0"
        val latestBase = latest.substringBefore("-").split(".").mapNotNull { it.toIntOrNull() }
        val currentBase = current.substringBefore("-").split(".").mapNotNull { it.toIntOrNull() }

        val maxParts = maxOf(latestBase.size, currentBase.size)
        for (i in 0 until maxParts) {
            val lPart = latestBase.getOrElse(i) { 0 }
            val cPart = currentBase.getOrElse(i) { 0 }
            if (lPart > cPart) return true
            if (lPart < cPart) return false
        }

        // Base semvers are equal (e.g. both are 2.2.0). Compare build numbers (-b147 vs -b148).
        val buildRegex = Regex("-b(\\d+)")
        val latestBuild = buildRegex.find(latest)?.groupValues?.get(1)?.toIntOrNull()
        val currentBuild = buildRegex.find(current)?.groupValues?.get(1)?.toIntOrNull()

        if (latestBuild != null && currentBuild != null) {
            return latestBuild > currentBuild
        }

        // Compare timestamps if present (e.g. -202610071545)
        val timestampRegex = Regex("(\\d{12,14})")
        val latestTimestamp = timestampRegex.find(latest)?.value?.toLongOrNull()
        val currentTimestamp = timestampRegex.find(current)?.value?.toLongOrNull()

        if (latestTimestamp != null && currentTimestamp != null) {
            return latestTimestamp > currentTimestamp
        }

        // Clean release (e.g. "2.2.0") is newer than pre-release ("2.2.0-b147")
        val latestHasSuffix = latest.contains("-")
        val currentHasSuffix = current.contains("-")
        if (!latestHasSuffix && currentHasSuffix) return true
        if (latestHasSuffix && !currentHasSuffix) return false

        return latest.compareTo(current, ignoreCase = true) > 0
    }
}