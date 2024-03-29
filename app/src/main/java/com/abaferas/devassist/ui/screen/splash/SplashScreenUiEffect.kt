package com.abaferas.devassist.ui.screen.splash

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class SplashScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : SplashScreenUiEffect()
    data object Home : SplashScreenUiEffect()
    data object SignUp : SplashScreenUiEffect()
}