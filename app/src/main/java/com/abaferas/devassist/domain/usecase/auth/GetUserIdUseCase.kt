package com.abaferas.devassist.domain.usecase.auth

import com.abaferas.devassist.data.repository.AuthRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): String {
        return authRepository.getUserId()
    }
}