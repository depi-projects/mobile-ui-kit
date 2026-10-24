package com.steamapps.companion.designsystem.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics

@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    label: String? = null,
) {
    val described = modifier.semantics {
        if (label != null) contentDescription = label
        if (progress != null) {
            progressBarRangeInfo = androidx.compose.ui.semantics.ProgressBarRangeInfo(progress.coerceIn(0f, 1f), 0f..1f)
        }
    }
    if (progress == null) {
        CircularProgressIndicator(modifier = described, color = MaterialTheme.colorScheme.primary)
    } else {
        CircularProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = described,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun LinearProgress(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    label: String? = null,
) {
    val described = modifier.semantics {
        if (label != null) contentDescription = label
        if (progress != null) {
            progressBarRangeInfo = androidx.compose.ui.semantics.ProgressBarRangeInfo(progress.coerceIn(0f, 1f), 0f..1f)
        }
    }
    if (progress == null) {
        LinearProgressIndicator(modifier = described, color = MaterialTheme.colorScheme.primary)
    } else {
        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = described,
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}
