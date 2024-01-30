package com.abaferas.devassist.domain.models

data class LearningItem(
    val itemId:String,
    val userId: String,
    val name: String,
    val type: LearningType,
    val author: String,
    val startDate: String,
    val endDate: String,
    val notesCount: Int,
    val totalAmount: Int,
    val finishedAmount: Int,
    val progress: Float,
)
