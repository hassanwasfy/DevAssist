package com.abaferas.devassist.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.abaferas.devassist.ui.screen.auth.login.addLoginRoute
import com.abaferas.devassist.ui.screen.auth.signup.addSignUpRoute
import com.abaferas.devassist.ui.screen.home.addHomeRoute
import com.abaferas.devassist.ui.screen.item.addEditItemRoute
import com.abaferas.devassist.ui.screen.item.addNewItemRoute


@Composable
fun AppNavigationNavGraph() {
    NavHost(
        navController = LocalNavController.current,
        startDestination = NavigationDestination.ScreenSignUp.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        addSignUpRoute()
        addLoginRoute()
        addHomeRoute()
        addNewItemRoute()
        addEditItemRoute()
    }
}