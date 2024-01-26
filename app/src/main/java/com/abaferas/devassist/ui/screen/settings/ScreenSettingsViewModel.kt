package com.abaferas.devassist.ui.screen.settings

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenSettingsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SettingsUiState, SettingsScreenUiEffect>(SettingsUiState()),
    SettingsScreenInteraction {

    private val args: SettingsScreenArgs = SettingsScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onError(errorMsg: String) {

    }

    override fun onClickBack() {

    }
}