package com.steamapps.companion.designsystem.theme

import android.provider.Settings
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

private val Ink = Color(0xFF16181D)
private val PrimaryButton = Color(0xFF1C1C1E)
private val MutedInk = Color(0xFF6B7280)

private val LightScheme = lightColorScheme(
    primary = PrimaryButton,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF0F0F2),
    onPrimaryContainer = Ink,
    secondary = Color(0xFFE68600),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFF1DE),
    onSecondaryContainer = Color(0xFF5C3200),
    tertiary = Color(0xFF12A56A),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFDDF6EA),
    onTertiaryContainer = Color(0xFF0D3D28),
    background = Color.White,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink,
    surfaceVariant = Color(0xFFF5F5F7),
    onSurfaceVariant = MutedInk,
    outline = Color(0xFFE3E6EE),
    error = Color(0xFFD92D4A),
    onError = Color.White,
    errorContainer = Color(0xFFFDE8EC),
    onErrorContainer = Color(0xFF8C1024),
    inverseSurface = Color(0xFF1E2024),
    inverseOnSurface = Color(0xFFF5F5F7),
    inversePrimary = Color(0xFFE68600),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF7F7F8),
    surfaceContainer = Color(0xFFF3F3F5),
    surfaceContainerHigh = Color(0xFFEEEEF0),
    surfaceContainerHighest = Color(0xFFE8E8EB),
)

/** Dark gray canvas (not near-black) + slightly lifted cards. */
private val DarkScheme = darkColorScheme(
    primary = Color(0xFFF5F5F7),
    onPrimary = Color(0xFF1E2024),
    primaryContainer = Color(0xFF32353C),
    onPrimaryContainer = Color(0xFFF5F5F7),
    secondary = Color(0xFFE68600),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF3D2800),
    onSecondaryContainer = Color(0xFFFFD9A8),
    tertiary = Color(0xFF12A56A),
    onTertiary = Color(0xFF062117),
    tertiaryContainer = Color(0xFF123828),
    onTertiaryContainer = Color(0xFFD4F5E4),
    background = Color(0xFF1E2024),
    onBackground = Color(0xFFF5F5F7),
    surface = Color(0xFF272A30),
    onSurface = Color(0xFFF5F5F7),
    surfaceVariant = Color(0xFF2E3138),
    onSurfaceVariant = Color(0xFF98989D),
    outline = Color(0xFF454850),
    error = Color(0xFFFF6B7A),
    onError = Color(0xFF2A0A0E),
    errorContainer = Color(0xFF4A1820),
    onErrorContainer = Color(0xFFFFD5DA),
    inverseSurface = Color(0xFFF5F5F7),
    inverseOnSurface = Color(0xFF1E2024),
    inversePrimary = Color(0xFFE68600),
    surfaceContainerLowest = Color(0xFF1E2024),
    surfaceContainerLow = Color(0xFF272A30),
    surfaceContainer = Color(0xFF272A30),
    surfaceContainerHigh = Color(0xFF32353C),
    surfaceContainerHighest = Color(0xFF3A3D44),
)

@Composable
fun CompanionTheme(
    font: AppFontChoice = AppFontChoice.default,
    themeMode: AppThemeMode = AppThemeMode.default,
    maxFontScale: Float = 2f,
    content: @Composable () -> Unit,
) {
    val darkTheme = themeMode.resolveDarkTheme(isSystemInDarkTheme())
    val context = LocalContext.current
    val reduceMotion = Settings.Global.getFloat(
        context.contentResolver,
        Settings.Global.ANIMATOR_DURATION_SCALE,
        1f,
    ) == 0f
    val density = LocalDensity.current
    val capped = Density(
        density = density.density,
        fontScale = density.fontScale.coerceAtMost(maxFontScale),
    )
    MaterialTheme(
        colorScheme = if (darkTheme) DarkScheme else LightScheme,
        typography = companionTypography(font),
        shapes = Shapes,
    ) {
        CompositionLocalProvider(
            LocalDensity provides capped,
            LocalReduceMotion provides reduceMotion,
            content = content,
        )
    }
}
