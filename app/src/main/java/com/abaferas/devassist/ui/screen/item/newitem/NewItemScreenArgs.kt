package com.abaferas.devassist.ui.screen.item.newitem

import androidx.lifecycle.SavedStateHandle

class NewItemScreenArgs(savedStateHandle: SavedStateHandle) {

    val type: String? = savedStateHandle[TYPE_NAME]

    companion object {
        const val TYPE_NAME = "type_name"
    }
}