package com.example.remind.domain.usecase.onboarding_usecase

import com.example.remind.data.model.response.MemberInfoResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.auth.AuthRepository
import javax.inject.Inject

class MemberInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(memberId: Int): ApiResult<MemberInfoResponse> {
        return authRepository.getMemberInfo(memberId)
    }
}