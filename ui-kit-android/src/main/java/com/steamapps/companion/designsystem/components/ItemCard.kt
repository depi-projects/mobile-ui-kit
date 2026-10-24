package com.steamapps.companion.designsystem.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Legacy name — delegates to [CompanionCatalogItemCard]. */
@Composable
fun ItemCard(
    title: String,
    subtitle: String? = null,
    imageUrl: String? = null,
    trailing: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    @Suppress("UNUSED_PARAMETER") usePanel: Boolean = true,
) {
    CompanionCatalogItemCard(
        title = title,
        modifier = modifier,
        imageUrl = imageUrl,
        accentColor = MaterialTheme.colorScheme.primary,
        subtitle = subtitle?.let {
            {
                Text(
                    it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        trailing = trailing,
    )
}
