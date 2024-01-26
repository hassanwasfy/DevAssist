package com.abaferas.devassist.ui.screen.books

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addBookRoute() {
    composable(
        route = NavigationDestination.ScreenBook.route,
    ) {
        ScreenBook()
    }
}

fun NavController.navigateToBook(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenBook.route) { builder() }
}