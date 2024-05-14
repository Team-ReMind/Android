package com.example.remind.data.network.service

import com.example.remind.data.model.request.KakaoLoginRequest
import com.example.remind.data.model.request.TokenRequest
import com.example.remind.data.model.response.SocialLoginResponse
import com.example.remind.data.model.response.TokenResponse
import com.example.remind.data.network.adapter.ApiResult
import retrofit2.http.Body
import retrofit2.http.POST

//용도에 따라 나누기
interface AuthService {
    @POST("/member/refresh")
    suspend fun updateToken(
        @Body body: TokenRequest
    ): ApiResult<TokenResponse>

    @POST("/member/login")
    suspend fun KakaoLogin(
        @Body body: KakaoLoginRequest
    ): ApiResult<SocialLoginResponse>
}