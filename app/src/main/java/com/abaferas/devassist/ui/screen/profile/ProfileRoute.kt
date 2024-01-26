package com.abaferas.devassist.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addProfileRoute() {
    composable(
        route = NavigationDestination.ScreenProfile.route,
    ) {
        ScreenProfile()
    }
}

fun NavController.navigateToProfile(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenProfile.route) { builder() }
}