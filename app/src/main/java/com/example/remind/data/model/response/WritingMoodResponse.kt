package com.example.remind.data.model.response

data class WritingMoodResponse(
    val code: Int,
    val `data`: MoodData,
    val message: String
)
data class MoodData(
    val moodId: Int
)