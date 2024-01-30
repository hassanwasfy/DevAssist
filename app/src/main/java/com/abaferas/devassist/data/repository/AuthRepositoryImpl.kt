package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.Constants
import com.abaferas.devassist.data.model.UserDto
import com.abaferas.devassist.data.utils.wrapRequest
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun creteUserWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult?> {
        return wrapRequest {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
        }
    }

    override suspend fun signInAnonymous(): Task<AuthResult?> {
        return wrapRequest { firebaseAuth.signInAnonymously() }
    }

    override suspend fun logInWithEmailAndPassword(email: String, password: String): Task<AuthResult?> {
        return wrapRequest { firebaseAuth.signInWithEmailAndPassword(email, password) }
    }

    override suspend fun isUserLoggedIn(): FirebaseUser? {
        return wrapRequest { firebaseAuth.currentUser }
    }

    override suspend fun insertNewUser(userDto: UserDto): Task<DocumentReference?> {
        return wrapRequest { firestore.collection(Constants.collectionUSERS).add(userDto) }
    }

    override suspend fun getUserId(): String {
        return wrapRequest { firebaseAuth.currentUser?.uid ?: "" }
    }
}