package com.abaferas.devassist.ui.screen.item.edititem

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.abaferas.devassist.ui.base.BaseViewModel


@HiltViewModel
class ScreenEditItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): BaseViewModel<EditItemUiState, EditItemScreenUiEffect>(EditItemUiState()),
    EditItemScreenInteraction {

    private val args: EditItemScreenArgs = EditItemScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {

    }

    override fun onError(errorMsg: String) {

    }

    override fun onNameChange(value: String) {

    }

    override fun onAuthorChange(value: String) {

    }

    override fun onStartDateChange(value: String) {

    }

    override fun onEndDateChange(value: String) {

    }

    override fun onTypeChange(value: String) {

    }

    override fun onAmountChange(value: String) {

    }

    override fun onFinishedChange(value: String) {

    }

    override fun onProgressChange(value: String) {

    }

    override fun onClickSave() {

    }

    override fun onClickEdit() {

    }

    override fun onClickDelete() {

    }
}