package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.ButtonShape
import com.steamapps.companion.designsystem.theme.DisabledAlpha
import com.steamapps.companion.designsystem.theme.Space

enum class ButtonVariant {
    Primary,
    Secondary,
    Tertiary,
    Destructive,
}

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    fillWidth: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val interactive = enabled && !loading
    val sized = modifier
        .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
        .heightIn(min = if (fillWidth) Space.button else 40.dp)
    val body: @Composable RowScope.() -> Unit = {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                strokeWidth = 2.dp,
                color = contentColor(variant),
            )
        } else {
            if (leadingIcon != null) {
                leadingIcon()
                androidx.compose.foundation.layout.Spacer(Modifier.width(Space.xs))
            }
            content()
        }
    }
    when (variant) {
        ButtonVariant.Primary -> FilledButton(
            onClick = onClick,
            enabled = interactive,
            modifier = sized,
            container = MaterialTheme.colorScheme.primary,
            content = MaterialTheme.colorScheme.onPrimary,
            body = body,
        )
        ButtonVariant.Destructive -> FilledButton(
            onClick = onClick,
            enabled = interactive,
            modifier = sized,
            container = MaterialTheme.colorScheme.error,
            content = MaterialTheme.colorScheme.onError,
            body = body,
        )
        ButtonVariant.Secondary -> OutlinedButton(
            onClick = onClick,
            enabled = interactive,
            modifier = sized,
            shape = ButtonShape,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = DisabledAlpha.container),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = DisabledAlpha.content),
            ),
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(alpha = if (interactive) 1f else DisabledAlpha.content),
            ),
            content = body,
        )
        ButtonVariant.Tertiary -> TextButton(
            onClick = onClick,
            enabled = interactive,
            modifier = sized,
            shape = ButtonShape,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = DisabledAlpha.content),
            ),
            content = body,
        )
    }
}

@Composable
private fun contentColor(variant: ButtonVariant): Color = when (variant) {
    ButtonVariant.Primary -> MaterialTheme.colorScheme.onPrimary
    ButtonVariant.Destructive -> MaterialTheme.colorScheme.onError
    ButtonVariant.Secondary -> MaterialTheme.colorScheme.onSurface
    ButtonVariant.Tertiary -> MaterialTheme.colorScheme.primary
}

@Composable
private fun FilledButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier,
    container: Color,
    content: Color,
    body: @Composable RowScope.() -> Unit,
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = ButtonShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = container,
            contentColor = content,
            disabledContainerColor = container.copy(alpha = DisabledAlpha.container),
            disabledContentColor = content.copy(alpha = DisabledAlpha.content),
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = com.steamapps.companion.designsystem.theme.Elevation.none,
            pressedElevation = com.steamapps.companion.designsystem.theme.Elevation.none,
        ),
        content = body,
    )
}

/** Inline text action. Not full width and not 56dp tall. */
@Composable
fun Link(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    TextButton(onClick = onClick, enabled = enabled, modifier = modifier) {
        Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
    }
}
