package com.abaferas.devassist.ui.screen.item.selecttype

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.data.model.LearningType
import com.abaferas.devassist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenSelectTypeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<SelectTypeUiState, SelectTypeScreenUiEffect>(SelectTypeUiState()),
    SelectTypeScreenInteraction {

    private val args: SelectTypeScreenArgs =
        SelectTypeScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {
        sendUiEffect(SelectTypeScreenUiEffect.NavigateUp)
    }

    override fun onError(errorMsg: String) {

    }

    override fun onClickType(type: LearningType) {
        when(type){
            LearningType.Video -> {
                sendUiEffect(SelectTypeScreenUiEffect.AddNewItem("Video"))
            }
            LearningType.BOOK -> {
                sendUiEffect(SelectTypeScreenUiEffect.AddNewItem("Book"))
            }
            LearningType.PLAY_LIST -> {
                sendUiEffect(SelectTypeScreenUiEffect.AddNewItem("PlayList"))
            }
            LearningType.Azkar -> {
                sendUiEffect(SelectTypeScreenUiEffect.AddNewItem("Azkar"))
            }
        }
    }
}