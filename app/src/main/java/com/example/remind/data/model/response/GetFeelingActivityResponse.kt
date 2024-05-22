package com.example.remind.data.model.response

data class GetFeelingActivityResponse(
    val code: Int,
    val `data`: List<FeelingActivity>,
    val message: String
)
data class FeelingActivity(
    val iconImage: String,
    val name: String,
    val percent: Int
)