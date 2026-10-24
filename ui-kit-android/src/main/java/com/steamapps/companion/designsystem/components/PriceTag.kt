package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette
import com.steamapps.companion.designsystem.theme.Shapes

@Composable
fun PriceTag(
    amountUsd: Double,
    modifier: Modifier = Modifier,
    partial: Boolean = false,
) {
    val label = buildString {
        append("$")
        append("%.2f".format(amountUsd))
        if (partial) append(" ~")
    }
    Text(
        text = label,
        modifier = modifier
            .clip(Shapes.small)
            .background(Palette.TacticalOrange.copy(alpha = 0.22f))
            .border(1.dp, Palette.TacticalOrange.copy(alpha = 0.55f), Shapes.small)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        style = MaterialTheme.typography.labelLarge,
        color = Palette.TacticalOrange,
    )
}

@Composable
fun PriceTagText(
    text: String?,
    modifier: Modifier = Modifier,
) {
    if (text.isNullOrBlank()) return
    Text(
        text = text,
        modifier = modifier
            .clip(Shapes.small)
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f))
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f), Shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.secondary,
    )
}
