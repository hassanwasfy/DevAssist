package com.abaferas.devassist.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState, UiEffect>(state: UiState) : ViewModel() {

    protected val iState = MutableStateFlow(state)
    val state = iState.asStateFlow()

    private val iEffect = MutableSharedFlow<UiEffect>()
    val effect = iEffect.asSharedFlow()

    protected abstract fun getData()
    protected abstract fun onError(errorMsg: String)

    fun <T> tryToExecute(
        onSuccess: (T) -> Unit = {},
        onError: (errorMsg: String) -> Unit = ::onError,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        execute: suspend () -> T,
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = execute()
                onSuccess(result)
            } catch (e: Exception) {
                onError(e.message.toString())
            }
        }
    }

    protected fun sendUiEffect(uiEffect: UiEffect) {
        viewModelScope.launch(Dispatchers.IO) {
            iEffect.emit(uiEffect)
        }
    }
}