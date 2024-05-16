package com.example.remind.domain.usecase.onboarding_usecase

import com.example.remind.data.model.request.KakaoLoginRequest
import com.example.remind.data.model.response.SocialLoginResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.auth.AuthRepository
import javax.inject.Inject

class KakaoTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(requestBody: KakaoLoginRequest): ApiResult<SocialLoginResponse> {
        return authRepository.getTokenFromKakao(requestBody)
    }
}