package com.abaferas.devassist.ui.screen.auth.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addSignUpRoute() {
    composable(
        route = NavigationDestination.ScreenSignUp.route,
    ) {
        ScreenSignUp()
    }
}

fun NavController.navigateToSignUp() {
    navigate(NavigationDestination.ScreenSignUp.route)
}