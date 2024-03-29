package com.abaferas.devassist.ui.screen.auth.login

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.domain.usecase.auth.LogInUserUseCase
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState
import com.abaferas.devassist.ui.utils.NetworkStateManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenLoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val networkStateManager: NetworkStateManager,
    private val logInUserUseCase: LogInUserUseCase
) : BaseViewModel<LoginUiState, LoginScreenUiEffect>(LoginUiState()), LoginScreenInteraction {

    private val args: LoginScreenArgs = LoginScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        if (networkStateManager.isInternetAvailable()) {
            iState.update {
                it.copy(
                    isInternetConnected = true,
                    isLoading = false,
                )
            }
        } else {
            iState.update {
                it.copy(
                    isLoading = false,
                    isInternetConnected = false,
                )
            }
        }
    }

    override fun onClickBack() {
        sendUiEffect(LoginScreenUiEffect.NavigateUp)
    }

    override fun onEmailValueChange(value: String) {
        if (
            Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$").matches(value) ||
            value.isEmpty()
        ) {
            iState.update {
                it.copy(
                    userEmailValue = EntryTextValue(
                        value = value,
                        error = ErrorUiState()
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    userEmailValue = EntryTextValue(
                        value = value,
                        error = ErrorUiState(isError = true, message = "invalid format!")
                    )
                )
            }
        }
    }

    override fun onPasswordValueChange(value: String) {
        if (value.length >= 8 || value.isEmpty()) {
            iState.update {
                it.copy(
                    passwordValue = EntryTextValue(
                        value = value,
                        error = ErrorUiState()
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    passwordValue = EntryTextValue(
                        value = value,
                        error = ErrorUiState(isError = true, message = "too short!")
                    )
                )
            }
        }
    }

    override fun onClickLogin() {
        if (
            state.value.userEmailValue.value.isEmpty() || state.value.userEmailValue.error.isError
        ) {
            iState.update {
                it.copy(
                    userEmailValue = EntryTextValue(
                        value = it.userEmailValue.value,
                        error = ErrorUiState(true, "empty or inValid Email!")
                    )
                )
            }
        } else if (
            state.value.passwordValue.value.isEmpty() || state.value.passwordValue.error.isError
        ) {
            iState.update {
                it.copy(
                    passwordValue = EntryTextValue(
                        value = it.passwordValue.value,
                        error = ErrorUiState(true, "empty or inValid Password!")
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    isLoading = true
                )
            }
            val email = state.value.userEmailValue.value
            val password = state.value.passwordValue.value
            tryToExecute(
                onSuccess = ::onSuccess,
                onError = ::onError,
                execute = {logInUserUseCase(email, password)}
            )
        }
    }

    override fun onRetry() {
        iState.update {
            it.copy(
                isLoading = true,
                isInternetConnected = true,
                isPasswordVisible = false,
                error = ErrorUiState()
            )
        }
        getData()
    }

    override fun onTogglePassword() {
        iState.update {
            it.copy(
                isPasswordVisible = !it.isPasswordVisible
            )
        }
    }

    override fun onError(errorMsg: String) {
        iState.update {
            it.copy(
                isLoading = false,
                error = ErrorUiState(isError = true, message = errorMsg)
            )
        }
    }

    private fun onSuccess(result: Task<AuthResult?>) {
        result.addOnSuccessListener { _ ->
            sendUiEffect(LoginScreenUiEffect.Login)
        }.addOnFailureListener { e ->
            iState.update {
                it.copy(
                    error = ErrorUiState(true, e.message.toString())
                )
            }
        }
    }
}