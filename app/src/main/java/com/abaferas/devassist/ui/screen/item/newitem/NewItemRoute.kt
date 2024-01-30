package com.abaferas.devassist.ui.screen.item.newitem

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abaferas.devassist.ui.navigation.NavigationDestination
import com.abaferas.devassist.ui.screen.item.edititem.EditItemScreenArgs

fun NavGraphBuilder.addNewItemRoute() {
    composable(
        route = "${NavigationDestination.ScreenNewItem.route}/{${NewItemScreenArgs.TYPE_NAME}}",
        arguments = listOf(
            navArgument(NewItemScreenArgs.TYPE_NAME){
                type = NavType.StringType
            }
        )
    ) {
        ScreenNewItem()
    }
}

fun NavController.navigateToNewItem(typeName: String) {
    Log.e("XCV","in Route: $typeName")
    navigate("${NavigationDestination.ScreenNewItem.route}/$typeName")
}