package com.abaferas.devassist.ui.screen.item

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.data.model.LearningItem
import com.abaferas.devassist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenViewItemViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<ViewItemUiState, ViewItemScreenUiEffect>(ViewItemUiState()),
    ViewItemScreenInteraction {

    private val args: ViewItemScreenArgs = ViewItemScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        val id: String? = savedStateHandle[args.itemId]
        if (id == null){
            iState.update {
                it.copy(
                    isLoading = false
                )
            }
        }else{

        }

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
        when(value){
            "Book" -> {
                iState.update {
                    it.copy(
                        type = LearningItem.LearningType.BOOK,
                        isBook = true
                    )
                }
            }
            "Course" -> {
                iState.update {
                    it.copy(
                        type = LearningItem.LearningType.COURSE,
                        isBook = false
                    )
                }
            }
        }
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