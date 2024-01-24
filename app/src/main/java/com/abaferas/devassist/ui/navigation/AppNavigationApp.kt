
package com.abaferas.devassist.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


val LocalNavController =
    compositionLocalOf<NavHostController> { error("No NavController found!") }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigationApp() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        Scaffold {
            rememberSystemUiController().setStatusBarColor(color_darkPrimaryColor)
            AppNavigationNavGraph()
        }
    }
}