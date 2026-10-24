package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class CompanionChromeIcon {
    Back,
    Forward,
    Settings,
    Share,
    External,
}

@Composable
fun CompanionChromeIconButton(
    icon: CompanionChromeIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    size: Dp = 36.dp,
    iconSize: Dp = 20.dp,
) {
    CompanionChromeIconSurface(
        icon = icon,
        modifier = modifier,
        contentDescription = contentDescription,
        containerColor = containerColor,
        iconTint = iconTint,
        size = size,
        iconSize = iconSize,
        onClick = onClick,
    )
}

@Composable
fun CompanionChromeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    size: Dp = 36.dp,
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.size(size),
        shape = CircleShape,
        color = containerColor,
    ) {
        Box(Modifier.size(size), contentAlignment = Alignment.Center, content = { content() })
    }
}

/** Non-interactive glyph (e.g. breadcrumb separator). */
@Composable
fun CompanionChromeIconGlyph(
    icon: CompanionChromeIcon,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    size: Dp = 20.dp,
    iconSize: Dp = 14.dp,
) {
    CompanionChromeIconSurface(
        icon = icon,
        modifier = modifier,
        contentDescription = contentDescription,
        containerColor = containerColor,
        iconTint = iconTint,
        size = size,
        iconSize = iconSize,
        onClick = null,
    )
}

@Composable
private fun CompanionChromeIconSurface(
    icon: CompanionChromeIcon,
    modifier: Modifier,
    contentDescription: String?,
    containerColor: Color,
    iconTint: Color,
    size: Dp,
    iconSize: Dp,
    onClick: (() -> Unit)?,
) {
    val imageVector = icon.toImageVector()
    val surfaceModifier = modifier.size(size)
    val inner: @Composable () -> Unit = {
        Box(Modifier.size(size), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = iconTint,
                modifier = Modifier.size(iconSize),
            )
        }
    }
    if (onClick != null) {
        Surface(
            onClick = onClick,
            modifier = surfaceModifier,
            shape = CircleShape,
            color = containerColor,
            content = inner,
        )
    } else {
        Surface(
            modifier = surfaceModifier,
            shape = CircleShape,
            color = containerColor,
            content = inner,
        )
    }
}

private fun CompanionChromeIcon.toImageVector(): ImageVector = when (this) {
    CompanionChromeIcon.Back -> Icons.AutoMirrored.Filled.ArrowBack
    CompanionChromeIcon.Forward -> Icons.AutoMirrored.Filled.KeyboardArrowRight
    CompanionChromeIcon.Settings -> Icons.Default.Settings
    CompanionChromeIcon.Share -> Icons.Default.Share
    CompanionChromeIcon.External -> Icons.AutoMirrored.Filled.OpenInNew
}
