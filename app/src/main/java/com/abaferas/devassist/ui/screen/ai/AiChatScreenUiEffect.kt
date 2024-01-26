package com.abaferas.devassist.ui.screen.ai

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class AiChatScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : AiChatScreenUiEffect()
}