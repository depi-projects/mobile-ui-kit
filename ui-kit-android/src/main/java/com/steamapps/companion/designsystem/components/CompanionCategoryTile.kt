package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette
import com.steamapps.companion.designsystem.theme.companionElevatedCardColor
import com.steamapps.companion.designsystem.theme.PanelShape
import com.steamapps.companion.designsystem.theme.Space

@Composable
fun CompanionCategoryTile(
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageUrl: Any? = null,
    enabled: Boolean = true,
    placeholder: @Composable () -> Unit,
) {
    val tileHeight = 188.dp
    val artHeight = 108.dp
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(tileHeight),
        shape = PanelShape,
        shadowElevation = 0.dp,
        tonalElevation = 0.dp,
        color = companionElevatedCardColor(),
    ) {
        Column(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(artHeight),
                contentAlignment = Alignment.Center,
            ) {
                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        fit = ImageFit.Fit,
                        clipToShape = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(artHeight + 12.dp)
                            .padding(horizontal = Space.xs),
                    )
                } else {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Palette.NavyLight.copy(alpha = 0.35f),
                                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                    ),
                                ),
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        placeholder()
                    }
                }
            }
            Text(
                title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = Space.sm, top = Space.sm, end = Space.sm),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                description,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = Space.sm, top = 4.dp, end = Space.sm),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
