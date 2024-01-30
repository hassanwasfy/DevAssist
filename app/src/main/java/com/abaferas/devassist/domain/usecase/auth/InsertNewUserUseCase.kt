package com.abaferas.devassist.domain.usecase.auth

import com.abaferas.devassist.data.repository.AuthRepository
import com.abaferas.devassist.domain.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class InsertNewUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(newUser: User): Task<DocumentReference?> {
        return authRepository.insertNewUser(newUser)
    }
}