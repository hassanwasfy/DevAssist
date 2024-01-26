package com.abaferas.devassist.ui.navigation

sealed class NavigationDestination(val route: String) {
    data object ScreenSplash: NavigationDestination("ScreenSplash")
    data object ScreenHome: NavigationDestination("ScreenHome")
    data object ScreenEditItem: NavigationDestination("ScreenEditItem")
    data object ScreenNewItem: NavigationDestination("ScreenNewItem")
    data object ScreenLogin: NavigationDestination("ScreenLogin")
    data object ScreenSignUp: NavigationDestination("ScreenSignUp")
    data object ScreenSettings: NavigationDestination("ScreenSettings")
    data object ScreenAiChat: NavigationDestination("ScreenAiChat")
    data object ScreenProfile: NavigationDestination("ScreenProfile")
    data object ScreenBook: NavigationDestination("ScreenBook")



}