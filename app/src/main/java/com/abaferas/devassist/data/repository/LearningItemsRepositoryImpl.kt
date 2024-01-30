package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.Constants
import com.abaferas.devassist.data.model.LearningItemDTO
import com.abaferas.devassist.data.utils.wrapRequest
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LearningItemsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
):LearningItemsRepository {
    override suspend fun saveNewLearningItem(learningItem: LearningItemDTO): Task<DocumentReference?> {
        return wrapRequest {
            firestore.collection(Constants.collectionLEARNINGITEMS).add(learningItem)
        }
    }

    override suspend fun editLearningItem(learningItem: LearningItemDTO): Task<Void?> {
        return wrapRequest {
            firestore.collection(Constants.collectionLEARNINGITEMS).document(learningItem.name?:"")
                .set(learningItem)
        }
    }

    override suspend fun deleteLearningItem(learningItem: LearningItemDTO): Task<Void?> {
        return wrapRequest {
            firestore.collection(Constants.collectionLEARNINGITEMS).document(learningItem.name?:"").delete()
        }
    }

    override suspend fun getAllLearningItems(userId: String): Flow<List<LearningItemDTO>> = callbackFlow {
        val registration = firestore.collection(Constants.collectionLEARNINGITEMS)
            .whereEqualTo("userId", userId)
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val learningItemsList = querySnapshot?.toObjects(LearningItemDTO::class.java) ?: emptyList()
                this.trySend(learningItemsList).isSuccess
            }
        awaitClose {
            registration.remove()
        }
    }.flowOn(Dispatchers.IO)
}