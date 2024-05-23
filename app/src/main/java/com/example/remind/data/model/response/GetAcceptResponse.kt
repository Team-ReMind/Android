package com.example.remind.data.model.response

data class GetAcceptResponse(
    val code: Int,
    val `data`: Accept,
    val message: String
)
data class Accept(
    val connectionId: Int
)