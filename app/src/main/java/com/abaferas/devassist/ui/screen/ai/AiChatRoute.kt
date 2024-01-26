package com.abaferas.devassist.ui.screen.ai

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.abaferas.devassist.ui.navigation.NavigationDestination

fun NavGraphBuilder.addAiChatRoute() {
    composable(
        route = NavigationDestination.ScreenAiChat.route,
    ) {
        ScreenAiChat()
    }
}

fun NavController.navigateToAiChat(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(NavigationDestination.ScreenAiChat.route) { builder() }
}