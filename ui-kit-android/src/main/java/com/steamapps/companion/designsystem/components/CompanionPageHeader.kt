package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.steamapps.companion.designsystem.theme.Space

/**
 * Shared screen chrome: breadcrumbs, back + left-aligned actions, title.
 * Use [headerActions] for Share, Steam, etc. (same row as back, start-aligned).
 */
@Composable
fun CompanionPageHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    breadcrumbs: List<BreadcrumbSegment> = emptyList(),
    onBack: (() -> Unit)? = null,
    backContentDescription: String = "Back",
    prominent: Boolean = false,
    horizontalPadding: Dp = Space.screen,
    headerActions: @Composable RowScope.() -> Unit = {},
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .padding(top = 8.dp, bottom = 4.dp),
    ) {
        if (breadcrumbs.isNotEmpty()) {
            CompanionBreadcrumbs(
                segments = breadcrumbs,
                modifier = Modifier.padding(bottom = 10.dp),
            )
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (onBack != null) {
                CompanionChromeIconButton(
                    icon = CompanionChromeIcon.Back,
                    onClick = onBack,
                    contentDescription = backContentDescription,
                )
            }
            Column(
                Modifier
                    .weight(1f)
                    .padding(
                        start = if (onBack != null) 12.dp else 0.dp,
                        end = 8.dp,
                    ),
            ) {
                if (title.isNotBlank()) {
                    if (prominent && onBack == null) {
                        Text(
                            title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Black,
                            letterSpacing = (-0.5).sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    } else {
                        CompanionAppBarTitle(title = title)
                    }
                }
                subtitle?.takeIf { it.isNotBlank() }?.let {
                    if (prominent && onBack == null) {
                        Text(
                            it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    } else {
                        CompanionAppBarSubtitle(it)
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                content = headerActions,
            )
        }
    }
}
