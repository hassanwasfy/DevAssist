package com.abaferas.devassist.ui.screen.item

import com.abaferas.devassist.data.model.LearningItem
import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState


data class ViewItemUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val isBook: Boolean = true,
    val itemId: String = "",
    val userId: String = "",
    val name: EntryTextValue = EntryTextValue(),
    val type: LearningItem.LearningType = LearningItem.LearningType.BOOK,
    val author: EntryTextValue = EntryTextValue(),
    val startDate: EntryTextValue = EntryTextValue(),
    val endDate: EntryTextValue = EntryTextValue(),
    val notes: MutableList<String> = mutableListOf(),
    val notesCount: EntryTextValue = EntryTextValue(),
    val totalAmount: EntryTextValue = EntryTextValue(),
    val finishedAmount: EntryTextValue = EntryTextValue(),
    val progress: EntryTextValue = EntryTextValue(),
) : BaseUiState