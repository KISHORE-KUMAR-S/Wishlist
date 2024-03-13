package com.android.wishlist.navigation

sealed class Screen(val route : String) {
    data object HomeScreen : Screen("home_screen")
    data object AddScreen : Screen("add_screen")
}