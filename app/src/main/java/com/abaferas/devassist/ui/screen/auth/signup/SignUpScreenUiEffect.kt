package com.abaferas.devassist.ui.screen.auth.signup

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class SignUpScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : SignUpScreenUiEffect()
    data object NavigateToHome : SignUpScreenUiEffect()
    data object NavigateToLogin : SignUpScreenUiEffect()
}