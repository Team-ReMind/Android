package com.example.remind.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val code: Int,
    val data: TokenDataClass,
    val message: String
)
@Serializable
data class TokenDataClass(
    val accessToken: String,
    val refreshToken: String
)
