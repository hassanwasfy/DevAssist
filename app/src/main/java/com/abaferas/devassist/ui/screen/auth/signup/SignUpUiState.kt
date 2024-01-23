package com.abaferas.devassist.ui.screen.auth.signup

import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.ErrorUiState


data class SignUpUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState()
) : BaseUiState