package ru.mobile.uikit.catalog.showcase

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.steamapps.companion.designsystem.components.FontOptionList
import com.steamapps.companion.designsystem.components.InventoryPrivacyIcon
import com.steamapps.companion.designsystem.components.PrivateInventoryHelp
import com.steamapps.companion.designsystem.components.ThemeOptionList
import com.steamapps.companion.designsystem.theme.AppFontChoice
import com.steamapps.companion.designsystem.theme.AppThemeMode
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.StoryFrame

fun miscStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "theme-font-pickers",
        title = "Theme & font pickers",
        keywords = listOf("settings", "theme", "font"),
    ) {
        var theme by remember { mutableStateOf(AppThemeMode.System) }
        var font by remember { mutableStateOf(AppFontChoice.default) }
        StoryFrame("ThemeOptionList") {
            ThemeOptionList(
                selected = theme,
                onSelect = { theme = it },
                optionLabel = { mode ->
                    when (mode) {
                        AppThemeMode.System -> "System"
                        AppThemeMode.Light -> "Light"
                        AppThemeMode.Dark -> "Dark"
                    }
                },
            )
        }
        StoryFrame("FontOptionList") {
            FontOptionList(
                selected = font,
                onSelect = { font = it },
                previewSample = "Ag 0123 — Steam Market",
            )
        }
    },
    CatalogStory(
        id = "privacy",
        title = "Privacy UI",
        keywords = listOf("privacy", "inventory"),
    ) {
        StoryFrame("InventoryPrivacyIcon statuses") {
            Column {
                InventoryPrivacyIcon(status = "public")
                InventoryPrivacyIcon(status = "private")
                InventoryPrivacyIcon(status = "friends_only")
                InventoryPrivacyIcon(status = "error")
                InventoryPrivacyIcon(status = null, profilePublic = false)
            }
        }
        StoryFrame("PrivateInventoryHelp") {
            PrivateInventoryHelp()
        }
    },
)
