package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.DisabledAlpha
import com.steamapps.companion.designsystem.theme.Space

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
) {
    val control = @Composable {
        androidx.compose.material3.Switch(
            checked = checked,
            onCheckedChange = if (label == null) onCheckedChange else null,
            enabled = enabled,
            colors = switchColors(),
        )
    }
    if (label == null) {
        control()
    } else {
        Row(
            modifier
                .toggleable(value = checked, enabled = enabled, role = Role.Switch, onValueChange = onCheckedChange)
                .padding(vertical = Space.xs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            control()
            Text(label, modifier = Modifier.padding(start = Space.sm), style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
) {
    val colors = CheckboxDefaults.colors(
        checkedColor = MaterialTheme.colorScheme.primary,
        uncheckedColor = MaterialTheme.colorScheme.outline,
        checkmarkColor = MaterialTheme.colorScheme.onPrimary,
        disabledCheckedColor = MaterialTheme.colorScheme.primary.copy(alpha = DisabledAlpha.content),
        disabledUncheckedColor = MaterialTheme.colorScheme.outline.copy(alpha = DisabledAlpha.content),
    )
    val box = @Composable {
        androidx.compose.material3.Checkbox(
            checked = checked,
            onCheckedChange = if (label == null) onCheckedChange else null,
            enabled = enabled,
            colors = colors,
        )
    }
    if (label == null) {
        box()
    } else {
        Row(
            modifier
                .toggleable(value = checked, enabled = enabled, role = Role.Checkbox, onValueChange = onCheckedChange)
                .padding(vertical = Space.xxs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            box()
            Text(label, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun Radio(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
) {
    val colors = RadioButtonDefaults.colors(
        selectedColor = MaterialTheme.colorScheme.primary,
        unselectedColor = MaterialTheme.colorScheme.outline,
        disabledSelectedColor = MaterialTheme.colorScheme.primary.copy(alpha = DisabledAlpha.content),
        disabledUnselectedColor = MaterialTheme.colorScheme.outline.copy(alpha = DisabledAlpha.content),
    )
    val button = @Composable {
        androidx.compose.material3.RadioButton(
            selected = selected,
            onClick = if (label == null) onClick else null,
            enabled = enabled,
            colors = colors,
        )
    }
    if (label == null) {
        button()
    } else {
        Row(
            modifier
                .selectable(selected = selected, enabled = enabled, role = Role.RadioButton, onClick = onClick)
                .padding(vertical = Space.xxs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            button()
            Text(label, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    label: String? = null,
) {
    androidx.compose.foundation.layout.Column(modifier) {
        if (label != null) {
            Text(label, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface)
        }
        androidx.compose.material3.Slider(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            valueRange = valueRange,
            steps = steps,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledThumbColor = MaterialTheme.colorScheme.primary.copy(alpha = DisabledAlpha.content),
                disabledActiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = DisabledAlpha.content),
                disabledInactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = DisabledAlpha.content),
            ),
        )
    }
}

@Composable
fun FormField(
    label: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    isError: Boolean = false,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        Text(
            label,
            style = MaterialTheme.typography.labelLarge,
            color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
        )
        content()
        if (!supportingText.isNullOrBlank()) {
            Text(
                supportingText,
                style = MaterialTheme.typography.bodySmall,
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = Space.xxs),
            )
        }
    }
}

@Composable
private fun switchColors() = SwitchDefaults.colors(
    checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
    checkedTrackColor = MaterialTheme.colorScheme.primary,
    uncheckedThumbColor = MaterialTheme.colorScheme.outline,
    uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
    disabledCheckedThumbColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = DisabledAlpha.content),
    disabledCheckedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = DisabledAlpha.container),
    disabledUncheckedThumbColor = MaterialTheme.colorScheme.outline.copy(alpha = DisabledAlpha.content),
    disabledUncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = DisabledAlpha.container),
)
