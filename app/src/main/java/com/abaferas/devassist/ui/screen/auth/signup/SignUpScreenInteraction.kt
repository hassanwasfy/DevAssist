package com.abaferas.devassist.ui.screen.auth.signup

interface SignUpScreenInteraction {
    fun onClickBack()
    fun onUserNameValueChange(value: String)
    fun onEmailValueChange(value: String)
    fun onPasswordValueChange(value: String)
    fun onPasswordConfirmationValueChange(value: String)
    fun onClickSignUp()
    fun onClickSignInAnonymously()
    fun onClickLogin()
    fun onRetry()
    fun onTogglePassword()

}