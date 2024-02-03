package com.abaferas.devassist.ui.screen.ai.chatslist

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class AiChatScreenUiEffect : BaseUiEffect {
    data object NavigateUp : AiChatScreenUiEffect()
    data object NewChat : AiChatScreenUiEffect()

}