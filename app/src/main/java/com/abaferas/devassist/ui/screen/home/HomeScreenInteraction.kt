package com.abaferas.devassist.ui.screen.home

interface HomeScreenInteraction {
    fun onClickBack()
    fun onClickRetry()
    fun onClickAddNewItem()
    fun onClickEditItem(itemId: String)
    fun onSearchValueChange(value: String)
    fun onClickFilter()
    fun onClickSearch()
}