package com.abaferas.devassist.ui.screen.splash

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.data.repository.IRepository
import com.abaferas.devassist.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenSplashViewModel @Inject constructor(
    private val repository: IRepository
) : BaseViewModel<SplashUiState, SplashScreenUiEffect>(SplashUiState()), SplashScreenInteraction {


    init {
        getData()
    }

    override fun getData() {
        iState.update {
            it.copy(
                isLoading = false
            )
        }

    }

    override fun onError(errorMsg: String) {

    }

    override fun onClickStart() {

    }
}