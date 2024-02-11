package com.codarchy.common.extensions

import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers

fun ImageRequest.Builder.generateImageRequest(imageUrl: String): ImageRequest {
    val placeholder = null
    return this
        .data(imageUrl)
        .listener(listener)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .placeholder(placeholder)
        .error(placeholder)
        .fallback(placeholder)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

}

val listener = object : ImageRequest.Listener {
    override fun onError(request: ImageRequest, result: ErrorResult) {
        super.onError(request, result)
    }

    override fun onSuccess(request: ImageRequest, result: SuccessResult) {
        super.onSuccess(request, result)
    }
}