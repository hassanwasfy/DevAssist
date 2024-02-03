package com.abaferas.devassist.ui.navigation

sealed class NavigationDestination(val route: String) {
    data object ScreenSplash : NavigationDestination("ScreenSplash")
    data object ScreenHome : NavigationDestination("ScreenHome")
    data object ScreenEditItem : NavigationDestination("ScreenEditItem")
    data object ScreenNewItem : NavigationDestination("ScreenNewItem")
    data object ScreenLogin : NavigationDestination("ScreenLogin")
    data object ScreenSignUp : NavigationDestination("ScreenSignUp")
    data object ScreenSettings : NavigationDestination("ScreenSettings")
    data object ScreenAiChat : NavigationDestination("ScreenAiChat")
    data object ScreenAiOneChat : NavigationDestination("ScreenAiOneChat")
    data object ScreenJob : NavigationDestination("ScreenJob")
    data object ScreenBook : NavigationDestination("ScreenBook")
    data object ScreenSelectType : NavigationDestination("ScreenSelectType")
    data object ScreenBookDetails : NavigationDestination("ScreenBookDetails")

}