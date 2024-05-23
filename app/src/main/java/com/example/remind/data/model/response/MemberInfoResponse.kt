package com.example.remind.data.model.response

data class MemberInfoResponse(
    val code: Int,
    val `data`: Info,
    val message: String
)
data class Info(
    val age: Int=0,
    val gender: String="",
    val imageUrl: String="",
    val name: String=""
)