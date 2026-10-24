package com.steamapps.companion.designsystem.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette
import com.steamapps.companion.designsystem.theme.companionSoftPanelColor

@Composable
fun Skeleton(
    modifier: Modifier = Modifier,
    height: Dp = 16.dp,
    width: Dp? = null,
    baseColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    val transition = rememberInfiniteTransition(label = "skeleton")
    val shift by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1100, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmer",
    )
    val highlight = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
    val brush = Brush.linearGradient(
        colors = listOf(
            baseColor,
            highlight,
            baseColor,
        ),
        start = Offset(shift * 400f, 0f),
        end = Offset(shift * 400f + 180f, 80f),
    )
    val shape = RoundedCornerShape(8.dp)
    Box(
        modifier
            .then(if (width != null) Modifier.width(width) else Modifier.fillMaxWidth())
            .height(height)
            .clip(shape)
            .background(brush),
    )
}

@Composable
fun ListSkeleton(
    rows: Int = 4,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(top = 12.dp)) {
        repeat(rows) {
            CardSkeleton(modifier = Modifier.padding(vertical = 6.dp))
        }
    }
}

@Composable
fun CardSkeleton(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(14.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Skeleton(height = 40.dp, width = 56.dp)
            Column(Modifier.padding(start = 12.dp).weight(1f)) {
                Skeleton(height = 14.dp, width = 160.dp)
                Spacer(Modifier.size(8.dp))
                Skeleton(height = 10.dp, width = 96.dp)
            }
            Skeleton(height = 14.dp, width = 52.dp)
        }
    }
}

@Composable
fun ItemDetailSkeleton(modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 8.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Skeleton(height = 180.dp, width = 280.dp)
            Spacer(Modifier.size(16.dp))
            Skeleton(height = 18.dp, width = 220.dp)
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
        ) {
            Skeleton(height = 12.dp, width = 80.dp)
            Skeleton(height = 36.dp, width = 120.dp, modifier = Modifier.padding(top = 12.dp))
            Skeleton(height = 28.dp, width = 180.dp, modifier = Modifier.padding(top = 8.dp))
            repeat(3) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Skeleton(height = 14.dp, width = 140.dp)
                    Skeleton(height = 14.dp, width = 52.dp)
                }
            }
            Skeleton(height = 96.dp, modifier = Modifier.padding(top = 14.dp).fillMaxWidth())
        }
        repeat(1) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
            ) {
                Skeleton(height = 12.dp, width = 100.dp)
                Spacer(Modifier.size(12.dp))
                Skeleton(height = 14.dp)
                Spacer(Modifier.size(8.dp))
                Skeleton(height = 14.dp, width = 200.dp)
            }
        }
    }
}

@Composable
fun HomeDashboardSkeleton(modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Skeleton(
                height = 48.dp,
                width = 48.dp,
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            )
            Skeleton(height = 16.dp, width = 120.dp, modifier = Modifier.padding(start = 10.dp).weight(1f))
            Skeleton(height = 36.dp, width = 88.dp)
            Skeleton(height = 36.dp, width = 36.dp, modifier = Modifier.padding(start = 6.dp))
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 14.dp, vertical = 18.dp),
        ) {
            Skeleton(height = 12.dp, width = 72.dp)
            Skeleton(height = 40.dp, width = 180.dp, modifier = Modifier.padding(top = 8.dp))
            Skeleton(height = 96.dp, modifier = Modifier.padding(top = 14.dp))
        }
        HomeWidgetBlockSkeleton(modifier = Modifier.padding(top = 16.dp))
        HomeWidgetBlockSkeleton(modifier = Modifier.padding(top = 12.dp), lines = 3, tallHeader = true)
        HomeWidgetBlockSkeleton(modifier = Modifier.padding(top = 12.dp), lines = 4)
        HomeWidgetBlockSkeleton(modifier = Modifier.padding(top = 12.dp), lines = 4)
    }
}

