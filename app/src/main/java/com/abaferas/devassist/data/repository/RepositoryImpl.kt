package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.model.LearningItem
import com.abaferas.devassist.data.model.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : IRepository {
    private val collectionUSERS = "users"
    private val collectionLEARNINGITEMS = "learningItems"
    override suspend fun creteUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult? {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        } catch (e: FirebaseFirestoreException) {
            throw e
        }
    }

    override suspend fun signInAnonymous(): AuthResult? {
        return wrapRequest { firebaseAuth.signInAnonymously().await() }
    }

    override suspend fun logInWithEmailAndPassword(email: String, password: String): AuthResult? {
        return wrapRequest { firebaseAuth.signInWithEmailAndPassword(email, password).await() }
    }

    override suspend fun insertNewUser(user: User) {
        return wrapRequest { firestore.collection(collectionUSERS).add(user) }
    }

    override suspend fun getUserId(): String {
        return wrapRequest { firebaseAuth.currentUser?.uid ?: "" }
    }

    override suspend fun saveNewLearningItem(learningItem: LearningItem): DocumentReference {
        return wrapRequest {
            firestore.collection(collectionLEARNINGITEMS).add(learningItem).await()
        }
    }

    override suspend fun editLearningItem(learningItem: LearningItem) {
        return wrapRequest {
            firestore.collection(collectionLEARNINGITEMS).document(learningItem.id)
                .set(learningItem).await()
        }
    }

    override suspend fun deleteLearningItem(learningItem: LearningItem) {
        return wrapRequest {
            firestore.collection(collectionLEARNINGITEMS).document(learningItem.id).delete().await()
        }
    }

    override suspend fun getAllLearningItems(userId: String): List<LearningItem> {
        return wrapRequest {
            firestore.collection(collectionLEARNINGITEMS)
                .whereEqualTo("userId", userId)
                .get()
                .await().toObjects(LearningItem::class.java)
        }
    }

    private suspend fun <T> wrapRequest(
        response: suspend () -> T
    ): T {
        return try {
            response()
        } catch (e: FirebaseFirestoreException) {
            throw e
        }
    }
}