package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class NavTab(
    val route: String,
    val label: String,
    val icon: ImageVector,
)

/**
 * Floating pill bar: optional lime pill on [centerHighlightRoute]; circular search on the right.
 */
@Composable
fun NavigationBar(
    tabs: List<NavTab>,
    currentRoute: String?,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    searchTab: NavTab? = null,
    searchSelected: Boolean = false,
    centerHighlightRoute: String? = null,
) {
    val muted = MaterialTheme.colorScheme.onSurfaceVariant
    val ink = MaterialTheme.colorScheme.onSurface
    val selectedTint = MaterialTheme.colorScheme.primary
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            Modifier
                .weight(1f)
                .height(52.dp),
            shape = RoundedCornerShape(31.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = com.steamapps.companion.designsystem.theme.Elevation.xl,
        ) {
            Row(
                Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tabs.forEach { tab ->
                    val selected = currentRoute == tab.route
                    val isCenterCta = centerHighlightRoute != null && tab.route == centerHighlightRoute
                    val showLimePill = isCenterCta && selected
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(22.dp))
                            .clickable { onTabSelected(tab.route) },
                        contentAlignment = Alignment.Center,
                    ) {
                        if (showLimePill) {
                            Box(
                                Modifier
                                    .padding(horizontal = 4.dp)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(selectedTint.copy(alpha = 0.22f)),
                            )
                        } else if (selected) {
                            Box(
                                Modifier
                                    .padding(horizontal = 6.dp)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(selectedTint.copy(alpha = 0.14f)),
                            )
                        }
                        Icon(
                            tab.icon,
                            contentDescription = tab.label,
                            modifier = Modifier.size(if (selected) 28.dp else 24.dp),
                            tint = when {
                                selected -> selectedTint
                                else -> muted.copy(alpha = 0.45f)
                            },
                        )
                    }
                }
            }
        }
        if (searchTab != null) {
            Surface(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(56.dp)
                    .clip(CircleShape)
                    .clickable { onTabSelected(searchTab.route) },
                shape = CircleShape,
                color = if (searchSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surface,
                shadowElevation = if (searchSelected) {
                    com.steamapps.companion.designsystem.theme.Elevation.lg
                } else {
                    com.steamapps.companion.designsystem.theme.Elevation.xl
                },
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        searchTab.icon,
                        contentDescription = searchTab.label,
                        tint = if (searchSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(26.dp),
                    )
                }
            }
        }
    }
}
