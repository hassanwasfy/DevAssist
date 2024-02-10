package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.model.ai.ChatDto
import com.google.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.flow.Flow

interface AiRepository {
    suspend fun sendMessage(msg: String):Flow<GenerateContentResponse>
}