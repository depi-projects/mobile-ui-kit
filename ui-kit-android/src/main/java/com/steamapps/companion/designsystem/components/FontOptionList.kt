package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.steamapps.companion.designsystem.theme.AppFontChoice
import com.steamapps.companion.designsystem.theme.Palette

@Composable
fun FontOptionList(
    selected: AppFontChoice,
    onSelect: (AppFontChoice) -> Unit,
    previewSample: String,
    modifier: Modifier = Modifier,
) {
    ListCard(modifier = modifier.fillMaxWidth()) {
        AppFontChoice.entries.forEachIndexed { index, font ->
            if (index > 0) ListDivider()
            val family = font.fontFamily()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(font) }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = font.displayName,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = family,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                    Text(
                        text = previewSample,
                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = family),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
                if (selected == font) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Palette.Coral)
                }
            }
        }
    }
}
