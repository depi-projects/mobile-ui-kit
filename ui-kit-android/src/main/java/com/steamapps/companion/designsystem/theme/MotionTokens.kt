package com.steamapps.companion.designsystem.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.staticCompositionLocalOf

/** True when the system animator duration scale is 0. Transitions should skip motion. */
val LocalReduceMotion = staticCompositionLocalOf { false }

/**
 * Shared motion scale. Screens and nav transitions should use these durations
 * instead of one-off millisecond literals.
 */
object MotionTokens {
    const val durationFast = 160
    const val durationMedium = 280
    const val durationNav = 340
    const val durationExit = 220

    val easing = FastOutSlowInEasing

    const val springStiffness = Spring.StiffnessMediumLow

    fun <T> tweenFast() = tween<T>(durationFast, easing = easing)
    fun <T> tweenMedium() = tween<T>(durationMedium, easing = easing)
    fun <T> tweenNav() = tween<T>(durationNav, easing = easing)
    fun <T> tweenExit() = tween<T>(durationExit, easing = easing)
}
