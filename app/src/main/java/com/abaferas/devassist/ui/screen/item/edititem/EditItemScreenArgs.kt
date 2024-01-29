package com.abaferas.devassist.ui.screen.item.edititem

import androidx.lifecycle.SavedStateHandle

class EditItemScreenArgs(savedStateHandle: SavedStateHandle) {

    val itemId: String = savedStateHandle[ITEM_ID] ?: ""

    companion object {
        const val ITEM_ID = "item_id"
    }
}