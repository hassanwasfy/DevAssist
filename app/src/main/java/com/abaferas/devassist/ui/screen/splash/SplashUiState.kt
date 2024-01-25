package com.abaferas.devassist.ui.screen.splash

import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.ErrorUiState


data class SplashUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState()
) : BaseUiState