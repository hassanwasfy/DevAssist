package com.abaferas.devassist.ui.navigation

sealed class NavigationDestination(val route: String) {
    data object ScreenSplash: NavigationDestination("ScreenSplash")
    data object ScreenSignIn: NavigationDestination("ScreenSignIn")
    data object ScreenSignUp: NavigationDestination("ScreenSignUp")
    data object ScreenHome: NavigationDestination("ScreenHome")

}