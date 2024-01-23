package com.abaferas.devassist.ui.screen.item

import com.abaferas.devassist.data.LearningItem
import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.ErrorUiState


data class ViewItemUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val itemId: Int = -1,
    val userId: Int = 0,
    val name: String = "",
    val type: LearningItem.LearningType = LearningItem.LearningType.BOOK,
    val author: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val notes: MutableList<String> = mutableListOf(),
    val notesCount: Int = 0,
    val totalAmount: Int = 0,
    val finishedAmount: Int = 0,
    val progress: Float = 0f,
) : BaseUiState