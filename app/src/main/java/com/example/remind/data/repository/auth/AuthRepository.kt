package com.example.remind.data.repository.auth

import com.example.remind.data.model.request.KakaoLoginRequest
import com.example.remind.data.model.request.OnBoardingRequest
import com.example.remind.data.model.response.MemberInfoResponse
import com.example.remind.data.model.response.OnBoardingResponse
import com.example.remind.data.model.response.SocialLoginResponse
import com.example.remind.data.network.adapter.ApiResult

interface AuthRepository {
    suspend fun getTokenFromKakao(body: KakaoLoginRequest): ApiResult<SocialLoginResponse>
    suspend fun postOnBoardingInfo(body: OnBoardingRequest): ApiResult<OnBoardingResponse>
    suspend fun getMemberInfo(memberId: Int): ApiResult<MemberInfoResponse>
}