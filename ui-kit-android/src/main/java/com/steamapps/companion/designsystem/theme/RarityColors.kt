package com.steamapps.companion.designsystem.theme

import androidx.compose.ui.graphics.Color

fun resolveItemRarityColor(
    rarityName: String?,
    rarityColorHex: String? = null,
    fallback: Color? = null,
): Color {
    parseHexColor(rarityColorHex)?.let { return it }
    rarityName?.takeIf { it.isNotBlank() }?.let { return itemRarityColorByName(it) }
    return fallback ?: Palette.RarityUnknown
}

fun itemRarityColorByName(name: String): Color {
    val key = name.lowercase().substringBefore(" grade").trim()
    return when {
        // item grades
        key == "consumer grade" -> Color(0xFFB0C3D9)
        key == "industrial grade" -> Color(0xFF5E98D9)
        key == "mil-spec grade" || key == "mil-spec" -> Color(0xFF4B69FF)
        key == "restricted" -> Color(0xFF8847FF)
        key == "classified" -> Color(0xFFD32CE6)
        key == "covert" -> Color(0xFFEB4B4B)
        key == "extraordinary" || key == "contraband" -> Color(0xFFE4AE39)
        // Dota 2
        key == "common" -> Color(0xFF9DA1A9)
        key == "uncommon" -> Color(0xFF5CB85C)
        key == "rare" -> Color(0xFF4B69FF)
        key == "mythical" -> Color(0xFF8847FF)
        key == "legendary" -> Color(0xFFD32CE6)
        key == "immortal" -> Color(0xFFE4AE39)
        key == "arcana" -> Color(0xFF00FFD0)
        key == "ancient" -> Color(0xFFEB4B4B)
        else -> Palette.RarityUnknown
    }
}
