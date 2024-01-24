package com.abaferas.devassist.ui.screen.auth.signup

import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState


data class SignUpUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val isInternetConnected: Boolean = true,
    val userNameValue: EntryTextValue = EntryTextValue(),
    val userEmailValue: EntryTextValue = EntryTextValue(),
    val passwordValue: EntryTextValue = EntryTextValue(),
    val passwordConfirmation: EntryTextValue = EntryTextValue(),
): BaseUiState