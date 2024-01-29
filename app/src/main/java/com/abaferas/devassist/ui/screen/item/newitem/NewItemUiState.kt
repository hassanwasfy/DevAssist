package com.abaferas.devassist.ui.screen.item.newitem

import com.abaferas.devassist.data.model.LearningType
import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState


data class NewItemUiState(
    val isLoading: Boolean = false,
    val selectingStartDate: Boolean = false,
    val selectingEndDate: Boolean = false,
    val error: ErrorUiState = ErrorUiState(),
    val isBook: Boolean = true,
    val itemId: String = "",
    val userId: String = "",
    val name: EntryTextValue = EntryTextValue(),
    val type: LearningType = LearningType.BOOK,
    val author: EntryTextValue = EntryTextValue(),
    val startDate: EntryTextValue = EntryTextValue(),
    val endDate: EntryTextValue = EntryTextValue(),
    val notes: MutableList<String> = mutableListOf(),
    val notesCount: EntryTextValue = EntryTextValue(),
    val totalAmount: EntryTextValue = EntryTextValue(),
    val finishedAmount: EntryTextValue = EntryTextValue(),
    val progress: EntryTextValue = EntryTextValue(),
) : BaseUiState