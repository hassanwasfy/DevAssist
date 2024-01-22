package com.abaferas.devassist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost


@Composable
fun AppNavigationNavGraph() {
    NavHost(
        navController = LocalNavController.current,
        startDestination = NavigationDestination.ScreenHome.route
    ) {

    }
}