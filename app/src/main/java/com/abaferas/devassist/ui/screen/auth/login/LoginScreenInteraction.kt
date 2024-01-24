package com.abaferas.devassist.ui.screen.auth.login

interface LoginScreenInteraction {
    fun onClickBack()
    fun onEmailValueChange(value: String)
    fun onPasswordValueChange(value: String)
    fun onClickLogin()
    fun onRetry()
    fun onTogglePassword()
}