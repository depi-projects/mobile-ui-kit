package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

enum class RarityIndicatorStyle {
    /** Small dot before label */
    Dot,
    /** Tinted pill with rarity name */
    Chip,
    /** Soft left gutter (no full border) */
    SideGutter,
}

@Composable
fun RarityDot(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color),
    )
}

@Composable
fun RarityChip(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        label,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.18f))
            .padding(horizontal = 8.dp, vertical = 3.dp),
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.SemiBold,
        color = color.copy(alpha = 0.95f),
        maxLines = 1,
    )
}

@Composable
fun RarityRow(
    label: String?,
    color: Color,
    style: RarityIndicatorStyle = RarityIndicatorStyle.Chip,
    modifier: Modifier = Modifier,
) {
    if (label.isNullOrBlank()) return
    when (style) {
        RarityIndicatorStyle.Dot -> Row(modifier, verticalAlignment = Alignment.CenterVertically) {
            RarityDot(color, Modifier.padding(end = 6.dp))
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        RarityIndicatorStyle.Chip -> RarityChip(label, color, modifier)
        RarityIndicatorStyle.SideGutter -> Text(
            label,
            modifier = modifier.padding(start = 6.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color,
        )
    }
}
