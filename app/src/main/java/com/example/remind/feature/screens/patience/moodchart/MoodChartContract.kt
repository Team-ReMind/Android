package com.example.remind.feature.screens.patience.moodchart

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.response.Mood
import com.example.remind.data.model.response.MoodChartResponse
import com.example.remind.feature.screens.patience.home.HomeContract

class MoodChartContract {
    data class State(
        val moodChartData: Mood = Mood(true, emptyList()),
        val xAxisData: List<String> = emptyList(),
        val date: String = ""
    ): UiState

    sealed class Event: UiEvent {
        data class LoadingNextData(val year: Int, val month: Int, val day: Int, val size: Int):Event()
        data class LoadingPreviousData(val year: Int, val month: Int, val day: Int):Event()
        data class storeDate(val date: String):Event()
    }

    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destinaton: String,
            val navOptions: NavOptions? = null,
            val builder: NavOptionsBuilder.() -> Unit = {}
        ): MoodChartContract.Effect()
        data class Toastmessage(val message: String): MoodChartContract.Effect()
    }
}