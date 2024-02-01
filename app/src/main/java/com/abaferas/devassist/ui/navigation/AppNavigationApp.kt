package com.abaferas.devassist.ui.navigation

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.composable.DevBottomBar
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.screen.home.navigateToHome
import com.abaferas.devassist.ui.screen.settings.navigateToSettings
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_primaryColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


val LocalNavController =
    compositionLocalOf<NavHostController> { error("No NavController found!") }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigationApp() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        Scaffold(
            modifier = Modifier.background(color_lightPrimaryColor),
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                ) {
                    DevBottomBar()
                }
            }
        ) {
            val sys = rememberSystemUiController()
            sys.setStatusBarColor(color_darkPrimaryColor)
            sys.setNavigationBarColor(color_darkPrimaryColor, darkIcons = false)
            AppNavigationNavGraph()
        }
    }
}