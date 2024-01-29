package com.abaferas.devassist.ui.screen.item.selecttype

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class SelectTypeScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : SelectTypeScreenUiEffect()
    data class AddNewItem(val item: String): SelectTypeScreenUiEffect()
}