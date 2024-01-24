package com.abaferas.devassist.ui.screen.auth.signup

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.utils.NetworkStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenSignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val networkStateManager: NetworkStateManager,
) : BaseViewModel<SignUpUiState, SignUpScreenUiEffect>(SignUpUiState()), SignUpScreenInteraction {

    private val args: SignUpScreenArgs = SignUpScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {

    }

    private fun onError(errMsg: String){

    }

    override fun onClickBack() {

    }

    override fun onUserNameValueChange(value: String) {

    }

    override fun onEmailValueChange(value: String) {

    }

    override fun onPasswordValueChange(value: String) {

    }

    override fun onPasswordConfirmationValueChange(value: String) {

    }

    override fun onClickSignUp() {

    }

    override fun onClickLogin() {

    }
}