package com.abaferas.devassist.ui.screen.item.selecttype

import com.abaferas.devassist.domain.models.LearningType

interface SelectTypeScreenInteraction {
    fun onClickBack()
    fun onClickType(type: LearningType)
}