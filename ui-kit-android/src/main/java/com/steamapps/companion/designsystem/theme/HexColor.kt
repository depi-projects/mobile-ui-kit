package com.steamapps.companion.designsystem.theme

import androidx.compose.ui.graphics.Color

fun parseHexColor(hex: String?): Color? {
    if (hex.isNullOrBlank() || !hex.startsWith("#")) return null
    return runCatching {
        val raw = hex.removePrefix("#")
        val value = raw.toLong(16)
        when (raw.length) {
            6 -> Color(0xFF000000 or value)
            8 -> Color(value)
            else -> null
        }
    }.getOrNull()
}
