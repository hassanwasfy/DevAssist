package com.abaferas.devassist.ui.screen.item

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenViewItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ViewItemUiState, ViewItemScreenUiEffect>(ViewItemUiState()),
    ViewItemScreenInteraction {

    private val args: ViewItemScreenArgs = ViewItemScreenArgs(savedStateHandle = savedStateHandle)

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