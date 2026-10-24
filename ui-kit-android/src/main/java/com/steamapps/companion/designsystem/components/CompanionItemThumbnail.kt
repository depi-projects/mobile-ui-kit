package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.resolveItemRarityColor

private val RarityBottomBorderHeight = 2.5.dp
private const val ItemArtScale = 1.22f

/**
 * Item art on a transparent background with a soft rarity glow from the bottom and a rarity bottom edge.
 * Weapon art may draw outside this box (no clip on the image).
 */
@Composable
fun CompanionItemThumbnail(
    imageUrl: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    rarityName: String? = null,
    rarityColorHex: String? = null,
    rarityFallback: Color? = null,
    cornerRadius: Dp = 12.dp,
    imagePadding: Dp = 2.dp,
) {
    val rarityColor = resolveItemRarityColor(rarityName, rarityColorHex, rarityFallback)
    val shape = RoundedCornerShape(cornerRadius)
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val w = constraints.maxWidth.toFloat()
        val h = constraints.maxHeight.toFloat()
        if (w > 0f && h > 0f) {
            Box(
                Modifier
                    .fillMaxSize()
                    .clip(shape)
                    .drawBehind {
                        val center = Offset(w / 2f, h * 0.94f)
                        val radius = maxOf(w, h) * 0.52f
                        drawRect(
                            brush = Brush.radialGradient(
                                colorStops = arrayOf(
                                    0f to rarityColor.copy(alpha = 0.32f),
                                    0.45f to rarityColor.copy(alpha = 0.12f),
                                    0.78f to rarityColor.copy(alpha = 0.03f),
                                    1f to Color.Transparent,
                                ),
                                center = center,
                                radius = radius,
                            ),
                        )
                    },
            )
        }
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            fit = ImageFit.Fit,
            clipToShape = false,
            modifier = Modifier
                .fillMaxSize()
                .padding(imagePadding)
                .graphicsLayer {
                    scaleX = ItemArtScale
                    scaleY = ItemArtScale
                    clip = false
                },
        )
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(RarityBottomBorderHeight)
                .clip(shape)
                .background(rarityColor),
        )
    }
}
