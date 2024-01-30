package com.abaferas.devassist.ui.screen.home

import androidx.compose.ui.graphics.vector.ImageVector
import com.abaferas.devassist.data.model.LearningType

interface HomeScreenInteraction {
    fun onClickBack()
    fun onClickRetry()
    fun onClickAddNewItem()
    fun onClickEditItem(itemId: String)
    fun onSearchValueChange(value: String)
    fun onClickFilter()
    fun onClickSearch()
    fun iconFinder(type: LearningType): ImageVector
}