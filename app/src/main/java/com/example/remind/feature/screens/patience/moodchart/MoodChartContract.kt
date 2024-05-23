package com.example.remind.feature.screens.patience.moodchart

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.response.DailyActivity
import com.example.remind.data.model.response.DailyTakingMedicineList
import com.example.remind.data.model.response.Mood
import com.example.remind.data.model.response.PercentList
import java.time.LocalDate

class MoodChartContract {
    data class State(
        val moodChartData: Mood = Mood(true, emptyList()),
        val xAxisData: List<String> = emptyList(),
        val yAxisData: List<Int> = emptyList(),
        val date: String = LocalDate.now().dayOfMonth.toString(),
        val dailyMood: DailyActivity = DailyActivity(),
        val feelingTotalPerCent: List<PercentList> = emptyList(),
        val dailyMedicine: List<DailyTakingMedicineList> = emptyList(),
        val currentSeriesDay: Int = 0
    ): UiState

    sealed class Event: UiEvent {
        data class LoadingNextData(val year: Int, val month: Int, val day: Int, val size: Int):Event()
        //data class ClickToBottomSheet(val moodDate: String):Event()
        //object ClickToBottomSheet:Event()
        data class storeDate(val date: String):Event()
        object clickToBottomsheet :Event()
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