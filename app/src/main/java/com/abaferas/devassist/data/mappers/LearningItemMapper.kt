package com.abaferas.devassist.data.mappers

import com.abaferas.devassist.data.model.learning.LearningItemDTO
import com.abaferas.devassist.domain.models.LearningItem
import com.abaferas.devassist.domain.models.LearningType

fun LearningItemDTO.toDomainModel(): LearningItem {
    return LearningItem(
        itemId = this.itemId ?: "",
        userId = this.userId ?: "",
        name = this.name ?: "",
        type = this.type ?: LearningType.BOOK,
        author = this.author ?: "",
        startDate = this.startDate ?: "",
        endDate = this.endDate ?: "",
        totalAmount = this.totalAmount ?: 0,
        finishedAmount = this.finishedAmount ?: 0,
        progress = this.progress ?: 0F,
    )
}

fun LearningItem.toDtoModel(): LearningItemDTO {
    return LearningItemDTO(
        itemId = this.itemId,
        userId = this.userId,
        name = this.name,
        type = this.type,
        author = this.author,
        startDate = this.startDate,
        endDate = this.endDate,
        totalAmount = this.totalAmount,
        finishedAmount = this.finishedAmount,
        progress = this.progress,
    )
}