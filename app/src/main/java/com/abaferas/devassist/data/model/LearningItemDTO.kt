package com.abaferas.devassist.data.model

import com.abaferas.devassist.domain.models.LearningType

data class LearningItemDTO(
    val itemId:String? = "",
    val userId: String? = "",
    val name: String? = "",
    val type: LearningType? = LearningType.BOOK,
    val author: String? = "",
    val startDate: String? = "",
    val endDate: String? = "",
    val totalAmount: Int? = 0,
    val finishedAmount: Int? = 0,
    val progress: Float? = 0F,
)
