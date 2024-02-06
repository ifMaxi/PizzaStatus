package com.maxidev.pizzastatus.ui.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(500.dp)
            .clip(RoundedCornerShape(5))
    )
}