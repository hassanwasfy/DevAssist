package com.abaferas.devassist.ui.screen.profile


import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.ErrorUiState

data class JobUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState()
) : BaseUiState