package com.example.remind.data.model.response

data class MoodChartResponse(
    val code: Int,
    val `data`: Mood,
    val message: String
)
data class Mood(
    val hasNext: Boolean,
    val moodChartDtos: List<MoodChartDto>
)

data class MoodChartDto(
    val feeling: String,
    val localDate: String,
    val score: Int
)