package com.abaferas.devassist.ui.screen.item.edititem


import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class EditItemScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : EditItemScreenUiEffect()
}