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
import com.abaferas.devassist.ui.screen.item.addEditItemRoute
import com.abaferas.devassist.ui.screen.item.addNewItemRoute
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
                animationSpec = tween(300, delayMillis = 200, easing = EaseIn),
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
        addNewItemRoute()
        addEditItemRoute()
        addSettingsRoute()
        addAiChatRoute()
        addProfileRoute()
        addBookRoute()

    }
}