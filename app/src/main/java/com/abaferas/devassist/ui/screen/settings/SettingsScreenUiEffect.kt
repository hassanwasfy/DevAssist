package com.abaferas.devassist.ui.screen.settings

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class SettingsScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : SettingsScreenUiEffect()
}