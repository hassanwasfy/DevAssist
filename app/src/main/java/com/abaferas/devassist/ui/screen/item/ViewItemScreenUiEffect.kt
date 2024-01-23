package com.abaferas.devassist.ui.screen.item

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class ViewItemScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : ViewItemScreenUiEffect()
}