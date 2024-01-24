package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.model.LearningItem
import com.abaferas.devassist.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference

interface IRepository {

    suspend fun creteUserWithEmailAndPassword(email: String, password:String): Task<AuthResult?>
    suspend fun signInAnonymous(): Task<AuthResult?>
    suspend fun logInWithEmailAndPassword(email: String, password:String): Task<AuthResult?>

    suspend fun getUserId(): String
    suspend fun insertNewUser(user: User):Task<DocumentReference?>
    suspend fun saveNewLearningItem(learningItem: LearningItem): DocumentReference
    suspend fun editLearningItem(learningItem: LearningItem)
    suspend fun deleteLearningItem(learningItem: LearningItem)
    suspend fun getAllLearningItems(userId: String): List<LearningItem>


}