package com.abaferas.devassist.ui.screen.books.newbooks

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.domain.models.Book
import com.abaferas.devassist.domain.usecase.books.GetNewBooksUseCase
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenBookViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getNewBooksUseCase: GetNewBooksUseCase
) : BaseViewModel<BookUiState, BookScreenUiEffect>(BookUiState()), BookScreenInteraction {

    private val args: BookScreenArgs = BookScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        tryToExecute(
            onError = { errMsg ->
                iState.update {
                    it.copy(
                        isLoading = false,
                        error = ErrorUiState(true, errMsg)
                    )
                }
            },
            onSuccess = { books ->
                iState.update {
                    it.copy(
                        isLoading = false,
                        items = books,
                        error = ErrorUiState()
                    )
                }
            },
            execute = {
                getNewBooksUseCase()
            }
        )
    }

    override fun onClickBack() {

    }

    override fun onError(errorMsg: String) {

    }

    override fun onClickRetry() {

    }
}