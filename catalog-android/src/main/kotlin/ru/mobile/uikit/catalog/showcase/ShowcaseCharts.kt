package ru.mobile.uikit.catalog.showcase

import androidx.compose.ui.graphics.Color
import com.steamapps.companion.designsystem.components.Bar
import com.steamapps.companion.designsystem.components.BarChart
import com.steamapps.companion.designsystem.components.ColumnChart
import com.steamapps.companion.designsystem.components.LineChart
import com.steamapps.companion.designsystem.components.Sparkline
import com.steamapps.companion.designsystem.components.RarityIndicatorStyle
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.StoryFrame

fun chartStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "charts",
        title = "Charts",
        keywords = listOf("chart", "graph", "sparkline"),
    ) {
        StoryFrame("BarChart") {
            BarChart(
                bars = listOf(
                    Bar("Mon", 12f),
                    Bar("Tue", 28f),
                    Bar("Wed", 18f),
                ),
                valueSuffix = " €",
            )
        }
        StoryFrame("ColumnChart") {
            ColumnChart(values = listOf(4f, 9f, 6f, 11f))
        }
        StoryFrame("Sparkline") {
            Sparkline(values = listOf(1f, 3f, 2f, 5f, 4f, 6f, 5f))
        }
        StoryFrame("LineChart (Vico)") {
            LineChart(
                values = listOf(10f, 14f, 9f, 16f, 22f, 18f),
                pointCaptions = listOf("$10", "$14", "$9", "$16", "$22", "$18"),
            )
        }
    },
    CatalogStory(
        id = "rarity",
        title = "Rarity indicators",
        keywords = listOf("rarity", "chip"),
    ) {
        val color = Color(0xFFD32CE6)
        StoryFrame("Dot + chip + row") {
            com.steamapps.companion.designsystem.components.RarityDot(color = color)
            com.steamapps.companion.designsystem.components.RarityChip(
                label = "Classified",
                color = color,
            )
            com.steamapps.companion.designsystem.components.RarityRow(
                label = "Covert",
                color = color,
                style = RarityIndicatorStyle.SideGutter,
            )
        }
    },
)
