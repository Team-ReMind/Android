package com.example.remind.data.model.response

data class GetDailyMoodResponse(
    val code: Int,
    val `data`: DailyActivity,
    val message: String
)
data class DailyActivity(
    val activities: List<Activities> = emptyList(),
    val feelingType: String = "",
    val moodDetail: String = ""
)
data class Activities(
    val detail: String = "",
    val feelingType: String = "",
    val iconImg: String = "",
    val name: String =""
)