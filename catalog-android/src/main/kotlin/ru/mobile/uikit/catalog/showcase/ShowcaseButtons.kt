package ru.mobile.uikit.catalog.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.components.CompanionChromeIcon
import com.steamapps.companion.designsystem.components.CompanionChromeIconButton
import com.steamapps.companion.designsystem.components.CompanionChromeTextButton
import com.steamapps.companion.designsystem.components.Button
import com.steamapps.companion.designsystem.components.ButtonVariant
import com.steamapps.companion.designsystem.components.DestructiveRow
import com.steamapps.companion.designsystem.components.SignOutButton
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.LocalCatalogPlayground
import ru.mobile.uikit.catalog.ui.StoryFrame

fun buttonStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "button",
        title = "Button",
        description = "Primary · Secondary · disabled",
        keywords = listOf("button", "primary", "secondary", "cta"),
    ) {
        val playground = LocalCatalogPlayground.current
        StoryFrame("Primary") { Button(onClick = {}) { Text("Continue") } }
        StoryFrame("Secondary") {
            Button(onClick = {}, variant = ButtonVariant.Secondary) { Text("Secondary action") }
        }
        StoryFrame("Disabled") {
            Button(onClick = {}, enabled = !playground.disabledStates) { Text("Disabled") }
        }
    },
    CatalogStory(
        id = "chrome-text",
        title = "CompanionChromeTextButton",
        keywords = listOf("button", "text", "chrome"),
    ) {
        StoryFrame("Default") { CompanionChromeTextButton(label = "View all", onClick = {}) }
    },
    CatalogStory(
        id = "chrome-icon",
        title = "CompanionChromeIconButton",
        description = "All icon variants",
        keywords = listOf("icon", "button"),
    ) {
        StoryFrame("Icons") {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CompanionChromeIcon.entries.forEach { icon ->
                    CompanionChromeIconButton(icon = icon, onClick = {})
                }
            }
        }
    },
    CatalogStory(
        id = "settings-signout",
        title = "SignOutButton",
        keywords = listOf("settings", "destructive"),
    ) {
        StoryFrame("Sign out") { SignOutButton(title = "Sign out", onClick = {}) }
    },
    CatalogStory(
        id = "settings-destructive-row",
        title = "DestructiveRow",
    ) {
        StoryFrame("Destructive row") {
            DestructiveRow(title = "Delete account", onClick = {})
        }
    },
)
