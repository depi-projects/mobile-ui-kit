package com.steamapps.companion.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

/** True when the active [MaterialTheme] is a dark appearance (not the OS setting alone). */
fun ColorScheme.isDarkAppearance(): Boolean = background.luminance() < 0.5f

/** Home widgets, list rows, and other soft inset panels. */
@Composable
fun companionSoftPanelColor(): Color {
    val scheme = MaterialTheme.colorScheme
    return if (scheme.isDarkAppearance()) {
        scheme.surfaceContainerHigh
    } else {
        scheme.surfaceVariant.copy(alpha = 0.35f)
    }
}

@Composable
fun companionSoftPanelPlaceholderColor(): Color {
    val scheme = MaterialTheme.colorScheme
    return if (scheme.isDarkAppearance()) {
        scheme.surfaceContainer
    } else {
        scheme.surfaceVariant.copy(alpha = 0.55f)
    }
}

/** Catalog tiles and grouped settings cards. */
@Composable
fun companionElevatedCardColor(): Color {
    val scheme = MaterialTheme.colorScheme
    return if (scheme.isDarkAppearance()) {
        scheme.surfaceContainer
    } else {
        scheme.surfaceVariant.copy(alpha = 0.35f)
    }
}
