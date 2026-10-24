package ru.mobile.uikit.catalog.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StoryFrame(
    title: String,
    description: String = "",
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val playground = LocalCatalogPlayground.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        if (description.isNotBlank()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
            )
        } else {
            androidx.compose.foundation.layout.Spacer(Modifier.padding(bottom = 8.dp))
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (playground.narrowWidth) {
                        Modifier.widthIn(max = 320.dp)
                    } else {
                        Modifier
                    },
                )
                .then(
                    if (playground.showLayoutBounds) {
                        Modifier.border(1.dp, Color(0xFFE11D48), RoundedCornerShape(12.dp))
                    } else {
                        Modifier
                    },
                ),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
            tonalElevation = 0.dp,
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                content = content,
            )
        }
    }
}
