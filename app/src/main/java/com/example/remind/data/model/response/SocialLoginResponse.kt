package com.example.remind.data.model.response

data class SocialLoginResponse(
    val code: Int,
    val data: MemberInfo,
    val message: String
)

data class MemberInfo(
    val accessToken: String,
    val isMemberFinishedOnboarding: Boolean,
    val name: String,
    val refreshToken: String
)