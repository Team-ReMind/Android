package com.example.remind.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SocialLoginResponse(
    val code: Int,
    val data: MemberInfo,
    val message: String
)
@Serializable
data class MemberInfo(
    val accessToken: String,
    val rolesType: String,
    val name: String,
    val refreshToken: String
)