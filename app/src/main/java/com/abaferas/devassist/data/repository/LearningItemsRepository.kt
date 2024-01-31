package com.abaferas.devassist.data.repository

import com.abaferas.devassist.domain.models.LearningItem
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow

interface LearningItemsRepository {
    suspend fun saveNewLearningItem(learningItem: LearningItem): Task<DocumentReference?>
    suspend fun editLearningItem(learningItem: LearningItem): Task<Void?>
    suspend fun deleteLearningItem(learningItem: LearningItem): Task<Void?>
    suspend fun getAllLearningItems(userId: String): Flow<List<LearningItem>>
    suspend fun getItemById(itemId: String): LearningItem
}