package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.model.UserDto
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference

interface AuthRepository {
    suspend fun creteUserWithEmailAndPassword(email: String, password:String): Task<AuthResult?>
    suspend fun signInAnonymous(): Task<AuthResult?>
    suspend fun logInWithEmailAndPassword(email: String, password:String): Task<AuthResult?>
    suspend fun isUserLoggedIn(): FirebaseUser?
    suspend fun getUserId(): String
    suspend fun insertNewUser(userDto: UserDto): Task<DocumentReference?>
}