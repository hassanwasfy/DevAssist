package com.abaferas.devassist.ui.screen.splash

import androidx.lifecycle.viewModelScope
import com.abaferas.devassist.data.repository.AuthRepository
import com.abaferas.devassist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenSplashViewModel @Inject constructor(
    private val repository: AuthRepository
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
        viewModelScope.launch(Dispatchers.Main) {
            for (i in 0 until 4){
                delay(200)
                iState.update {
                    it.copy(
                        progress = it.progress + 0.25f
                    )
                }
            }
            delay(200)
            if (repository.isUserLoggedIn() == null){
                sendUiEffect(SplashScreenUiEffect.SignUp)
            }else{
                sendUiEffect(SplashScreenUiEffect.Home)
            }
        }

    }

    override fun onError(errorMsg: String) {

    }

    override fun onClickStart() {

    }
}