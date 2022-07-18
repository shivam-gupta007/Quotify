package com.app.quotify.feature_quote.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Rounded.Home
    )

    object Favourite: BottomBarScreen(
        route = "favorite",
        title = "Favorite",
        icon = Icons.Default.Favorite
    )
}

