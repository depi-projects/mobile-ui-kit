package ru.mobile.uikit.catalog.model

import androidx.compose.runtime.Composable

data class CatalogStory(
    val id: String,
    val title: String,
    val description: String = "",
    val keywords: List<String> = emptyList(),
    val content: @Composable () -> Unit,
)

data class CatalogSection(
    val id: String,
    val title: String,
    val subtitle: String = "",
    val stories: List<CatalogStory>,
) {
    val storyCount: Int get() = stories.size
}

enum class CatalogFontScale(val label: String, val scale: Float) {
    Default("100%", 1f),
    Large("130%", 1.3f),
    ExtraLarge("150%", 1.5f),
}
