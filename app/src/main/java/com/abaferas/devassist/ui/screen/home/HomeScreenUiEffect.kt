package com.abaferas.devassist.ui.screen.home

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class HomeScreenUiEffect : BaseUiEffect {
    data object NavigateUp : HomeScreenUiEffect()
    data object AddNewItem : HomeScreenUiEffect()
    data class EditCurrentItem(val itemId: String) : HomeScreenUiEffect()
}