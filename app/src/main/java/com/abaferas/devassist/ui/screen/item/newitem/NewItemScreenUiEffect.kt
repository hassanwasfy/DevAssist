package com.abaferas.devassist.ui.screen.item.newitem

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class NewItemScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : NewItemScreenUiEffect()
    data object NavigateHome : NewItemScreenUiEffect()
}