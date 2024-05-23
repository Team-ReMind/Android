package com.example.remind.data.network.service

import com.example.remind.data.model.request.KakaoLoginRequest
import com.example.remind.data.model.request.OnBoardingRequest
import com.example.remind.data.model.request.TokenRequest
import com.example.remind.data.model.response.MemberInfoResponse
import com.example.remind.data.model.response.OnBoardingResponse
import com.example.remind.data.model.response.SocialLoginResponse
import com.example.remind.data.model.response.TokenResponse
import com.example.remind.data.network.adapter.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("/member/refresh")
    suspend fun updateToken(
        @Body body: TokenRequest
    ): ApiResult<TokenResponse>

    @POST("/member/login")
    suspend fun KakaoLogin(
        @Body body: KakaoLoginRequest
    ): ApiResult<SocialLoginResponse>

    @POST("/member/onboarding")
    suspend fun OnBoarding(
        @Body body: OnBoardingRequest
    ): ApiResult<OnBoardingResponse>

    @GET("/member/info")
    suspend fun MemberInfo(
        @Query("memberId") memberId:Int
    ): ApiResult<MemberInfoResponse>
}