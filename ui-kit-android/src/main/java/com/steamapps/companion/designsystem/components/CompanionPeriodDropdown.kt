package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class CompanionDropdownOption(
    val id: String,
    val label: String,
)

@Composable
fun CompanionPeriodDropdown(
    options: List<CompanionDropdownOption>,
    selectedId: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    compact: Boolean = false,
) {
    if (options.isEmpty()) return
    var expanded by remember { mutableStateOf(false) }
    val selectedLabel = options.firstOrNull { it.id == selectedId }?.label ?: options.first().label
    Surface(
        onClick = { expanded = true },
        modifier = modifier,
        shape = RoundedCornerShape(if (compact) 12.dp else 14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.75f),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.25f),
        ),
    ) {
        Row(
            Modifier.padding(
                horizontal = if (compact) 8.dp else 14.dp,
                vertical = if (compact) 6.dp else 10.dp,
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                selectedLabel,
                style = if (compact) MaterialTheme.typography.labelSmall else MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
            )
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 2.dp)
                    .size(if (compact) 18.dp else 22.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            option.label,
                            fontWeight = if (option.id == selectedId) FontWeight.Bold else FontWeight.Normal,
                        )
                    },
                    onClick = {
                        expanded = false
                        if (option.id != selectedId) onSelected(option.id)
                    },
                )
            }
        }
    }
}
