package com.abaferas.devassist.ui.screen.books.newbooks

import com.abaferas.devassist.domain.models.Book
import com.abaferas.devassist.domain.models.NewBook
import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.ErrorUiState


data class BookUiState(
    val isLoading: Boolean = true,
    val isInternetConnected: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val items: List<Book> = emptyList()
) : BaseUiState