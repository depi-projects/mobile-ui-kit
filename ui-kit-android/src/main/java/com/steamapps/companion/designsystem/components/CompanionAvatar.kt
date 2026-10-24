package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Square avatar with soft corner radius (Steam-style). */
val CompanionAvatarShape = RoundedCornerShape(10.dp)

@Composable
fun CompanionAvatar(
    imageUrl: Any?,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    contentDescription: String? = null,
    borderWidth: Dp = 0.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f),
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .then(
                if (borderWidth > 0.dp) {
                    Modifier.border(borderWidth, borderColor, CompanionAvatarShape)
                } else {
                    Modifier
                },
            ),
        fit = ImageFit.Crop,
        shape = CompanionAvatarShape,
    )
}
