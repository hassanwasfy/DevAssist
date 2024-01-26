package com.abaferas.devassist.ui.screen.item

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addEditItemRoute() {
    composable(
        route = "${NavigationDestination.ScreenEditItem.route}/{${ViewItemScreenArgs.ITEM_ID}}",
        arguments = listOf(
            navArgument(ViewItemScreenArgs.ITEM_ID){
                type = NavType.StringType
            }
        )
    ) {
        ScreenViewItem()
    }
}

fun NavController.navigateToEditItem(itemId: String) {
    navigate("${NavigationDestination.ScreenEditItem.route}/{$itemId}")
}

fun NavGraphBuilder.addNewItemRoute() {
    composable(
        route = NavigationDestination.ScreenNewItem.route,
    ) {
        ScreenViewItem()
    }
}

fun NavController.navigateToNewItem() {
    navigate(NavigationDestination.ScreenNewItem.route)
}