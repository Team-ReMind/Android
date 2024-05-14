package com.example.remind.data.model.request

import kotlinx.serialization.Serializable
@Serializable
data class TokenRequest(
    val refreshToken: String
)
