package com.abaferas.devassist.ui.screen.auth.login

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class LoginScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : LoginScreenUiEffect()
    data object Login : LoginScreenUiEffect()
}