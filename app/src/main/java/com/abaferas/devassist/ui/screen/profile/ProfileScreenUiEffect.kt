package com.abaferas.devassist.ui.screen.profile

import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class ProfileScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : ProfileScreenUiEffect()
}