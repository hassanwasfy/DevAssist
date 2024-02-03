package com.abaferas.devassist.ui.navigation

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.abaferas.devassist.ui.screen.ai.chatslist.addAiChatRoute
import com.abaferas.devassist.ui.screen.auth.login.addLoginRoute
import com.abaferas.devassist.ui.screen.auth.signup.addSignUpRoute
import com.abaferas.devassist.ui.screen.books.bookdetails.addBookDetailsRoute
import com.abaferas.devassist.ui.screen.books.newbooks.addBookRoute
import com.abaferas.devassist.ui.screen.home.addHomeRoute
import com.abaferas.devassist.ui.screen.item.edititem.addEditItemRoute
import com.abaferas.devassist.ui.screen.item.newitem.addNewItemRoute
import com.abaferas.devassist.ui.screen.item.selecttype.addSelectTypeRoute
import com.abaferas.devassist.ui.screen.profile.addJobRoute
import com.abaferas.devassist.ui.screen.settings.addSettingsRoute
import com.abaferas.devassist.ui.screen.splash.addSplashRoute
import com.abaferas.devassist.Constants
import com.abaferas.devassist.ui.screen.ai.onechat.addAiOneChatRoute

@Composable
fun AppNavigationNavGraph() {
    NavHost(
        navController = LocalNavController.current,
        startDestination = NavigationDestination.ScreenSplash.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(Constants.ANIMATION_DURATION, easing = EaseIn),
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(Constants.ANIMATION_DURATION, easing = EaseOut),
            )
        },
    ) {
        addSplashRoute()
        addSignUpRoute()
        addLoginRoute()
        addHomeRoute()
        addSettingsRoute()
        addAiChatRoute()
        addAiOneChatRoute()
        addJobRoute()
        addBookRoute()
        addNewItemRoute()
        addEditItemRoute()
        addSelectTypeRoute()
        addBookDetailsRoute()
    }
}