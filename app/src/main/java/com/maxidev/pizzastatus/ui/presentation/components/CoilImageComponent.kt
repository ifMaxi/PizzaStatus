package com.maxidev.pizzastatus.ui.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers

@Composable
fun CoilImageComponent(
    image: String
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(image)
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .fetcherDispatcher(Dispatchers.IO)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = null
    )
}