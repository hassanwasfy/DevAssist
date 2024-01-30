package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.model.LearningItemDTO
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow

interface LearningItemsRepository {
    suspend fun saveNewLearningItem(learningItem: LearningItemDTO): Task<DocumentReference?>
    suspend fun editLearningItem(learningItem: LearningItemDTO): Task<Void?>
    suspend fun deleteLearningItem(learningItem: LearningItemDTO): Task<Void?>
    suspend fun getAllLearningItems(userId: String): Flow<List<LearningItemDTO>>
}