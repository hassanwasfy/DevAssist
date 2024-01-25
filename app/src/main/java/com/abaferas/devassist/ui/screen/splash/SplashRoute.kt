package com.abaferas.devassist.ui.screen.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addSplashRoute() {
    composable(
        route = NavigationDestination.ScreenSplash.route,
    ) {
        ScreenSplash()
    }
}

fun NavController.navigateToSplash(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenSplash.route) { builder() }
}