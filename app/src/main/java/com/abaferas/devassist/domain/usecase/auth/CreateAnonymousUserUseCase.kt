package com.abaferas.devassist.domain.usecase.auth

import com.abaferas.devassist.data.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class CreateAnonymousUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Task<AuthResult?> {
        return authRepository.signInAnonymous()
    }
}