package com.abaferas.devassist.data.model

data class LearningItem(
    val id: String,
    val userId: Int,
    val name: String,
    val type: LearningType,
    val author: String,
    val startDate: String,
    val endDate: String,
    val notes: MutableList<String>,
    val notesCount: Int,
    val totalAmount: Int,
    val finishedAmount: Int,
    val progress: Float,
)
enum class LearningType {
    Video, BOOK, PLAY_LIST, Azkar
}


val homeList = listOf(
    LearningItem(
        id = "1",
        userId = 1,
        name = "Atomic",
        type = LearningType.BOOK,
        author = "Jhon",
        startDate = "22 Dec",
        endDate = "28 Dec",
        notes = mutableListOf(),
        notesCount = 0,
        totalAmount = 400,
        finishedAmount = 200,
        progress = 50f
    ),
    LearningItem(
        id = "2",
        userId = 1,
        name = "Atomic",
        type = LearningType.BOOK,
        author = "Jhon",
        startDate = "22 Dec",
        endDate = "28 Dec",
        notes = mutableListOf(),
        notesCount = 0,
        totalAmount = 400,
        finishedAmount = 200,
        progress = 50f
    ),
    LearningItem(
        id = "3",
        userId = 1,
        name = "Atomic",
        type = LearningType.BOOK,
        author = "Jhon",
        startDate = "22 Dec",
        endDate = "28 Dec",
        notes = mutableListOf(),
        notesCount = 0,
        totalAmount = 400,
        finishedAmount = 200,
        progress = 50f
    ),
    LearningItem(
        id = "4",
        userId = 1,
        name = "Atomic",
        type = LearningType.BOOK,
        author = "Jhon",
        startDate = "22 Dec",
        endDate = "28 Dec",
        notes = mutableListOf(),
        notesCount = 0,
        totalAmount = 400,
        finishedAmount = 200,
        progress = 50f
    ),
)