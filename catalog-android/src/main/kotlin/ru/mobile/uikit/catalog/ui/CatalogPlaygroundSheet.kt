package ru.mobile.uikit.catalog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.AppThemeMode
import ru.mobile.uikit.catalog.model.CatalogFontScale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CatalogPlaygroundSheet(
    controller: CatalogPlaygroundController,
    onDismiss: () -> Unit,
) {
    val state = controller.state
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .padding(bottom = 32.dp),
        ) {
            Text("Playground", style = MaterialTheme.typography.titleLarge)
            Text(
                "Stress-test components like Storybook addons.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            SheetLabel("Theme")
            ChipRow {
                AppThemeMode.entries.forEach { mode ->
                    FilterChip(
                        selected = state.themeMode == mode,
                        onClick = { controller.update { it.copy(themeMode = mode) } },
                        label = { Text(mode.name) },
                    )
                }
            }

            SheetLabel("Font scale (a11y)")
            ChipRow {
                CatalogFontScale.entries.forEach { scale ->
                    FilterChip(
                        selected = state.fontScale == scale,
                        onClick = { controller.update { it.copy(fontScale = scale) } },
                        label = { Text(scale.label) },
                    )
                }
            }

            SheetLabel("Layout")
            ChipRow {
                FilterChip(
                    selected = state.narrowWidth,
                    onClick = { controller.update { it.copy(narrowWidth = !it.narrowWidth) } },
                    label = { Text("Narrow 320dp") },
                )
                FilterChip(
                    selected = state.showLayoutBounds,
                    onClick = { controller.update { it.copy(showLayoutBounds = !it.showLayoutBounds) } },
                    label = { Text("Layout bounds") },
                )
                FilterChip(
                    selected = state.disabledStates,
                    onClick = { controller.update { it.copy(disabledStates = !it.disabledStates) } },
                    label = { Text("Disabled states") },
                )
            }
        }
    }
}

@Composable
private fun SheetLabel(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChipRow(content: @Composable () -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 4.dp),
        content = { content() },
    )
}
