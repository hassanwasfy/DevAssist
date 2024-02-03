package com.abaferas.devassist.ui.screen.ai.chatslist

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.ui.base.BaseViewModel
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenAiChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<AiChatUiState, AiChatScreenUiEffect>(AiChatUiState()), AiChatScreenInteraction {

    private val args: AiChatScreenArgs = AiChatScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {
        sendUiEffect(AiChatScreenUiEffect.NavigateUp)
    }

    override fun onError(errorMsg: String) {

    }

    override fun addNewChat() {
        sendUiEffect(AiChatScreenUiEffect.NewChat)
    }
}