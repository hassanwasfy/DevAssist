package com.abaferas.devassist.ui.screen.home

import com.abaferas.devassist.data.model.LearningItem
import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.ErrorUiState


data class HomeUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val userName: String = "",
    val searchValue: String = "",
    val items: List<LearningItem> = emptyList(),
) : BaseUiState