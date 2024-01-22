package com.abaferas.devassist.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addHomeRoute() {
    composable(
        route = NavigationDestination.ScreenHome.route,
    ) {
        ScreenHome()
    }
}

fun NavController.navigateToHome() {
    navigate(NavigationDestination.ScreenHome.route)
}