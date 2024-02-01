package com.abaferas.devassist.ui.screen.books.bookdetails

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.abaferas.devassist.ui.base.BaseViewModel


@HiltViewModel
class ScreenBookDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<BookDetailsUiState, BookDetailsScreenUiEffect>(BookDetailsUiState()),
    BookDetailsScreenInteraction {

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {

    }

    override fun onError(errorMsg: String) {

    }
}