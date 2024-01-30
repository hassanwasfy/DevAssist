package com.abaferas.devassist.data.mappers

import com.abaferas.devassist.data.model.LearningItemDTO
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
        notesCount = this.notesCount ?: 0,
        totalAmount = this.totalAmount ?: 0,
        finishedAmount = this.finishedAmount ?: 0,
        progress = this.progress ?: 0F,
    )
}