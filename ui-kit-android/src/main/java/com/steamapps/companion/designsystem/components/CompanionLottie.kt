package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.steamapps.companion.designsystem.R

@Composable
fun CompanionLoadingLottie(
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
) {
    CompanionLottie(
        rawRes = R.raw.lottie_loading,
        modifier = modifier.size(size),
        iterations = LottieConstants.IterateForever,
    )
}

@Composable
fun CompanionSuccessLottie(
    modifier: Modifier = Modifier,
    size: Dp = 88.dp,
) {
    CompanionLottie(
        rawRes = R.raw.lottie_success,
        modifier = modifier.size(size),
        iterations = 1,
    )
}

@Composable
private fun CompanionLottie(
    rawRes: Int,
    modifier: Modifier,
    iterations: Int,
) {
    val composition by rememberLottieComposition(com.airbnb.lottie.compose.LottieCompositionSpec.RawRes(rawRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iterations,
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier,
    )
}
