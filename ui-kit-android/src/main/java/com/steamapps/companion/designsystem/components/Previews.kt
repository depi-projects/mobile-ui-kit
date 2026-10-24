package com.steamapps.companion.designsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.steamapps.companion.designsystem.theme.CompanionTheme

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ButtonVariantsPreview() {
    CompanionTheme {
        Column {
            Button(onClick = {}) { Text("Primary") }
            Button(onClick = {}, variant = ButtonVariant.Secondary) { Text("Secondary") }
            Button(onClick = {}, variant = ButtonVariant.Tertiary) { Text("Tertiary") }
            Button(onClick = {}, variant = ButtonVariant.Destructive) { Text("Destructive") }
            Button(onClick = {}, enabled = false) { Text("Disabled") }
            Button(onClick = {}, loading = true) { Text("Loading") }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    CompanionTheme {
        TextField(value = "", onValueChange = {}, label = "Email", placeholder = "name@mail.com", error = "Required")
    }
}
