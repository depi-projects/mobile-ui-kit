package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CompanionContentSection(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Panel(modifier = modifier.padding(bottom = 12.dp)) {
        title?.let {
            CompanionContentSectionTitle(it)
        }
        Column(content = content)
    }
}
