package com.example.remind.domain.usecase

import com.example.remind.data.model.request.OnBoardingRequest
import com.example.remind.data.model.response.OnBoardingResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.auth.AuthRepository
import javax.inject.Inject

class OnBoardingUserCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(requestBody: OnBoardingRequest): ApiResult<OnBoardingResponse> {
        return authRepository.postOnBoardingInfo(requestBody)
    }
}