@Composable
private fun HomeWidgetBlockSkeleton(
    modifier: Modifier = Modifier,
    lines: Int = 4,
    tallHeader: Boolean = false,
) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(14.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Skeleton(height = 14.dp, width = 140.dp, modifier = Modifier.weight(1f))
            Skeleton(height = 28.dp, width = 28.dp)
        }
        if (tallHeader) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
            ) {
                repeat(3) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Skeleton(height = 40.dp, width = 40.dp)
                        Skeleton(height = 56.dp, width = 52.dp, modifier = Modifier.padding(top = 6.dp))
                    }
                }
            }
        }
        repeat(lines) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(companionSoftPanelColor())
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Skeleton(height = 32.dp, width = 44.dp)
                Skeleton(height = 12.dp, modifier = Modifier.padding(horizontal = 8.dp).weight(1f))
                Skeleton(height = 28.dp, width = 52.dp)
                Skeleton(height = 14.dp, width = 48.dp, modifier = Modifier.padding(start = 4.dp))
            }
        }
    }
}

@Composable
fun HomeStyleItemCardSkeleton(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth().padding(top = 10.dp, bottom = 2.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(companionSoftPanelColor())
                .padding(start = 114.dp, end = 12.dp, top = 14.dp, bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Skeleton(height = 14.dp, width = 140.dp)
                Skeleton(height = 11.dp, width = 96.dp, modifier = Modifier.padding(top = 8.dp))
            }
            Skeleton(height = 28.dp, width = 44.dp, modifier = Modifier.padding(end = 6.dp))
            Skeleton(height = 14.dp, width = 40.dp)
        }
        Skeleton(
            height = 80.dp,
            width = 96.dp,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

@Composable
fun InventoryPageSkeleton(modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 8.dp)) {
        Skeleton(height = 12.dp, width = 72.dp)
        Skeleton(height = 36.dp, width = 160.dp, modifier = Modifier.padding(top = 8.dp))
        Skeleton(height = 88.dp, modifier = Modifier.padding(top = 12.dp).fillMaxWidth())
        Skeleton(height = 48.dp, modifier = Modifier.padding(top = 12.dp).fillMaxWidth())
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Skeleton(height = 40.dp, modifier = Modifier.weight(1f))
            Skeleton(height = 40.dp, width = 44.dp)
        }
        Skeleton(height = 56.dp, modifier = Modifier.padding(top = 10.dp).fillMaxWidth())
        repeat(5) {
            HomeStyleItemCardSkeleton()
        }
    }
}

@Composable
fun WatchlistPageSkeleton(modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 8.dp)) {
        Skeleton(height = 14.dp, width = 140.dp, modifier = Modifier.padding(bottom = 12.dp))
        repeat(5) { HomeStyleItemCardSkeleton() }
    }
}

@Composable
fun CategoryItemsSkeleton(modifier: Modifier = Modifier, rows: Int = 6) {
    Column(modifier.padding(top = 8.dp)) {
        repeat(rows) { HomeStyleItemCardSkeleton() }
    }
}

@Composable
fun CatalogPageSkeleton(modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 8.dp)) {
        Skeleton(height = 52.dp, modifier = Modifier.fillMaxWidth())
        Skeleton(height = 14.dp, width = 100.dp, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(2) {
                Skeleton(
                    height = 72.dp,
                    modifier = Modifier.weight(1f),
                )
            }
        }
        Skeleton(height = 14.dp, width = 120.dp, modifier = Modifier.padding(top = 20.dp, bottom = 8.dp))
        repeat(2) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(bottom = 10.dp)) {
                repeat(2) {
                    Skeleton(height = 88.dp, modifier = Modifier.weight(1f))
                }
            }
        }
        repeat(3) { HomeStyleItemCardSkeleton() }
    }
}

@Composable
fun LeaderboardPageSkeleton(modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 8.dp)) {
        Skeleton(height = 48.dp, modifier = Modifier.fillMaxWidth())
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
        ) {
            repeat(3) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Skeleton(height = 40.dp, width = 40.dp)
                    Skeleton(height = 56.dp, width = 52.dp, modifier = Modifier.padding(top = 6.dp))
                }
            }
        }
        repeat(6) { HomeStyleItemCardSkeleton() }
    }
}
