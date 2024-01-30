package com.abaferas.devassist.ui.screen.auth.signup

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.domain.models.User
import com.abaferas.devassist.domain.usecase.auth.CreateAnonymousUserUseCase
import com.abaferas.devassist.domain.usecase.auth.CreateNewUserUseCase
import com.abaferas.devassist.domain.usecase.auth.GetUserIdUseCase
import com.abaferas.devassist.domain.usecase.auth.InsertNewUserUseCase
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState
import com.abaferas.devassist.ui.utils.NetworkStateManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenSignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val networkStateManager: NetworkStateManager,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val insertNewUserUseCase: InsertNewUserUseCase,
    private val createNewUserUseCase: CreateNewUserUseCase,
    private val createAnonymousUserUseCase: CreateAnonymousUserUseCase,
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

    override fun onClickBack() {
        sendUiEffect(SignUpScreenUiEffect.NavigateUp)
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
        if (
            state.value.userNameValue.value.isEmpty() || state.value.userNameValue.error.isError
        ) {
            iState.update {
                it.copy(
                    userNameValue = EntryTextValue(
                        value = it.userNameValue.value,
                        error = ErrorUiState(true, "inValid Name!")
                    )
                )
            }
        } else if (
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
        } else if (
            state.value.passwordConfirmation.value.isEmpty() || state.value.passwordConfirmation.error.isError
        ) {
            iState.update {
                it.copy(
                    passwordConfirmation = EntryTextValue(
                        value = it.passwordConfirmation.value,
                        error = ErrorUiState(true, "empty or doesn't match Password!")
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
                execute = { createNewUserUseCase(email, password) }
            )
        }
    }

    private fun onSuccess(result: Task<AuthResult?>) {
        result.addOnSuccessListener { _ ->
            tryToExecute(
                onSuccess = ::onSaveUserSuccess,
                onError = ::onSaveUserError,
                execute = {
                    insertNewUserUseCase(
                        newUser = User(
                            getUserIdUseCase(),
                            iState.value.userNameValue.value,
                            iState.value.userEmailValue.value
                        )
                    )
                }
            )
            sendUiEffect(SignUpScreenUiEffect.NavigateToHome)
        }.addOnFailureListener { e ->
            iState.update {
                it.copy(
                    error = ErrorUiState(true, e.message.toString())
                )
            }
        }
    }

    private fun onSaveUserSuccess(result: Task<DocumentReference?>) {
        result.addOnSuccessListener {
            sendUiEffect(SignUpScreenUiEffect.NavigateUp)
        }
    }

    private fun onSaveUserError(errMsg: String) {
        iState.update {
            it.copy(
                error = ErrorUiState(isError = true, message = errMsg)
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

    override fun onClickSignInAnonymously() {
        if (
            state.value.userNameValue.value.isEmpty() || state.value.userNameValue.error.isError
        ) {
            iState.update {
                it.copy(
                    userNameValue = EntryTextValue(
                        value = it.userNameValue.value,
                        error = ErrorUiState(true, "inValid Name!")
                    )
                )
            }
        } else {
            tryToExecute(
                onSuccess = ::onSuccess,
                onError = ::onError,
                execute = { createAnonymousUserUseCase() }
            )
        }
    }

    override fun onClickLogin() {
        sendUiEffect(SignUpScreenUiEffect.NavigateToLogin)
    }

    override fun onRetry() {
        iState.update {
            SignUpUiState()
        }
    }

    override fun onTogglePassword() {
        iState.update {
            it.copy(
                isPasswordVisible = !it.isPasswordVisible
            )
        }
    }
}