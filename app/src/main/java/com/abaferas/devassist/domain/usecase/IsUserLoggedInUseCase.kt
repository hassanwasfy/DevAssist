package com.abaferas.devassist.domain.usecase

import com.abaferas.devassist.data.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): FirebaseUser? {
        return authRepository.isUserLoggedIn()
    }
}