package com.steamapps.companion.designsystem.theme

import androidx.annotation.FontRes
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.steamapps.companion.designsystem.R

enum class AppFontChoice(val storageId: String, val displayName: String) {
    Manrope("manrope", "Manrope"),
    Inter("inter", "Inter"),
    Onest("onest", "Onest"),
    Montserrat("montserrat", "Montserrat"),
    IbmPlexSans("ibm_plex_sans", "IBM Plex Sans"),
    SpaceGrotesk("space_grotesk", "Space Grotesk"),
    SourceSans3("source_sans_3", "Source Sans 3"),
    Satoshi("satoshi", "Satoshi"),
    ;

    fun fontFamily(): FontFamily = when (this) {
        Manrope -> ManropeFamily
        Inter -> InterFamily
        Onest -> OnestFamily
        Montserrat -> MontserratFamily
        IbmPlexSans -> IbmPlexSansFamily
        SpaceGrotesk -> SpaceGroteskFamily
        SourceSans3 -> SourceSans3Family
        Satoshi -> SatoshiFamily
    }

    /** Sample shown in font pickers on every platform. */
    val previewText: String get() = "The quick brown fox"

    companion object {
        val default: AppFontChoice = Manrope

        fun fromStorageId(id: String?): AppFontChoice =
            entries.find { it.storageId == id } ?: default
    }
}

private val ManropeFamily = FontFamily(
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_medium, FontWeight.Medium),
    Font(R.font.manrope_semibold, FontWeight.SemiBold),
    Font(R.font.manrope_bold, FontWeight.Bold),
)

private fun variableFamily(@FontRes resId: Int): FontFamily = FontFamily(
    Font(resId, FontWeight.Normal),
    Font(resId, FontWeight.Medium),
    Font(resId, FontWeight.SemiBold),
    Font(resId, FontWeight.Bold),
)

private val InterFamily = variableFamily(R.font.inter_var)
private val OnestFamily = variableFamily(R.font.onest_var)
private val MontserratFamily = variableFamily(R.font.montserrat_var)
private val IbmPlexSansFamily = variableFamily(R.font.ibmplexsans_var)
private val SpaceGroteskFamily = variableFamily(R.font.space_grotesk_var)
private val SourceSans3Family = variableFamily(R.font.sourcesans3_var)
private val SatoshiFamily = variableFamily(R.font.satoshi_var)
