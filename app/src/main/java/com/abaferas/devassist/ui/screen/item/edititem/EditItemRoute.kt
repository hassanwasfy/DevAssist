package com.abaferas.devassist.ui.screen.item.edititem


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abaferas.devassist.ui.navigation.NavigationDestination
import com.abaferas.devassist.ui.screen.item.newitem.NewItemScreenArgs

fun NavGraphBuilder.addEditItemRoute() {
    composable(
        route = "${NavigationDestination.ScreenEditItem.route}/{${EditItemScreenArgs.ITEM_ID}}",
        arguments = listOf(
            navArgument(EditItemScreenArgs.ITEM_ID){
                type = NavType.StringType
            }
        )
    ) {
        ScreenEditItem()
    }
}

fun NavController.navigateToEditItem(itemId: String) {
    navigate("${NavigationDestination.ScreenEditItem.route}/{$itemId}")
}