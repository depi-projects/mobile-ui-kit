package com.steamapps.companion.designsystem.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding

@Composable
fun PrivateInventoryHelp(modifier: Modifier = Modifier) {
    Text(
        "Steam → Profile → Edit → Privacy: set Profile and Game details to Public, " +
            "and Inventory to Public (Friends Only cannot be read without Steam cookies).",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier.padding(top = 12.dp),
    )
}
