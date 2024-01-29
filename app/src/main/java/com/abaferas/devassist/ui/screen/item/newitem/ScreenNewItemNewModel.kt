package com.abaferas.devassist.ui.screen.item.newitem

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.data.model.LearningType
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.utils.NetworkStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenNewItemNewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val networkStateManager: NetworkStateManager
) : BaseViewModel<NewItemUiState, NewItemScreenUiEffect>(NewItemUiState()),
    NewItemScreenInteraction {

    private val args: NewItemScreenArgs = NewItemScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        val id: String? = savedStateHandle[args.type]
        id?.let { type ->
            when(type){
                "Book" -> {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            type = LearningType.BOOK
                        )
                    }
                }
                "Azkar" -> {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            type = LearningType.Azkar
                        )
                    }
                }
                "Course" -> {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            type = LearningType.PLAY_LIST
                        )
                    }
                }
                "Video" -> {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            type = LearningType.Video
                        )
                    }
                }
            }
        }

    }

    override fun onClickBack() {
        sendUiEffect(NewItemScreenUiEffect.NavigateUp)
    }

    override fun onError(errorMsg: String) {

    }

    override fun onNameChange(value: String) {
        iState.update {
            it.copy(
                name = EntryTextValue(value = value)
            )
        }
    }

    override fun onAuthorChange(value: String) {
        iState.update {
            it.copy(
                author = EntryTextValue(value = value)
            )
        }
    }

    override fun onStartDateChange(value: String) {
        iState.update {
            it.copy(
                startDate = EntryTextValue(value = value)
            )
        }
    }

    override fun onEndDateChange(value: String) {
        iState.update {
            it.copy(
                endDate = EntryTextValue(value = value)
            )
        }
    }

    override fun onAmountChange(value: String) {
        iState.update {
            it.copy(
                totalAmount = EntryTextValue(value = value)
            )
        }
    }

    override fun onFinishedChange(value: String) {
        iState.update {
            it.copy(
                finishedAmount = EntryTextValue(value = value)
            )
        }
    }

    override fun onClickSave() {

    }

    override fun onDismiss() {
        iState.update {
            it.copy(
                selectingStartDate = false,
                selectingEndDate = false
            )
        }
    }

    override fun onOpenStartDialog() {
        iState.update {
            it.copy(
                selectingStartDate = true
            )
        }
    }

    override fun onOpenEndDialog() {
        iState.update {
            it.copy(
                selectingEndDate = true
            )
        }
    }
}