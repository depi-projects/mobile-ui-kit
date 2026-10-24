package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.PanelShape
import com.steamapps.companion.designsystem.theme.Space

@Composable
fun CompanionCatalogItemCard(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    imageUrl: Any? = null,
    accentColor: Color = MaterialTheme.colorScheme.primary,
    rarityName: String? = null,
    rarityColorHex: String? = null,
    subtitle: @Composable (() -> Unit)? = null,
    meta: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    val shape = PanelShape
    val surfaceColor = MaterialTheme.colorScheme.surface
    val surfaceModifier = modifier.fillMaxWidth()
    val cardContent: @Composable () -> Unit = {
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(accentColor),
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .padding(Space.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CompanionItemThumbnail(
                    imageUrl = imageUrl,
                    contentDescription = title,
                    rarityName = rarityName,
                    rarityColorHex = rarityColorHex,
                    rarityFallback = accentColor,
                    modifier = Modifier
                        .width(76.dp)
                        .fillMaxHeight(),
                    cornerRadius = 10.dp,
                )
                Column(
                    Modifier
                        .weight(1f)
                        .padding(start = Space.sm),
                ) {
                    Text(
                        title,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    subtitle?.invoke()
                    meta?.invoke()
                }
                trailing?.invoke()
            }
        }
    }
    if (onClick != null) {
        Surface(
            onClick = onClick,
            modifier = surfaceModifier,
            shape = shape,
            shadowElevation = com.steamapps.companion.designsystem.theme.Elevation.lg,
            tonalElevation = 1.dp,
            color = surfaceColor,
            content = cardContent,
        )
    } else {
        Surface(
            modifier = surfaceModifier,
            shape = shape,
            shadowElevation = com.steamapps.companion.designsystem.theme.Elevation.lg,
            tonalElevation = 1.dp,
            color = surfaceColor,
            content = cardContent,
        )
    }
}
