package com.abaferas.devassist.ui.screen.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addSettingsRoute() {
    composable(
        route = NavigationDestination.ScreenSettings.route,
    ) {
        ScreenSettings()
    }
}

fun NavController.navigateToSettings(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenSettings.route) { builder() }
}