package ru.mobile.uikit.catalog.showcase

import com.steamapps.companion.designsystem.components.CompanionAppBarSubtitle
import com.steamapps.companion.designsystem.components.CompanionAppBarTitle
import com.steamapps.companion.designsystem.components.CompanionAppBarTitleBlock
import com.steamapps.companion.designsystem.components.CompanionBreadcrumbs
import com.steamapps.companion.designsystem.components.CompanionContentSectionTitle
import com.steamapps.companion.designsystem.components.CompanionGroupLabel
import com.steamapps.companion.designsystem.components.CompanionHeaderPill
import com.steamapps.companion.designsystem.components.CompanionHeroBadge
import com.steamapps.companion.designsystem.components.CompanionHeroTitle
import com.steamapps.companion.designsystem.components.CompanionSectionTitle
import com.steamapps.companion.designsystem.components.CompanionWidgetSectionHead
import com.steamapps.companion.designsystem.components.ScreenHeader
import com.steamapps.companion.designsystem.components.ScreenScaffold
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.StoryFrame

fun typographyChromeStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "screen-header",
        title = "ScreenHeader / ScreenScaffold",
        keywords = listOf("header", "title", "scaffold"),
    ) {
        StoryFrame("ScreenHeader") {
            ScreenHeader(title = "Inventory", subtitle = "128 items")
        }
        StoryFrame("ScreenScaffold") {
            ScreenScaffold(title = "Catalog", subtitle = "Browse all items") {
                androidx.compose.material3.Text("Scrollable content area")
            }
        }
    },
    CatalogStory(
        id = "app-bar-block",
        title = "CompanionAppBarTitleBlock",
        keywords = listOf("appbar", "chrome"),
    ) {
        StoryFrame("Title + subtitle") {
            CompanionAppBarTitleBlock(
                title = "Page title",
                subtitle = "Signed in",
            )
        }
        StoryFrame("Atoms") {
            CompanionAppBarTitle("Page title")
            CompanionAppBarSubtitle("Secondary line")
            CompanionHeroTitle("Hero headline")
            CompanionHeroBadge("NEW")
        }
    },
    CatalogStory(
        id = "section-labels",
        title = "Section labels",
        keywords = listOf("typography", "section"),
    ) {
        StoryFrame("Labels") {
            CompanionSectionTitle("Section")
            CompanionGroupLabel("Group")
            CompanionContentSectionTitle("Content block")
            CompanionWidgetSectionHead(title = "Widget", actionLabel = "See all", onAction = {})
        }
    },
    CatalogStory(
        id = "breadcrumbs",
        title = "CompanionBreadcrumbs / HeaderPill",
    ) {
        StoryFrame("Breadcrumbs") {
            CompanionBreadcrumbs(
                segments = listOf(
                    com.steamapps.companion.designsystem.components.BreadcrumbSegment("Home") {},
                    com.steamapps.companion.designsystem.components.BreadcrumbSegment("Inventory") {},
                    com.steamapps.companion.designsystem.components.BreadcrumbSegment("Rifles"),
                ),
            )
        }
        StoryFrame("Header pill") {
            CompanionHeaderPill(label = "StatTrak™", onClick = {})
        }
    },
)
