package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Shapes

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    error: String? = null,
    isError: Boolean = error != null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    clearable: Boolean = false,
    maxLength: Int? = null,
    onClear: () -> Unit = {},
    onFocusChanged: (Boolean) -> Unit = {},
    onSearch: (() -> Unit)? = null,
    onDone: (() -> Unit)? = null,
) {
    val resolvedIme = when {
        imeAction != ImeAction.Default -> imeAction
        onSearch != null -> ImeAction.Search
        onDone != null -> ImeAction.Done
        else -> ImeAction.Default
    }
    val clear: @Composable (() -> Unit)? = if (clearable && value.isNotEmpty() && enabled && !readOnly) {
        {
            IconButton(onClick = { onValueChange(""); onClear() }, modifier = Modifier.size(40.dp)) {
                Icon(Icons.Default.Close, contentDescription = "Clear")
            }
        }
    } else {
        null
    }
    OutlinedTextField(
        value = value,
        onValueChange = { next ->
            if (maxLength == null || next.length <= maxLength) onValueChange(next)
        },
        modifier = modifier.fillMaxWidth().onFocusChanged { onFocusChanged(it.isFocused) },
        enabled = enabled,
        readOnly = readOnly,
        label = label?.let { { Text(it) } },
        placeholder = placeholder?.let { { Text(it) } },
        supportingText = (error ?: supportingText)?.let { { Text(it) } },
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon ?: clear,
        singleLine = singleLine,
        maxLines = maxLines,
        shape = Shapes.small,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = resolvedIme),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch?.invoke() },
            onDone = { onDone?.invoke() },
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorCursorColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
    )
}
