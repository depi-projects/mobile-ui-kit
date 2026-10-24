package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

enum class CompanionAlertType {
    Info,
    Success,
    Warning,
    Error,
}

@Composable
fun CompanionPageAlert(
    type: CompanionAlertType,
    message: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    detail: String? = null,
    action: @Composable (() -> Unit)? = null,
) {
    if (message.isBlank()) return
    val style = alertStyle(type)
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = style.container,
        border = BorderStroke(1.dp, style.border),
    ) {
        Row(
            Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(
                style.icon,
                contentDescription = null,
                tint = style.iconTint,
                modifier = Modifier
                    .size(22.dp)
                    .padding(top = 1.dp),
            )
            Column(Modifier.padding(start = 12.dp)) {
                if (!title.isNullOrBlank()) {
                    Text(
                        title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = style.titleColor,
                    )
                }
                Text(
                    message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = if (title != null) Modifier.padding(top = 2.dp) else Modifier,
                )
                if (!detail.isNullOrBlank()) {
                    Text(
                        detail,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 6.dp),
                    )
                }
                action?.invoke()
            }
        }
    }
}

@Composable
fun CompanionPageAlerts(
    alerts: List<CompanionPageAlertModel>,
    modifier: Modifier = Modifier,
) {
    if (alerts.isEmpty()) return
    Column(modifier, verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
        alerts.forEach { alert ->
            CompanionPageAlert(
                type = alert.type,
                title = alert.title,
                message = alert.message,
                detail = alert.detail,
            )
        }
    }
}

data class CompanionPageAlertModel(
    val type: CompanionAlertType,
    val message: String,
    val title: String? = null,
    val detail: String? = null,
)

private data class AlertStyle(
    val container: Color,
    val border: Color,
    val icon: ImageVector,
    val iconTint: Color,
    val titleColor: Color,
)

@Composable
private fun alertStyle(type: CompanionAlertType): AlertStyle = when (type) {
    CompanionAlertType.Info -> AlertStyle(
        container = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
        border = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        icon = Icons.Outlined.Info,
        iconTint = MaterialTheme.colorScheme.primary,
        titleColor = MaterialTheme.colorScheme.primary,
    )
    CompanionAlertType.Success -> AlertStyle(
        container = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.45f),
        border = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.35f),
        icon = Icons.Outlined.CheckCircle,
        iconTint = MaterialTheme.colorScheme.tertiary,
        titleColor = MaterialTheme.colorScheme.onTertiaryContainer,
    )
    CompanionAlertType.Warning -> AlertStyle(
        container = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f),
        border = MaterialTheme.colorScheme.secondary.copy(alpha = 0.35f),
        icon = Icons.Outlined.WarningAmber,
        iconTint = MaterialTheme.colorScheme.secondary,
        titleColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
    CompanionAlertType.Error -> AlertStyle(
        container = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.45f),
        border = MaterialTheme.colorScheme.error.copy(alpha = 0.35f),
        icon = Icons.Outlined.ErrorOutline,
        iconTint = MaterialTheme.colorScheme.error,
        titleColor = MaterialTheme.colorScheme.error,
    )
}
