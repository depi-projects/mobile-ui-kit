package ru.mobile.uikit.catalog.showcase

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.steamapps.companion.designsystem.components.Panel
import com.steamapps.companion.designsystem.components.ScreenBackground
import com.steamapps.companion.designsystem.components.TextField
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.StoryFrame

fun inputsAndSurfaceStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "text-field",
        title = "TextField",
        keywords = listOf("input", "textfield", "search"),
    ) {
        var query by remember { mutableStateOf("") }
        StoryFrame("Empty") {
            TextField(value = query, onValueChange = { query = it }, label = "Search items")
        }
        StoryFrame("With value") {
            TextField(value = "AK-47 | Redline", onValueChange = {}, label = "Search items")
        }
    },
    CatalogStory(
        id = "panel",
        title = "Panel",
        description = "showAccentStripe true / false",
        keywords = listOf("card", "panel", "surface"),
    ) {
        StoryFrame("Plain") {
            Panel { Text("Portfolio summary and stats") }
        }
        StoryFrame("Accent stripe") {
            Panel(showAccentStripe = true) { Text("Featured panel with gradient stripe") }
        }
    },
    CatalogStory(
        id = "screen-background",
        title = "ScreenBackground",
        keywords = listOf("background", "canvas"),
    ) {
        StoryFrame("Default") {
            ScreenBackground {
                Text("Content on the kit background")
            }
        }
    },
)
