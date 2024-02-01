package com.abaferas.devassist.ui.screen.profile


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination


fun NavGraphBuilder.addJobRoute() {
    composable(
        route = NavigationDestination.ScreenJob.route,
    ) {
        ScreenJob()
    }
}

fun NavController.navigateToJob(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenJob.route) { builder() }
}