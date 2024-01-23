package com.abaferas.devassist.ui.screen.item

import androidx.lifecycle.SavedStateHandle

class ViewItemScreenArgs(savedStateHandle: SavedStateHandle) {

    val itemId: Int = savedStateHandle[ITEM_ID] ?: 0

    companion object {
        const val ITEM_ID = "item_id"
    }
}