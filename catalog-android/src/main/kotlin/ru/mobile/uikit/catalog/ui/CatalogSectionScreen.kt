package ru.mobile.uikit.catalog.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import ru.mobile.uikit.catalog.CatalogRegistry
import ru.mobile.uikit.catalog.model.CatalogStory

@Composable
fun CatalogSectionScreen(
    sectionId: String,
    modifier: Modifier = Modifier,
) {
    val section = CatalogRegistry.section(sectionId)
    if (section == null) {
        Text("Unknown section", modifier = modifier)
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    ) {
        item {
            Text(section.title, style = MaterialTheme.typography.headlineSmall)
            if (section.subtitle.isNotBlank()) {
                Text(
                    section.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp, top = 4.dp),
                )
            }
        }
        items(section.stories, key = { it.id }) { story ->
            StoryBlock(story)
        }
    }
}

@Composable
private fun StoryBlock(story: CatalogStory) {
    Text(
        story.title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
    )
    if (story.description.isNotBlank()) {
        Text(
            story.description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp),
        )
    }
    story.content()
}
