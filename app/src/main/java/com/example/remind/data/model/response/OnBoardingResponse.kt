package com.example.remind.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class OnBoardingResponse(
    val code: Int,
    val message: String,
    val data: SuccessResponse
)
@Serializable
data class SuccessResponse(
    val userId: Int,
    val roles_type: String
)