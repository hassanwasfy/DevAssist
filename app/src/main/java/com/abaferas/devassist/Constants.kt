package com.abaferas.devassist

object Constants{
    const val NAME_LENGTH = 4
    const val ANIMATION_DURATION = 300
    const val MODEL_NAME = "gemini-pro"
    const val COLLECTION_USERS = "users"
    const val COLLECTION_LEARNING_ITEMS = "learningItems"
    const val COLLECTION_CHATS = "chats"
    const val TOP_BAR_HEIGHT = 64
    const val BOTTOM_BAR_HEIGHT = 80
    const val CONNECTION_TIME_OUT = 5000L
}

enum class Role(val role: String){
    USER("user"),MODEL("model")
}