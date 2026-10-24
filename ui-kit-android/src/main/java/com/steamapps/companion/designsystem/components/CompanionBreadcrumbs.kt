package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.ButtonShape
import com.steamapps.companion.designsystem.theme.Palette
import com.steamapps.companion.designsystem.theme.Space

data class BreadcrumbSegment(
    val label: String,
    val isCurrent: Boolean = false,
    val onClick: (() -> Unit)? = null,
)

@Composable
fun CompanionBreadcrumbs(
    segments: List<BreadcrumbSegment>,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    if (segments.isEmpty()) return
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        val anyCurrent = segments.any { it.isCurrent }
        segments.forEachIndexed { index, segment ->
            val isLast = index == segments.lastIndex
            val current = segment.isCurrent || (!anyCurrent && isLast)
            val style = if (current) {
                MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
            } else {
                MaterialTheme.typography.labelSmall
            }
            val color = if (current) MaterialTheme.colorScheme.onSurface else contentColor
            val label = CompanionPageStyle.displayTitle(segment.label)
            if (segment.onClick != null && !current) {
                Text(
                    label,
                    style = style,
                    color = Palette.AccentBlue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.clickable(onClick = segment.onClick),
                )
            } else {
                Text(
                    label,
                    style = style,
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            if (!isLast) {
                CompanionChromeIconGlyph(
                    icon = CompanionChromeIcon.Forward,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f),
                    iconTint = contentColor.copy(alpha = 0.85f),
                    size = 18.dp,
                    iconSize = 12.dp,
                )
            }
        }
    }
}

/** Pill control on hero headers (Valorant-Agents `btnBack`). */
@Composable
fun CompanionHeaderPill(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    showBackIcon: Boolean = true,
    contentColor: Color = Color.White,
    containerColor: Color = Palette.Charcoal.copy(alpha = 0.72f),
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = ButtonShape,
        color = containerColor,
        shadowElevation = com.steamapps.companion.designsystem.theme.Elevation.sm,
    ) {
        Row(
            Modifier.padding(horizontal = Space.sm, vertical = Space.xxs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (showBackIcon) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = label,
                    tint = contentColor,
                    modifier = Modifier.size(18.dp),
                )
            }
            label?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.labelMedium,
                    color = contentColor,
                    modifier = Modifier.padding(start = if (showBackIcon) 4.dp else 0.dp),
                )
            }
        }
    }
}
