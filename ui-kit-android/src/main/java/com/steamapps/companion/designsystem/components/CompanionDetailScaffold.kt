package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette
import com.steamapps.companion.designsystem.theme.PanelShape
import com.steamapps.companion.designsystem.theme.Space

/**
 * Detail layout inspired by [Valorant-Agents](https://github.com/AhmedVargos/Valorant-Agents)
 * `activity_agent_details`: hero band, pill back, header actions, title on art.
 */
@Composable
fun CompanionDetailScaffold(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    breadcrumbs: List<BreadcrumbSegment> = emptyList(),
    badge: String? = null,
    heroImageUrl: Any? = null,
    heroAccent: Color = Palette.Navy,
    backLabel: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
    contentScrollEnabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    val heroHeight = CompanionPageStyle.heroHeight
    ScreenBackground(modifier = modifier) {
        Column(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(heroHeight)
                    .clip(PanelShape)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                heroAccent.copy(alpha = 0.92f),
                                Palette.Charcoal,
                                heroAccent.copy(alpha = 0.4f),
                            ),
                        ),
                    ),
            ) {
                AsyncImage(
                    model = heroImageUrl,
                    contentDescription = title,
                    fit = ImageFit.Fit,
                    clipToShape = false,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxWidth(0.55f)
                        .height(heroHeight)
                        .padding(end = Space.sm, top = Space.md),
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                        .padding(Space.md),
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CompanionHeaderPill(
                            onClick = onBack,
                            label = backLabel,
                            contentColor = Color.White,
                        )
                        Row(
                            Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Start,
                        ) {
                            actions()
                        }
                    }
                    if (breadcrumbs.isNotEmpty()) {
                        CompanionBreadcrumbs(
                            segments = breadcrumbs,
                            modifier = Modifier.padding(top = Space.sm),
                            contentColor = Color.White.copy(alpha = 0.75f),
                        )
                    }
                }
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(Space.md)
                        .fillMaxWidth(0.62f),
                ) {
                    CompanionHeroTitle(title)
                    badge?.let { CompanionHeroBadge(it) }
                }
            }
            val bodyModifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Space.screen)
                .padding(top = Space.md, bottom = Space.lg)
                .then(if (contentScrollEnabled) Modifier.verticalScroll(rememberScrollState()) else Modifier)
            Column(bodyModifier, content = content)
        }
    }
}
