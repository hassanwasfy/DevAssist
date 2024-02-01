package com.abaferas.devassist.ui.screen.books.bookdetails


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination


fun NavGraphBuilder.addBookDetailsRoute() {
    composable(
        route = NavigationDestination.ScreenBookDetails.route,
    ) {
        ScreenBookDetails()
    }
}

fun NavController.navigateToBookDetails(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenBookDetails.route) { builder() }
}