package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties

/**
 * Confirmation dialog. `destructive` paints the confirm action with [ButtonVariant.Destructive].
 * Dismiss is a text action. Tap outside and system back call [onDismiss] unless [dismissOnBack] / [dismissOnClickOutside] is false.
 */
@Composable
fun Dialog(
    visible: Boolean,
    title: String,
    onDismiss: () -> Unit,
    confirmLabel: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    message: String? = null,
    dismissLabel: String? = null,
    destructive: Boolean = false,
    loading: Boolean = false,
    error: String? = null,
    dismissOnBack: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    content: @Composable (() -> Unit)? = null,
) {
    if (!visible) return
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBack,
            dismissOnClickOutside = dismissOnClickOutside,
        ),
        title = { Text(title) },
        text = {
            androidx.compose.foundation.layout.Column {
                if (!message.isNullOrBlank()) {
                    Text(message, style = MaterialTheme.typography.bodyMedium)
                }
                content?.invoke()
                if (!error.isNullOrBlank()) {
                    Text(
                        error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm, enabled = !loading) {
                Text(
                    confirmLabel,
                    color = if (destructive) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.primary
                    },
                )
            }
        },
        dismissButton = dismissLabel?.let { label ->
            {
                TextButton(onClick = onDismiss) { Text(label) }
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

enum class SnackbarVariant {
    Default,
    Success,
    Error,
}

/** Short = 4s, Long = 10s, Indefinite = until action or dismiss. */
enum class SnackbarDuration {
    Short,
    Long,
    Indefinite,
}

/** One-line transient message. Queue: one visible snackbar; the next waits until dismiss. */
@Composable
fun Snackbar(
    message: String,
    modifier: Modifier = Modifier,
    variant: SnackbarVariant = SnackbarVariant.Default,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
) {
    val container = when (variant) {
        SnackbarVariant.Default -> MaterialTheme.colorScheme.inverseSurface
        SnackbarVariant.Success -> MaterialTheme.colorScheme.tertiaryContainer
        SnackbarVariant.Error -> MaterialTheme.colorScheme.errorContainer
    }
    val content = when (variant) {
        SnackbarVariant.Default -> MaterialTheme.colorScheme.inverseOnSurface
        SnackbarVariant.Success -> MaterialTheme.colorScheme.onTertiaryContainer
        SnackbarVariant.Error -> MaterialTheme.colorScheme.onErrorContainer
    }
    val actionColor = when (variant) {
        SnackbarVariant.Default -> MaterialTheme.colorScheme.inversePrimary
        SnackbarVariant.Success -> MaterialTheme.colorScheme.tertiary
        SnackbarVariant.Error -> MaterialTheme.colorScheme.error
    }
    androidx.compose.material3.Snackbar(
        modifier = modifier,
        action = if (actionLabel != null && onAction != null) {
            { TextButton(onClick = onAction) { Text(actionLabel) } }
        } else {
            null
        },
        dismissAction = onDismiss?.let { dismiss ->
            { TextButton(onClick = dismiss) { Text("×") } }
        },
        containerColor = container,
        contentColor = content,
        actionContentColor = actionColor,
    ) {
        Text(message)
    }
}

/** Material queue: one snackbar at a time, the next waits. */
@Composable
fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.SnackbarHost(hostState, modifier) { data ->
        Snackbar(
            message = data.visuals.message,
            actionLabel = data.visuals.actionLabel,
            onAction = { data.performAction() },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tooltip(
    text: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val state = rememberTooltipState()
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = { PlainTooltip { Text(text) } },
        state = state,
        modifier = modifier,
        content = content,
    )
}

@Composable
fun Menu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    offset: androidx.compose.ui.unit.DpOffset = androidx.compose.ui.unit.DpOffset.Zero,
    content: @Composable ColumnScope.() -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = modifier,
        offset = offset,
        containerColor = MaterialTheme.colorScheme.surface,
        content = content,
    )
}

@Composable
fun MenuItem(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    destructive: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    DropdownMenuItem(
        text = {
            Text(
                label,
                color = if (destructive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
            )
        },
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        leadingIcon = leadingIcon,
        trailingIcon = trailing,
    )
}
