package com.steamapps.companion.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Maps API inventoryStatus / profile visibility to a consistent icon.
 */
@Composable
fun InventoryPrivacyIcon(
    status: String?,
    profilePublic: Boolean? = null,
    modifier: Modifier = Modifier,
) {
    val (icon, desc) = when {
        profilePublic == false -> Icons.Default.Lock to "Private profile"
        status == "accessible" || status == "public" -> Icons.Default.Public to "Public inventory"
        status == "private" -> Icons.Default.Lock to "Private inventory"
        status == "friends_only" -> Icons.Default.People to "Friends only inventory"
        status == "error" -> Icons.Default.Warning to "Unavailable"
        else -> Icons.Default.HelpOutline to "Unknown"
    }
    Icon(
        imageVector = icon,
        contentDescription = desc,
        modifier = modifier,
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

fun inventoryStatusLabel(status: String?): String = when (status) {
    "accessible", "public" -> "Public"
    "private" -> "Private"
    "friends_only" -> "Friends only"
    "error" -> "Unavailable"
    else -> "Unknown"
}
