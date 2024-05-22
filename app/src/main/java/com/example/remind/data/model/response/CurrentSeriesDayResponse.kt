package com.example.remind.data.model.response

data class CurrentSeriesDayResponse(
    val code: Int,
    val `data`: CurrentSeries,
    val message: String
)
data class CurrentSeries(
    val currentSeriesDays: Int
)