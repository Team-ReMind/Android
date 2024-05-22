package com.example.remind.feature.screens.patience.writing

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.request.MoodActivity
import com.example.remind.data.model.request.WritingMoodRequest
import com.example.remind.data.model.response.Activity

class WritingContract {
    data class State(
        //디폴트 활동 조회
        val ActivityList: List<Activity> = emptyList(),
        val moodActivity: MoodActivity = MoodActivity(),
        val writingMoodRequest: WritingMoodRequest = WritingMoodRequest()
    ): UiState

    sealed class Event: UiEvent {

        data class FeelingButtonClicked(val feelingType: String): Event()
        data class NextButtonClicked(val destinationRoute: String, val currentRoute: String): Event()
        data class PreviousButtonClicked(val destinationRoute: String, val currentRoute: String): Event()
        data class ActivityButtonClicked(val activityId: Int): Event()
        data class FeelingActivityButtonClicked(val feelingType: String): Event()
        data class StoreFeelingListItem(val detail: String): Event()
        data class NavigateToHome(val context: Context): Event()
        data class NavigateToStep2(val destinationRoute: String, val currentRoute: String): Event()
        data class SendInfoButton(val destinationRoute: String, val currentRoute: String): Event()
    }

    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destinaton: String,
            val navOptions: NavOptions? = null,
            val builder: NavOptionsBuilder.() -> Unit = {}
        ): WritingContract.Effect()
        data class Toastmessage(val message: String): Effect()
    }
}