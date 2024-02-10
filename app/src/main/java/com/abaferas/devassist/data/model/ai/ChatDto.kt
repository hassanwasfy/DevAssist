package com.abaferas.devassist.data.model.ai

import com.abaferas.devassist.Role

data class ChatDto(
    val role: Role,
    val msg: String,
    val time: Long,
)
