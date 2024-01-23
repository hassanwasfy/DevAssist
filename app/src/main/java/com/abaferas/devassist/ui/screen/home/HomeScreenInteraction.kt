package com.abaferas.devassist.ui.screen.home

interface HomeScreenInteraction {
    fun onClickBack()
    fun onClickAddNewItem()
    fun onClickEditItem(itemId: Int)
}