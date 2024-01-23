package com.abaferas.devassist.ui.screen.auth.signup

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenSignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<SignUpUiState, SignUpScreenUiEffect>(SignUpUiState()), SignUpScreenInteraction {

    private val args: SignUpScreenArgs = SignUpScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        TODO("Not yet implemented")
    }

    override fun onClickBack() {
        TODO("Not yet implemented")
    }
}