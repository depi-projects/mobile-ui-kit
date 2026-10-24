package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import coil.compose.SubcomposeAsyncImage

enum class ImageFit {
    Crop,
    Fit,
}

@Composable
fun AsyncImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    fit: ImageFit = ImageFit.Crop,
    shape: Shape = RectangleShape,
    clipToShape: Boolean = true,
    onLoading: () -> Unit = {},
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {},
    placeholder: @Composable () -> Unit = {
        Skeleton(modifier = Modifier.fillMaxSize())
    },
    error: @Composable () -> Unit = {
        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant))
    },
) {
    SubcomposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        contentScale = if (fit == ImageFit.Crop) ContentScale.Crop else ContentScale.Fit,
        modifier = if (clipToShape) modifier.clip(shape) else modifier,
        loading = { placeholder() },
        error = { error() },
        onLoading = { _ -> onLoading() },
        onSuccess = { _ -> onSuccess() },
        onError = { _ -> onError() },
    )
}
