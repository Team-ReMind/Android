package com.example.remind.data.model.request

data class WritingMoodRequest(
    val detail: String,
    val feelingType: String,
    val localDate: String,
    val moodActivities: List<MoodActivity>
)
data class MoodActivity(
    val activityId: Int,
    val detail: String,
    val feelingType: String
)