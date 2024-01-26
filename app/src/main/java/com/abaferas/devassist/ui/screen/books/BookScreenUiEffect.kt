package com.abaferas.devassist.ui.screen.books

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class BookScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : BookScreenUiEffect()
}