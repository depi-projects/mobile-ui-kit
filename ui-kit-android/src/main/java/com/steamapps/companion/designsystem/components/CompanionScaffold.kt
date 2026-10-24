package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.LocalContentColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Space

@Composable
fun CompanionScaffold(
    title: String,
    subtitle: String? = null,
    breadcrumbs: List<BreadcrumbSegment> = emptyList(),
    onBack: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    contentTopPadding: Dp = 0.dp,
    prominentHeader: Boolean = false,
    headerActions: @Composable RowScope.() -> Unit = {},
    /** @deprecated Use [headerActions]; kept for call sites not yet migrated. */
    actions: @Composable RowScope.() -> Unit = {},
    bottomBar: @Composable (() -> Unit)? = null,
    fab: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    ScreenBackground(modifier = modifier) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onBackground) {
        Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            CompanionPageHeader(
                title = title,
                subtitle = subtitle,
                breadcrumbs = breadcrumbs,
                onBack = onBack,
                prominent = prominentHeader,
                headerActions = {
                    headerActions()
                    actions()
                },
            )
            Column(
                Modifier
                    .weight(1f)
                    .padding(horizontal = Space.screen)
                    .padding(top = contentTopPadding)
                    .padding(bottom = Space.md),
                content = content,
            )
            bottomBar?.invoke()
        }
        if (fab != null) {
            Box(Modifier.align(Alignment.BottomEnd).padding(Space.md)) { fab() }
        }
        }
        }
    }
}
