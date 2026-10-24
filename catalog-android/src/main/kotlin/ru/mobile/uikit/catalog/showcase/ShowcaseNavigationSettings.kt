package ru.mobile.uikit.catalog.showcase

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.steamapps.companion.designsystem.components.CompanionSegmentOption
import com.steamapps.companion.designsystem.components.CompanionSegmentedControl
import com.steamapps.companion.designsystem.components.NavTab
import com.steamapps.companion.designsystem.components.NavigationBar
import com.steamapps.companion.designsystem.components.ListCard
import com.steamapps.companion.designsystem.components.ListDivider
import com.steamapps.companion.designsystem.components.ListRow
import com.steamapps.companion.designsystem.components.ListSectionTitle
import com.steamapps.companion.designsystem.components.SwitchRow
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.LocalCatalogPlayground
import ru.mobile.uikit.catalog.ui.StoryFrame

fun navigationSettingsStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "nav-bar",
        title = "NavigationBar",
        description = "Tab selection · center highlight · search",
        keywords = listOf("navigation", "bottom bar", "tabs"),
    ) {
        val tabs = remember {
            listOf(
                NavTab("home", "Home", Icons.Default.Home),
                NavTab("inv", "Items", Icons.Default.Star),
                NavTab("profile", "Profile", Icons.Default.Person),
            )
        }
        var route by remember { mutableStateOf("home") }
        StoryFrame("Standard") {
            NavigationBar(
                tabs = tabs,
                currentRoute = route,
                onTabSelected = { route = it },
            )
        }
        StoryFrame("Center highlight + search") {
            NavigationBar(
                tabs = tabs,
                currentRoute = route,
                onTabSelected = { route = it },
                centerHighlightRoute = "inv",
                searchTab = NavTab("search", "Search", Icons.Default.Search),
                searchSelected = route == "search",
            )
        }
    },
    CatalogStory(
        id = "segmented",
        title = "CompanionSegmentedControl",
        keywords = listOf("segment", "toggle"),
    ) {
        var selected by remember { mutableStateOf("7d") }
        StoryFrame("Three options") {
            CompanionSegmentedControl(
                options = listOf(
                    CompanionSegmentOption("7d", "7D"),
                    CompanionSegmentOption("30d", "30D"),
                    CompanionSegmentOption("all", "All"),
                ),
                selectedId = selected,
                onSelected = { selected = it },
            )
        }
    },
    CatalogStory(
        id = "settings-list",
        title = "Settings list kit",
        keywords = listOf("settings", "list", "switch"),
    ) {
        var wifiOnly by remember { mutableStateOf(true) }
        val disabled = LocalCatalogPlayground.current.disabledStates
        StoryFrame("Card + rows") {
            ListSectionTitle("Preferences")
            ListCard {
                SwitchRow(
                    title = "Wi‑Fi only sync",
                    checked = wifiOnly,
                    onCheckedChange = { wifiOnly = it },
                )
                ListDivider()
                ListRow(
                    title = "Language",
                    subtitle = "English",
                    onClick = if (disabled) null else ({ }),
                )
                ListRow(
                    title = "Disabled row",
                    subtitle = "No click handler",
                    showChevron = false,
                )
            }
        }
    },
)
