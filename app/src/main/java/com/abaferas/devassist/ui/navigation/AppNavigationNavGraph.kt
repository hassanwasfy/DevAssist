package com.abaferas.devassist.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.abaferas.devassist.ui.screen.ai.addAiChatRoute
import com.abaferas.devassist.ui.screen.auth.login.addLoginRoute
import com.abaferas.devassist.ui.screen.auth.signup.addSignUpRoute
import com.abaferas.devassist.ui.screen.books.addBookRoute
import com.abaferas.devassist.ui.screen.home.addHomeRoute
import com.abaferas.devassist.ui.screen.item.edititem.addEditItemRoute
import com.abaferas.devassist.ui.screen.item.selecttype.addSelectTypeRoute
import com.abaferas.devassist.ui.screen.item.newitem.addNewItemRoute
import com.abaferas.devassist.ui.screen.profile.addProfileRoute
import com.abaferas.devassist.ui.screen.settings.addSettingsRoute
import com.abaferas.devassist.ui.screen.splash.addSplashRoute


@Composable
fun AppNavigationNavGraph() {
    NavHost(
        navController = LocalNavController.current,
        startDestination = NavigationDestination.ScreenSplash.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(300),
                ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(300),
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = SlideDirection.End
            )
        },
    ) {
        addSplashRoute()
        addSignUpRoute()
        addLoginRoute()
        addHomeRoute()
        addSettingsRoute()
        addAiChatRoute()
        addProfileRoute()
        addBookRoute()
        addNewItemRoute()
        addEditItemRoute()
        addSelectTypeRoute()
    }
}