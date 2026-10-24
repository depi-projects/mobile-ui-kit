package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.AppThemeMode
import com.steamapps.companion.designsystem.theme.Palette

@Composable
fun ThemeOptionList(
    selected: AppThemeMode,
    onSelect: (AppThemeMode) -> Unit,
    optionLabel: @Composable (AppThemeMode) -> String,
    modifier: Modifier = Modifier,
) {
    ListCard(modifier = modifier.fillMaxWidth()) {
        AppThemeMode.entries.forEachIndexed { index, mode ->
            if (index > 0) ListDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(mode) }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = optionLabel(mode),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier.weight(1f),
                )
                if (selected == mode) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Palette.Coral)
                }
            }
        }
    }
}
