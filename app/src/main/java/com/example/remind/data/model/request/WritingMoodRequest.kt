package com.example.remind.data.model.request

data class WritingMoodRequest(
    val detail: String="",
    val feelingType: String = "",
    val localDate: String = "",
    val moodActivities: List<MoodActivity> = emptyList()
)
data class MoodActivity(
    val activityId: Int=0,
    val detail: String="",
    val feelingType: String=""
)