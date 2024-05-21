package com.example.remind.data.model.response

data class GetFeelingPercentResponse(
    val code: Int,
    val `data`: List<PercentList>,
    val message: String
)
data class PercentList(
    val feelingType: String,
    val percent: Double
)