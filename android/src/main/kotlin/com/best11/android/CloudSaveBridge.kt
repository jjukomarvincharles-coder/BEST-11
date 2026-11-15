package com.best11.android

/**
 * Integration seam only. Add Google Play Games Services sign-in and snapshots
 * after configuring OAuth credentials and Play Console services.
 * Offline local saves remain the source of truth until a cloud account links.
 */
class CloudSaveBridge {
    fun isConfigured(): Boolean = false
}
