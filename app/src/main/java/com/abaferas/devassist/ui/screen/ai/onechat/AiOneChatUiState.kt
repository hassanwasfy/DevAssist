package com.abaferas.devassist.ui.screen.ai.onechat


import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.ErrorUiState

data class AiOneChatUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val msgList: MutableList<ChatMessage> = mutableListOf(),
    val isResponsing: Boolean = false,
    val msgValue: String = "",
) : BaseUiState{
    data class ChatMessage(
        val role: Role,
        val msg: String,
    ){
        enum class Role(val role: String){
            USER("user"),MODEL("model")
        }
    }
}