package com.abaferas.devassist.ui.screen.auth.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.abaferas.devassist.data.repository.IRepository
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState
import com.abaferas.devassist.ui.utils.NetworkStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenSignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val networkStateManager: NetworkStateManager,
    private val repository: IRepository
) : BaseViewModel<SignUpUiState, SignUpScreenUiEffect>(SignUpUiState()), SignUpScreenInteraction {

    private val args: SignUpScreenArgs = SignUpScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        if (networkStateManager.isInternetAvailable()) {
            iState.update {
                it.copy(
                    isInternetConnected = true,
                    isLoading = false,
                    isRetrying = false
                )
            }
        } else {
            iState.update {
                it.copy(
                    isLoading = false,
                    isInternetConnected = false,
                    isRetrying = false
                )
            }
        }
    }

    private fun onSuccess() {

    }

    private fun onError(errMsg: String) {
        iState.update {
            it.copy(
                error = ErrorUiState(isError = true, message = errMsg)
            )
        }
    }

    override fun onClickBack() {

    }

    override fun onUserNameValueChange(value: String) {
        if (value.length >= 6 || value.isEmpty()) {
            iState.update {
                it.copy(
                    userNameValue = EntryTextValue(
                        value = value,
                        error = ErrorUiState()
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    userNameValue = EntryTextValue(
                        value = value,
                        error = ErrorUiState(isError = true, message = "too short!")
                    )
                )
            }
        }
    }

    override fun onEmailValueChange(value: String) {
        if (Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$").matches(value) || value.isEmpty()) {
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

    override fun onPasswordConfirmationValueChange(value: String) {
        if (iState.value.passwordValue.value == value || value.isEmpty()) {
            iState.update {
                it.copy(
                    passwordConfirmation = EntryTextValue(
                        value = value,
                        error = ErrorUiState()
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    passwordConfirmation = EntryTextValue(
                        value = value,
                        error = ErrorUiState(isError = true, message = "doesn't match!")
                    )
                )
            }
        }
    }

    override fun onClickSignUp() {

    }

    override fun onClickLogin() {

    }

    override fun onRetry() {
        iState.update {
            it.copy(
                isRetrying = true
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
}