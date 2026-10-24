package com.steamapps.companion.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/** Shared spacing. Screens use [screen] as the page inset. */
object Space {
    val xxs = 4.dp
    val xs = 8.dp
    val sm = 12.dp
    val md = 16.dp
    val lg = 20.dp
    val xl = 24.dp
    val xxl = 32.dp
    val screen = 20.dp
    val button = 56.dp
}

/** One radius scale for panels, buttons, and the nav dock. */
object Radius {
    val sm = 12.dp
    val md = 16.dp
    val lg = 20.dp
    val xl = 28.dp
}

val Shapes = androidx.compose.material3.Shapes(
    extraSmall = RoundedCornerShape(Radius.sm),
    small = RoundedCornerShape(Radius.sm),
    medium = RoundedCornerShape(Radius.md),
    large = RoundedCornerShape(Radius.lg),
    extraLarge = RoundedCornerShape(Radius.xl),
)

val PanelShape = RoundedCornerShape(Radius.lg)
val ButtonShape = RoundedCornerShape(Radius.md)

/** Shared shadow scale. Components pick a step; they do not invent a new dp. */
object Elevation {
    val none = 0.dp
    val xs = 2.dp
    val sm = 4.dp
    val md = 6.dp
    val lg = 8.dp
    val xl = 12.dp
}

/** Same disabled treatment for every interactive variant. */
object DisabledAlpha {
    const val container = 0.12f
    const val content = 0.38f
}
