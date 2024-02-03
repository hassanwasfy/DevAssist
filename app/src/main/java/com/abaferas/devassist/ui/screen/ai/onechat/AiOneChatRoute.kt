package com.abaferas.devassist.ui.screen.ai.onechat


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination


fun NavGraphBuilder.addAiOneChatRoute() {
    composable(
        route = NavigationDestination.ScreenAiOneChat.route,
    ) {
        ScreenAiOneChat()
    }
}

fun NavController.navigateToAiOneChat(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenAiOneChat.route) { builder() }
}