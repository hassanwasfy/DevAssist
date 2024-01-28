package com.abaferas.devassist.ui.screen.item

import androidx.lifecycle.SavedStateHandle

class ViewItemScreenArgs(savedStateHandle: SavedStateHandle) {

    val itemId: String = savedStateHandle[ITEM_ID] ?: ""

    companion object {
        const val ITEM_ID = "item_id"
    }
}