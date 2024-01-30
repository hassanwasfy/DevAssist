package com.abaferas.devassist.domain.usecase

import com.abaferas.devassist.data.model.UserDto
import com.abaferas.devassist.data.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class InsertNewUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(newUser: UserDto): Task<DocumentReference?> {
        return authRepository.insertNewUser(newUser)
    }
}