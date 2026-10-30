package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.steamapps.companion.designsystem.theme.Palette
import java.util.Locale

fun formatItemUsd(value: Double): String = String.format(Locale.US, "$%,.0f", value)

@Composable
fun CompanionItemValueText(
    usd: Double?,
    modifier: Modifier = Modifier,
    compact: Boolean = false,
    fallback: String? = null,
    valueColor: Color = Palette.Gold,
    loading: Boolean = false,
) {
    when {
        loading && (usd == null || usd <= 0.0) -> CompanionLoadingLottie(
            modifier = modifier.size(if (compact) 22.dp else 28.dp),
            size = if (compact) 22.dp else 28.dp,
        )
        usd != null && usd > 0.0 -> Text(
            formatItemUsd(usd),
            modifier = modifier,
            fontWeight = FontWeight.ExtraBold,
            fontSize = if (compact) 15.sp else 18.sp,
            letterSpacing = (-0.3).sp,
            color = valueColor,
            maxLines = 1,
        )
        !fallback.isNullOrBlank() -> Text(
            fallback,
            modifier = modifier,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
        )
    }
}
