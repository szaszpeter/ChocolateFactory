package com.codarchy.presentations_landing.extensions

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import com.codarchy.presentations_search.R

fun NavController.navigateToDetails() {
    val navOptions =
        NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
    val request = NavDeepLinkRequest.Builder
        .fromUri("android-app://com.codarchy.presentationdetail.details".toUri())
        .build()
    this.navigate(request, navOptions)
}