package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.model.LearningItem
import com.abaferas.devassist.data.model.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference

interface IRepository {

    suspend fun creteUserWithEmailAndPassword(email: String, password:String): AuthResult?
    suspend fun signInAnonymous(): AuthResult?
    suspend fun logInWithEmailAndPassword(email: String, password:String): AuthResult?

    suspend fun getUserId(): String
    suspend fun insertNewUser(user: User)
    suspend fun saveNewLearningItem(learningItem: LearningItem): DocumentReference
    suspend fun editLearningItem(learningItem: LearningItem)
    suspend fun deleteLearningItem(learningItem: LearningItem)
    suspend fun getAllLearningItems(userId: String): List<LearningItem>


}