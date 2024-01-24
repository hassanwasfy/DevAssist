package com.abaferas.devassist.ui.screen.auth.login

import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState


data class LoginUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val isInternetConnected: Boolean = true,
    val isRetrying: Boolean = true,
    val isPasswordVisible: Boolean = false,
    val userEmailValue: EntryTextValue = EntryTextValue(),
    val passwordValue: EntryTextValue = EntryTextValue(),
) : BaseUiState