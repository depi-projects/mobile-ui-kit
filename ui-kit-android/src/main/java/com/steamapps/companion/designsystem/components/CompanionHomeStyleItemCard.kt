package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.steamapps.companion.designsystem.theme.Palette
import com.steamapps.companion.designsystem.theme.companionSoftPanelColor

@Composable
fun CompanionHomeStyleItemCard(
    title: String,
    imageUrl: Any?,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    priceUsd: Double? = null,
    priceDisplay: String? = null,
    priceColor: Color = Palette.Gold,
    subtitle: @Composable (() -> Unit)? = null,
    beforePrice: @Composable (() -> Unit)? = null,
    overlayTopEnd: @Composable (BoxScope.() -> Unit)? = null,
    typeLabel: String? = null,
    rarityName: String? = null,
    rarityColorHex: String? = null,
) {
    val imageWidth = 96.dp
    val imageHeight = 80.dp
    val textStartPad = imageWidth + 18.dp
    Box(
        modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 2.dp),
    ) {
        val rowModifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(companionSoftPanelColor())
        Row(
            if (onClick != null) {
                rowModifier.clickable(onClick = onClick)
            } else {
                rowModifier
            }
                .padding(start = textStartPad, end = 12.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 17.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                typeLabel?.takeIf { it.isNotBlank() }?.let { type ->
                    Text(
                        type,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        color = Palette.ItemTypeMuted,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
                subtitle?.invoke()
            }
            beforePrice?.invoke()
            CompanionItemValueText(
                usd = priceUsd,
                fallback = priceDisplay,
                compact = true,
                valueColor = priceColor,
            )
        }
        CompanionItemThumbnail(
            imageUrl = imageUrl,
            contentDescription = title,
            rarityName = rarityName,
            rarityColorHex = rarityColorHex,
            modifier = Modifier
                .zIndex(1f)
                .align(Alignment.TopStart)
                .padding(start = 4.dp)
                .size(width = imageWidth, height = imageHeight)
                .offset(y = (-4).dp),
            cornerRadius = 14.dp,
        )
        overlayTopEnd?.let { overlay ->
            Box(Modifier.align(Alignment.TopEnd)) {
                overlay()
            }
        }
    }
}
