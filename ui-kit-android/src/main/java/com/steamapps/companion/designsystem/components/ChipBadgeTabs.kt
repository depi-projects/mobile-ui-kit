package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Space

enum class ChipVariant {
    Filter,
    Assist,
}

@Composable
fun Chip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ChipVariant = ChipVariant.Filter,
    selected: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    val iconSlot: @Composable (() -> Unit)? = leadingIcon?.let { image ->
        { Icon(image, contentDescription = null, modifier = Modifier.size(18.dp)) }
    }
    when (variant) {
        ChipVariant.Filter -> FilterChip(
            selected = selected,
            onClick = onClick,
            label = { Text(label) },
            leadingIcon = iconSlot,
            enabled = enabled,
            modifier = modifier.heightIn(min = 32.dp),
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                labelColor = MaterialTheme.colorScheme.onSurface,
            ),
        )
        ChipVariant.Assist -> SuggestionChip(
            onClick = onClick,
            label = { Text(label) },
            enabled = enabled,
            modifier = modifier.heightIn(min = 32.dp),
            colors = androidx.compose.material3.SuggestionChipDefaults.suggestionChipColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
        )
    }
}

/** Dot when [count] is null. Count badge otherwise. Color is `error`. */
@Composable
fun CountBadge(
    modifier: Modifier = Modifier,
    count: Int? = null,
    content: @Composable () -> Unit,
) {
    BadgedBox(
        modifier = modifier,
        badge = {
            if (count == null) {
                Badge(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                )
            } else if (count > 0) {
                Badge(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                ) {
                    Text(if (count > 99) "99+" else count.toString())
                }
            }
        },
    ) {
        content()
    }
}

data class TabItem(
    val id: String,
    val label: String,
    val icon: ImageVector? = null,
)

@Composable
fun Tabs(
    tabs: List<TabItem>,
    selectedId: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    scrollable: Boolean = false,
    onReselected: (String) -> Unit = {},
) {
    val index = tabs.indexOfFirst { it.id == selectedId }.coerceAtLeast(0)
    val row: @Composable (@Composable () -> Unit) -> Unit = { tabsContent ->
        if (scrollable) {
            ScrollableTabRow(selectedTabIndex = index, modifier = modifier, containerColor = MaterialTheme.colorScheme.surface, edgePadding = Space.screen) {
                tabsContent()
            }
        } else {
            TabRow(selectedTabIndex = index, modifier = modifier, containerColor = MaterialTheme.colorScheme.surface) {
                tabsContent()
            }
        }
    }
    row {
        tabs.forEach { tab ->
            val selected = tab.id == selectedId
            Tab(
                selected = selected,
                onClick = { if (selected) onReselected(tab.id) else onSelected(tab.id) },
                text = { Text(tab.label) },
                icon = tab.icon?.let { image -> { Icon(image, contentDescription = null) } },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
fun Fab(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    contentColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onPrimary,
    content: @Composable () -> Unit,
) {
    val described = modifier.semantics { this.contentDescription = contentDescription }
    if (label == null) {
        FloatingActionButton(
            onClick = onClick,
            modifier = described.size(56.dp),
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = com.steamapps.companion.designsystem.theme.Elevation.md,
            ),
            content = content,
        )
    } else {
        ExtendedFloatingActionButton(
            text = { Text(label) },
            icon = content,
            onClick = onClick,
            modifier = described,
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = com.steamapps.companion.designsystem.theme.Elevation.md,
            ),
        )
    }
}

@Composable
fun Expandable(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier.fillMaxWidth()) {
        Surface(onClick = onToggle, color = MaterialTheme.colorScheme.surface) {
            Row(
                Modifier.fillMaxWidth().padding(Space.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(title, style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f))
                Icon(
                    if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                )
            }
        }
        if (expanded) {
            Column(Modifier.padding(horizontal = Space.md, vertical = Space.sm), content = { content() })
        }
    }
}

enum class SpaceStep {
    Xxs,
    Xs,
    Sm,
    Md,
    Lg,
    Xl,
    Xxl,
    Screen,
}

@Composable
fun Gap(
    step: SpaceStep,
    modifier: Modifier = Modifier,
) {
    val size = when (step) {
        SpaceStep.Xxs -> Space.xxs
        SpaceStep.Xs -> Space.xs
        SpaceStep.Sm -> Space.sm
        SpaceStep.Md -> Space.md
        SpaceStep.Lg -> Space.lg
        SpaceStep.Xl -> Space.xl
        SpaceStep.Xxl -> Space.xxl
        SpaceStep.Screen -> Space.screen
    }
    androidx.compose.foundation.layout.Spacer(modifier.size(size))
}

@Composable
fun IconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    toggled: Boolean = false,
    loading: Boolean = false,
    containerColor: androidx.compose.ui.graphics.Color = if (toggled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
    iconTint: androidx.compose.ui.graphics.Color = if (toggled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
    size: Dp = 36.dp,
    iconSize: Dp = 20.dp,
) {
    Surface(
        onClick = onClick,
        enabled = !loading,
        modifier = modifier.size(size),
        shape = androidx.compose.foundation.shape.CircleShape,
        color = containerColor,
    ) {
        androidx.compose.foundation.layout.Box(contentAlignment = Alignment.Center) {
            if (loading) {
                CircularProgress(modifier = Modifier.size(iconSize))
            } else {
                Icon(icon, contentDescription = contentDescription, tint = iconTint, modifier = Modifier.size(iconSize))
            }
        }
    }
}
