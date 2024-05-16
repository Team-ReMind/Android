package com.example.remind.domain.usecase.onboarding_usecase

import com.example.remind.data.repository.auth.TokenRepository
import javax.inject.Inject

class FcmTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke() : String {
        return tokenRepository.getFCMToken()
    }
}