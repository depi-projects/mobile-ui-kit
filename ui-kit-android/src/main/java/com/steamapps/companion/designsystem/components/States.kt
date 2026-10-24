package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.material3.LocalContentColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette

enum class PageSkeleton {
    Generic,
    Home,
    Inventory,
    Leaderboard,
    ItemDetail,
    Catalog,
    Watchlist,
}

enum class LoadingIndicator {
    Circular,
    Lottie,
}

@Composable
fun LoadingState(
    message: String = "Loading…",
    modifier: Modifier = Modifier,
    skeleton: PageSkeleton = PageSkeleton.Generic,
    showProgressIndicator: Boolean = true,
    indicator: LoadingIndicator = LoadingIndicator.Circular,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 14.dp, end = 14.dp)
            .semantics {
                progressBarRangeInfo = androidx.compose.ui.semantics.ProgressBarRangeInfo.Indeterminate
                if (message.isNotBlank()) contentDescription = message
            },
    ) {
        if (showProgressIndicator) {
            when (indicator) {
                LoadingIndicator.Circular -> CircularProgress(
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 8.dp),
                    label = message.ifBlank { null },
                )
                LoadingIndicator.Lottie -> CompanionLoadingLottie(
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 8.dp),
                    size = 56.dp,
                )
            }
            if (message.isNotBlank()) {
                Text(
                    message,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp),
                )
            }
        }
        when (skeleton) {
            PageSkeleton.Home -> HomeDashboardSkeleton()
            PageSkeleton.Inventory -> InventoryPageSkeleton()
            PageSkeleton.Leaderboard -> LeaderboardPageSkeleton()
            PageSkeleton.ItemDetail -> ItemDetailSkeleton()
            PageSkeleton.Generic -> ListSkeleton(rows = 3)
            PageSkeleton.Catalog -> CatalogPageSkeleton()
            PageSkeleton.Watchlist -> WatchlistPageSkeleton()
        }
    }
}

@Composable
fun ItemDetailLoadingState() {
    LoadingState(message = "", skeleton = PageSkeleton.ItemDetail)
}

@Composable
fun EmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.Inventory2,
    iconTint: androidx.compose.ui.graphics.Color = Palette.Coral,
    contentDescription: String? = null,
    action: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            Modifier
                .size(64.dp)
                .background(Palette.CoralChip, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                icon,
                contentDescription = contentDescription ?: title,
                tint = iconTint,
                modifier = Modifier.size(32.dp),
            )
        }
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
        )
        Text(
            message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp),
        )
        action?.let {
            Box(Modifier.padding(top = 20.dp)) {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
                    it()
                }
            }
        }
    }
}

@Composable
fun ErrorState(
    message: String,
    modifier: Modifier = Modifier,
    title: String = "Something went wrong",
    icon: ImageVector = Icons.Outlined.ErrorOutline,
    action: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            Modifier
                .size(64.dp)
                .background(MaterialTheme.colorScheme.error.copy(alpha = 0.12f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(32.dp),
            )
        }
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
        )
        Text(
            message,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp),
        )
        action?.let {
            Box(Modifier.padding(top = 20.dp)) {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
                    it()
                }
            }
        }
    }
}

@Composable
fun SearchEmptyState(modifier: Modifier = Modifier) {
    EmptyState(
        title = "No matches",
        message = "Try a different name or check spelling.",
        modifier = modifier,
        icon = Icons.Outlined.SearchOff,
    )
}

@Composable
fun SuccessState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.CheckCircle,
    action: @Composable (() -> Unit)? = null,
) {
    EmptyState(
        title = title,
        message = message,
        modifier = modifier,
        icon = icon,
        iconTint = MaterialTheme.colorScheme.tertiary,
        action = action,
    )
}

@Composable
fun OfflineStyleErrorState(message: String, modifier: Modifier = Modifier) {
    ErrorState(
        title = "Could not load",
        message = message,
        modifier = modifier,
        icon = Icons.Outlined.CloudOff,
    )
}
