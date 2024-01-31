package com.abaferas.devassist.ui.mappers

import com.abaferas.devassist.domain.models.LearningItem
import com.abaferas.devassist.ui.screen.item.edititem.EditItemUiState


fun EditItemUiState.toDomain(): LearningItem {
    return LearningItem(
        itemId = this.itemId,
        userId = this.userId,
        name = this.name.value,
        type = this.type,
        author = this.author.value,
        startDate = this.startDate.value,
        endDate = this.endDate.value,
        totalAmount = this.totalAmount.value.toInt(),
        finishedAmount = this.finishedAmount.value.toInt(),
        progress = this.progress.value.toFloat(),
    )
}