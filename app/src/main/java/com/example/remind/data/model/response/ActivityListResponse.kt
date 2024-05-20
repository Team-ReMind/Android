package com.example.remind.data.model.response

data class ActivityListResponse(
    val code: Int,
    val `data`: ActivityData,
    val message: String
)

data class ActivityData(
    val activities: List<Activity>
)

data class Activity(
    val activityId: Int,
    val iconImage: String,
    val name: String
)