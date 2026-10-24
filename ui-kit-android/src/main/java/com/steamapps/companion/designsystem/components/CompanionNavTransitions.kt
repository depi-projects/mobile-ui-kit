package com.steamapps.companion.designsystem.components

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import com.steamapps.companion.designsystem.theme.LocalReduceMotion
import com.steamapps.companion.designsystem.theme.MotionTokens

/**
 * NavHost / AnimatedContent transitions. When [LocalReduceMotion] is true, motion is cut.
 * Call these inside composition; they are not static values.
 */
object CompanionNavTransitions {
    @Composable
    fun detailEnter(): EnterTransition = if (LocalReduceMotion.current) {
        EnterTransition.None
    } else {
        fadeIn(spring(stiffness = MotionTokens.springStiffness)) +
            slideInHorizontally(spring(stiffness = MotionTokens.springStiffness)) { it / 4 }
    }

    @Composable
    fun detailExit(): ExitTransition = if (LocalReduceMotion.current) {
        ExitTransition.None
    } else {
        fadeOut(MotionTokens.tweenExit()) +
            slideOutHorizontally(MotionTokens.tweenExit()) { -it / 10 }
    }

    @Composable
    fun detailPopEnter(): EnterTransition = if (LocalReduceMotion.current) {
        EnterTransition.None
    } else {
        fadeIn(MotionTokens.tweenNav()) +
            slideInHorizontally(MotionTokens.tweenNav()) { -it / 5 }
    }

    @Composable
    fun detailPopExit(): ExitTransition = if (LocalReduceMotion.current) {
        ExitTransition.None
    } else {
        fadeOut(MotionTokens.tweenExit()) +
            slideOutHorizontally(MotionTokens.tweenNav()) { it / 4 }
    }

    @Composable
    fun tabEnter(): EnterTransition = if (LocalReduceMotion.current) {
        EnterTransition.None
    } else {
        fadeIn(MotionTokens.tweenMedium()) +
            scaleIn(MotionTokens.tweenNav(), initialScale = 0.96f)
    }

    @Composable
    fun tabExit(): ExitTransition = if (LocalReduceMotion.current) {
        ExitTransition.None
    } else {
        fadeOut(MotionTokens.tweenFast()) +
            scaleOut(MotionTokens.tweenFast(), targetScale = 0.98f)
    }
}
