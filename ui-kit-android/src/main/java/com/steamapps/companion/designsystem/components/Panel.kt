package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette
import com.steamapps.companion.designsystem.theme.PanelShape
import com.steamapps.companion.designsystem.theme.Space

@Composable
fun Panel(
    modifier: Modifier = Modifier,
    showAccentStripe: Boolean = false,
    accent: androidx.compose.ui.graphics.Color? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = PanelShape,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = com.steamapps.companion.designsystem.theme.Elevation.xs,
        shadowElevation = com.steamapps.companion.designsystem.theme.Elevation.md,
    ) {
        Column {
            if (accent != null || showAccentStripe) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(
                            if (accent != null) {
                                androidx.compose.ui.graphics.SolidColor(accent)
                            } else {
                                Brush.horizontalGradient(listOf(Palette.Orange, Palette.NavLime))
                            },
                        ),
                )
            }
            Column(Modifier.padding(Space.md), content = content)
        }
    }
}
