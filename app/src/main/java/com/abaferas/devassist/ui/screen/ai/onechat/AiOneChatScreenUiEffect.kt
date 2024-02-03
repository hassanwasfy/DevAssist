package com.abaferas.devassist.ui.screen.ai.onechat


import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class AiOneChatScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : AiOneChatScreenUiEffect()
}