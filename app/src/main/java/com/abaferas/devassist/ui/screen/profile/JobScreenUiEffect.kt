package com.abaferas.devassist.ui.screen.profile


import com.abaferas.devassist.ui.base.BaseUiEffect

sealed class JobScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : JobScreenUiEffect()
}