package com.abaferas.devassist.ui.screen.books.bookdetails


import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class BookDetailsScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : BookDetailsScreenUiEffect()
}