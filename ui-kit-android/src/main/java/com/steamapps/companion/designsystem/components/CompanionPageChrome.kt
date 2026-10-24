package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette
import com.steamapps.companion.designsystem.theme.Space

/** Single source for page titles, section labels, and app-bar styling. */
object CompanionPageStyle {
    fun displayTitle(raw: String): String = raw.trim().uppercase()

    val heroHeight: Dp = 248.dp
    const val appBarSurfaceAlpha: Float = 0.92f
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun standardTopAppBarColors() = TopAppBarDefaults.topAppBarColors(
    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = CompanionPageStyle.appBarSurfaceAlpha),
    titleContentColor = MaterialTheme.colorScheme.onSurface,
    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
)

@Composable
fun CompanionAppBarTitleBlock(
    title: String,
    subtitle: String? = null,
    breadcrumbs: List<BreadcrumbSegment> = emptyList(),
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        if (breadcrumbs.isNotEmpty()) {
            CompanionBreadcrumbs(
                segments = breadcrumbs,
                modifier = Modifier.padding(bottom = 4.dp),
            )
        }
        CompanionAppBarTitle(title)
        subtitle?.let { CompanionAppBarSubtitle(it) }
    }
}

@Composable
fun CompanionAppBarTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        CompanionPageStyle.displayTitle(title),
        modifier = modifier,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
fun CompanionAppBarSubtitle(subtitle: String, modifier: Modifier = Modifier) {
    Text(
        subtitle,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
fun CompanionHeroTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        CompanionPageStyle.displayTitle(title),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun CompanionHeroBadge(text: String, modifier: Modifier = Modifier) {
    androidx.compose.material3.Surface(
        shape = com.steamapps.companion.designsystem.theme.ButtonShape,
        color = Palette.Charcoal.copy(alpha = 0.55f),
        modifier = modifier.padding(top = Space.xs),
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
        )
    }
}

/** Catalog hub section, e.g. “Rifles · Cases”. */
@Composable
fun CompanionSectionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        CompanionPageStyle.displayTitle(title),
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

/** Accent label above a list group (“Quick picks”). */
@Composable
fun CompanionGroupLabel(
    label: String,
    modifier: Modifier = Modifier,
) {
    Text(
        CompanionPageStyle.displayTitle(label),
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
    )
}

/** In-panel section header (price, about, watchlist). */
@Composable
fun CompanionContentSectionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        CompanionPageStyle.displayTitle(title),
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        color = Palette.Coral,
    )
}

/** Home widgets: small caps row with optional count and trailing action. */
@Composable
fun CompanionWidgetSectionHead(
    title: String,
    modifier: Modifier = Modifier,
    count: Int? = null,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = 18.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            CompanionPageStyle.displayTitle(title),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        count?.let {
            Text(
                "$it",
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        if (actionLabel != null && onAction != null) {
            Text(
                actionLabel,
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onAction),
                color = Palette.Orange,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.End,
            )
        }
    }
}

@Composable
fun CompanionHeroIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.semantics {
            if (contentDescription != null) this.contentDescription = contentDescription
        },
        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White),
        content = content,
    )
}
