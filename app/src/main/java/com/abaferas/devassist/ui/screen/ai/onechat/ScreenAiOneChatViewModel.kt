package com.abaferas.devassist.ui.screen.ai.onechat

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.abaferas.devassist.ui.base.BaseViewModel


@HiltViewModel
class ScreenAiOneChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<AiOneChatUiState, AiOneChatScreenUiEffect>(AiOneChatUiState()),
    AiOneChatScreenInteraction {

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