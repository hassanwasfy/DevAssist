package com.abaferas.devassist.ui.screen.books.newbooks

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.domain.models.Book
import com.abaferas.devassist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenBookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<BookUiState, BookScreenUiEffect>(BookUiState()), BookScreenInteraction {

    private val args: BookScreenArgs = BookScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        tryToExecute(
            onError = {},
            onSuccess = {},
            execute = {
                delay(3000)
                iState.update {
                    it.copy(
                        isLoading = false,
                        isInternetConnected = true,
                        items = listOf(
                            Book(
                                title = "TitleBook",
                                subtitle = "this is subtitle",
                                isbn13 = "121216782",
                                price = "4453246",
                                image = "sds",
                                url = "sds",
                            ),
                            Book(
                                title = "TitleBook",
                                subtitle = "this is subtitle",
                                isbn13 = "12123412",
                                price = "4453246",
                                image = "sds",
                                url = "sds",
                            ),
                            Book(
                                title = "TitleBook",
                                subtitle = "this is subtitle",
                                isbn13 = "121212",
                                price = "4453246",
                                image = "sds",
                                url = "sds",
                            ),
                            Book(
                                title = "TitleBook",
                                subtitle = "this is subtitle",
                                isbn13 = "1212412",
                                price = "4453246",
                                image = "sds",
                                url = "sds",
                            ),
                            Book(
                                title = "TitleBook",
                                subtitle = "this is subtitle",
                                isbn13 = "1212612",
                                price = "4453246",
                                image = "sds",
                                url = "sds",
                            ),
                        )
                    )
                }
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