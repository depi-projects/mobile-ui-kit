package ru.mobile.uikit.catalog.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mobile.uikit.catalog.CatalogRegistry

@Composable
fun CatalogHomeScreen(
    onOpenSection: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var query by rememberSaveable { mutableStateOf("") }
    val sections = CatalogRegistry.search(query)

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        item {
            Column(Modifier.padding(bottom = 12.dp)) {
                Text("UI Kit Catalog", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "${CatalogRegistry.totalStories} stories · ${CatalogRegistry.sections.size} groups",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    "Open Playground (top bar) for light/dark, font scale, and narrow width.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                placeholder = { Text("Search components…") },
                singleLine = true,
            )
        }
        items(sections, key = { it.id }) { section ->
            ListItem(
                headlineContent = { Text(section.title) },
                supportingContent = {
                    Text(
                        section.subtitle.ifBlank { "${section.storyCount} stories" },
                        maxLines = 2,
                    )
                },
                trailingContent = {
                    Text(
                        "${section.storyCount}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                modifier = Modifier.clickable { onOpenSection(section.id) },
            )
        }
    }
}
