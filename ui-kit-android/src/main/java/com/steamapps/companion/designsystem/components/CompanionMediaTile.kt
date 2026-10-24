package com.steamapps.companion.designsystem.components

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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.PanelShape
import com.steamapps.companion.designsystem.theme.Space

/**
 * Image-forward tile (Valorant-Agents–style): art fills the card, bottom scrim for labels.
 * See [AhmedVargos/Valorant-Agents](https://github.com/AhmedVargos/Valorant-Agents) `AgentCellView`.
 */
@Composable
fun CompanionMediaTile(
    title: String,
    subtitle: String? = null,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    imageHeight: Dp = 112.dp,
    contentDescription: String = title,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = PanelShape,
        shadowElevation = com.steamapps.companion.designsystem.theme.Elevation.lg,
        tonalElevation = 2.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .clip(PanelShape),
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                fit = ImageFit.Crop,
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC0E1419)),
                                startY = size.height * 0.35f,
                                endY = size.height,
                            ),
                        )
                    },
            )
            Column(
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(Space.sm),
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                subtitle?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.82f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
