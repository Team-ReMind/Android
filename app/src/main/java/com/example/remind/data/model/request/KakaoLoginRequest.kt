package com.example.remind.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class KakaoLoginRequest(
    val kakaoAccessToken: String
)
