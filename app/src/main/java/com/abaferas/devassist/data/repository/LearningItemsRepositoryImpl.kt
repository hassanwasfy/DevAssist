package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.Constants
import com.abaferas.devassist.data.mappers.toDomainModel
import com.abaferas.devassist.data.mappers.toDtoModel
import com.abaferas.devassist.data.model.learning.LearningItemDTO
import com.abaferas.devassist.data.utils.wrapRequest
import com.abaferas.devassist.domain.models.LearningItem
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LearningItemsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : LearningItemsRepository {
    override suspend fun saveNewLearningItem(learningItem: LearningItem): Task<DocumentReference?> {
        val item = learningItem.toDtoModel()
        return wrapRequest {
            firestore.collection(Constants.collectionLEARNINGITEMS).add(item)
        }
    }

    override suspend fun editLearningItem(learningItem: LearningItem): Task<Void?> {
        val item = learningItem.toDtoModel()
        return wrapRequest {
            firestore.collection(Constants.collectionLEARNINGITEMS).document(item.name ?: "")
                .set(item)
        }
    }

    override suspend fun deleteLearningItem(learningItem: LearningItem): Task<Void?> {
        val item = learningItem.toDtoModel()
        return wrapRequest {
            firestore.collection(Constants.collectionLEARNINGITEMS).document(item.name ?: "")
                .delete()
        }
    }

    override suspend fun getItemById(itemId: String): LearningItem {
        return wrapRequest {
            firestore.collection(Constants.collectionLEARNINGITEMS)
                .whereEqualTo("itemId", itemId)
                .get()
        }.await().toObjects(LearningItemDTO::class.java).find {
            it.itemId == itemId
        }?.toDomainModel() ?: throw NoSuchElementException("Item not found!")
    }

    override suspend fun getAllLearningItems(userId: String): Flow<List<LearningItem>> =
        callbackFlow {
            val registration = firestore.collection(Constants.collectionLEARNINGITEMS)
                .whereEqualTo("userId", userId)
                .addSnapshotListener { querySnapshot, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }
                    val learningItemsList =
                        querySnapshot?.toObjects(LearningItemDTO::class.java)?.map {
                            it.toDomainModel()
                        } ?: emptyList()
                    this.trySend(learningItemsList).isSuccess
                }
            awaitClose {
                registration.remove()
            }
        }.flowOn(Dispatchers.IO)

}