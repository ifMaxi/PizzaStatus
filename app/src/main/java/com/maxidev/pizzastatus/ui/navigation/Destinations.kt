package com.maxidev.pizzastatus.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.maxidev.pizzastatus.R

sealed class Destinations(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    data object MainScreen: Destinations(
        route = "main_screen",
        resourceId = R.string.main_screen,
        icon = Icons.Outlined.Home
    )
    data object CodeListScreen: Destinations(
        route = "code_list_screen",
        resourceId = R.string.code_list_screen,
        icon = Icons.AutoMirrored.Outlined.List
    )
}