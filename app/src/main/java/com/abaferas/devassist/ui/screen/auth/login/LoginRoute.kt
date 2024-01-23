package com.abaferas.devassist.ui.screen.auth.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addLoginRoute() {
    composable(
        route = NavigationDestination.ScreenLogin.route,
    ) {
        ScreenLogin()
    }
}

fun NavController.navigateToLogin() {
    navigate(NavigationDestination.ScreenLogin.route)
}