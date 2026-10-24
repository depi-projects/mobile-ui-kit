package ru.mobile.uikit.catalog.showcase

import com.steamapps.companion.designsystem.components.CompanionAlertType
import com.steamapps.companion.designsystem.components.CompanionPageAlert
import com.steamapps.companion.designsystem.components.CompanionPageAlertModel
import com.steamapps.companion.designsystem.components.CompanionPageAlerts
import com.steamapps.companion.designsystem.components.EmptyState
import com.steamapps.companion.designsystem.components.ErrorState
import com.steamapps.companion.designsystem.components.LoadingState
import com.steamapps.companion.designsystem.components.OfflineStyleErrorState
import com.steamapps.companion.designsystem.components.SearchEmptyState
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.StoryFrame

fun feedbackStatesStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "alerts",
        title = "CompanionPageAlert",
        description = "Info · Warning · Error (+ title/detail)",
        keywords = listOf("alert", "banner", "feedback"),
    ) {
        CompanionAlertType.entries.forEach { type ->
            StoryFrame(type.name) {
                CompanionPageAlert(
                    type = type,
                    title = "${type.name} title",
                    message = "Short message for ${type.name.lowercase()} state.",
                    detail = "Optional detail line for QA.",
                )
            }
        }
        StoryFrame("Stack (CompanionPageAlerts)") {
            CompanionPageAlerts(
                alerts = listOf(
                    CompanionPageAlertModel(CompanionAlertType.Info, "Sync in progress"),
                    CompanionPageAlertModel(CompanionAlertType.Warning, "Prices may be stale"),
                ),
            )
        }
    },
    CatalogStory(
        id = "async-states",
        title = "Loading / Empty / Error",
        keywords = listOf("state", "empty", "error", "loading"),
    ) {
        StoryFrame("LoadingState") { LoadingState(message = "Loading inventory…") }
        StoryFrame("EmptyState") { EmptyState(title = "No items", message = "Try another filter.") }
        StoryFrame("ErrorState") {
            ErrorState(
                message = "Could not reach server.",
                action = {
                    com.steamapps.companion.designsystem.components.Button(onClick = {}) {
                        androidx.compose.material3.Text("Retry")
                    }
                },
            )
        }
        StoryFrame("SearchEmptyState") { SearchEmptyState() }
        StoryFrame("OfflineStyleErrorState") {
            OfflineStyleErrorState(message = "Offline — showing cached data.")
        }
    },
)
