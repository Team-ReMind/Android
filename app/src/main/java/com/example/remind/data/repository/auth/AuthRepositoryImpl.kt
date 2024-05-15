package com.example.remind.data.repository.auth

import com.example.remind.data.model.request.KakaoLoginRequest
import com.example.remind.data.model.request.OnBoardingRequest
import com.example.remind.data.model.request.TokenRequest
import com.example.remind.data.model.response.OnBoardingResponse
import com.example.remind.data.model.response.SocialLoginResponse
import com.example.remind.data.model.response.TokenResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.service.AuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: AuthService
): AuthRepository {

    override suspend fun getTokenFromKakao(kakaoLoginRequest: KakaoLoginRequest): ApiResult<SocialLoginResponse> {
        return service.KakaoLogin(kakaoLoginRequest)
    }

    override suspend fun postOnBoardingInfo(body: OnBoardingRequest): ApiResult<OnBoardingResponse> {
        return service.OnBoarding(body)
    }

}