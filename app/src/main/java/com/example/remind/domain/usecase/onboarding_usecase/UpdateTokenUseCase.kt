package com.example.remind.domain.usecase.onboarding_usecase

import com.example.remind.data.model.request.TokenRequest
import com.example.remind.data.model.response.TokenResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.auth.AuthRepository
import javax.inject.Inject

//class UpdateTokenUseCase @Inject constructor(
//    private val authRepository: AuthRepository
//) {
//    suspend operator fun invoke(requestBody: TokenRequest): ApiResult<TokenResponse> {
//        return authRepository.updateToken(requestBody)
//    }
//}