package com.abaferas.devassist.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AiRepositoryImpl @Inject constructor(
    private val generativeModel: GenerativeModel
) : AiRepository {
    override suspend fun sendMessage(msg: String): Flow<GenerateContentResponse> {
        return generativeModel.generateContentStream(msg)
    }
}