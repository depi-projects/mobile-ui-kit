package ru.mobile.uikit.catalog.showcase

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.components.CatalogPageSkeleton
import com.steamapps.companion.designsystem.components.CompanionLoadingLottie
import com.steamapps.companion.designsystem.components.CompanionSuccessLottie
import com.steamapps.companion.designsystem.components.CardSkeleton
import com.steamapps.companion.designsystem.components.ListSkeleton
import com.steamapps.companion.designsystem.components.HomeDashboardSkeleton
import com.steamapps.companion.designsystem.components.HomeStyleItemCardSkeleton
import com.steamapps.companion.designsystem.components.InventoryPageSkeleton
import com.steamapps.companion.designsystem.components.LeaderboardPageSkeleton
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.StoryFrame

fun skeletonLottieStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "skeletons",
        title = "Skeletons",
        description = "Loading placeholders — scroll section to compare density",
        keywords = listOf("skeleton", "shimmer", "loading"),
    ) {
        StoryFrame("ListSkeleton") { ListSkeleton(rows = 4) }
        StoryFrame("CardSkeleton") { CardSkeleton() }
        StoryFrame("HomeStyleItemCardSkeleton") { HomeStyleItemCardSkeleton() }
        StoryFrame("HomeDashboardSkeleton") {
            BoxShort { HomeDashboardSkeleton() }
        }
        StoryFrame("InventoryPageSkeleton") {
            BoxShort { InventoryPageSkeleton() }
        }
        StoryFrame("LeaderboardPageSkeleton") {
            BoxShort { LeaderboardPageSkeleton() }
        }
        StoryFrame("CatalogPageSkeleton") {
            BoxShort { CatalogPageSkeleton() }
        }
    },
    CatalogStory(
        id = "lottie",
        title = "Lottie",
        keywords = listOf("animation", "lottie"),
    ) {
        StoryFrame("Loading") { CompanionLoadingLottie() }
        StoryFrame("Success") { CompanionSuccessLottie() }
    },
)

@androidx.compose.runtime.Composable
private fun BoxShort(content: @androidx.compose.runtime.Composable () -> Unit) {
    androidx.compose.foundation.layout.Box(Modifier.height(320.dp)) {
        content()
    }
}
