package com.steamapps.companion.designsystem.theme

import androidx.compose.ui.graphics.Color

/** Counter-Strike 2 / Steam-inspired palette (original app styling, not Valve assets). */
object Palette {
    val Navy = Color(0xFF1B2838)
    val NavyLight = Color(0xFF2A475E)
    val Charcoal = Color(0xFF0E1419)
    val CharcoalElevated = Color(0xFF171D25)
    val CharcoalPanel = Color(0xFF1E252D)

    /** App accent. Same coral as the Home prototype. */
    val Coral = Color(0xFFFF4D67)
    val TacticalOrange = Coral
    val TacticalOrangeDim = Color(0xFFE68600)
    val CoralChip = Color(0xFFFFE8EC)
    val Mint = Color(0xFFD4F5E4)
    val MintInk = Color(0xFF0D3D28)
    /** Bottom nav center pill (reference fintech UI). */
    val NavLime = Color(0xFFCBF165)
    val NavLimeInk = Color(0xFF1A2E0A)
    val NavLimeChip = Color(0xFFEEF6D4)
    val Up = Color(0xFF12A56A)
    val Down = Color(0xFFE11D48)
    val Orange = Color(0xFFE68600)
    val AccentBlue = Color(0xFF6EA8FF)
    val AccentBlueDim = Color(0xFF4A8FCC)

    val SuccessGreen = Up
    val ErrorRed = Down
    val TextMuted = Color(0xFF8F98A0)
    val ItemTypeMuted = Color(0xFF7B8FA3)
    val BorderSubtle = Color(0xFF3D4450)
    /** AARRGGBB. `parseHexColor` treats 8 digits as AARRGGBB and 6 as RRGGBB. */
    val GlowOrange = Color(0x33E09805)
    val RarityUnknown = Color(0xFF8B93A7)

    /** Portfolio value & chart accent */
    val Gold = Color(0xFFC9A227)
    val GoldBright = Color(0xFFE8C547)
    val GoldDeep = Color(0xFF9A7B0A)
    val GoldFill = Color(0xFFF5E6B8)
}
