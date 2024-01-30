package com.abaferas.devassist.ui.screen.item.selecttype

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addSelectTypeRoute() {
    composable(
        route = NavigationDestination.ScreenSelectType.route,
    ) {
        ScreenSelectType()
    }
}

fun NavController.navigateToSelectType(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenSelectType.route) { builder() }
}