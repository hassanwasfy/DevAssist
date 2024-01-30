package com.abaferas.devassist.domain.usecase

import com.abaferas.devassist.data.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class CreateNewUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String,password: String): Task<AuthResult?> {
        return authRepository.creteUserWithEmailAndPassword(email, password)
    }
}