package ru.mobile.uikit.catalog

import ru.mobile.uikit.catalog.model.CatalogSection
import ru.mobile.uikit.catalog.showcase.buttonStories
import ru.mobile.uikit.catalog.showcase.cardsTilesStories
import ru.mobile.uikit.catalog.showcase.chartStories
import ru.mobile.uikit.catalog.showcase.feedbackStatesStories
import ru.mobile.uikit.catalog.showcase.inputsAndSurfaceStories
import ru.mobile.uikit.catalog.showcase.miscStories
import ru.mobile.uikit.catalog.showcase.navigationSettingsStories
import ru.mobile.uikit.catalog.showcase.skeletonLottieStories
import ru.mobile.uikit.catalog.showcase.typographyChromeStories

object CatalogRegistry {
    val sections: List<CatalogSection> = listOf(
        CatalogSection(
            id = "buttons",
            title = "Buttons & actions",
            subtitle = "Primary, secondary, chrome, destructive",
            stories = buttonStories(),
        ),
        CatalogSection(
            id = "inputs",
            title = "Inputs & surfaces",
            stories = inputsAndSurfaceStories(),
        ),
        CatalogSection(
            id = "typography",
            title = "Typography & chrome",
            stories = typographyChromeStories(),
        ),
        CatalogSection(
            id = "navigation",
            title = "Navigation & settings",
            stories = navigationSettingsStories(),
        ),
        CatalogSection(
            id = "feedback",
            title = "Alerts & states",
            stories = feedbackStatesStories(),
        ),
        CatalogSection(
            id = "cards",
            title = "Cards & tiles",
            stories = cardsTilesStories(),
        ),
        CatalogSection(
            id = "charts",
            title = "Charts & rarity",
            stories = chartStories(),
        ),
        CatalogSection(
            id = "skeletons",
            title = "Skeletons & motion",
            stories = skeletonLottieStories(),
        ),
        CatalogSection(
            id = "misc",
            title = "Pickers & privacy",
            stories = miscStories(),
        ),
    )

    val totalStories: Int get() = sections.sumOf { it.storyCount }

    fun section(id: String): CatalogSection? = sections.find { it.id == id }

    fun search(query: String): List<CatalogSection> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return sections
        return sections.mapNotNull { section ->
            val matchedStories = section.stories.filter { story ->
                story.title.lowercase().contains(q) ||
                    story.description.lowercase().contains(q) ||
                    story.keywords.any { it.lowercase().contains(q) }
            }
            if (matchedStories.isEmpty()) {
                if (section.title.lowercase().contains(q)) section else null
            } else {
                section.copy(stories = matchedStories)
            }
        }
    }
}
