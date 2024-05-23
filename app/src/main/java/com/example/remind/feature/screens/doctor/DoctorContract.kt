package com.example.remind.feature.screens.doctor

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.response.DoctorData
import com.example.remind.data.model.response.GetMonthlyMedicineResponse
import com.example.remind.data.model.response.Mood
import com.example.remind.data.model.response.PercentList
import com.example.remind.data.model.response.Prescription
import com.example.remind.data.model.response.Rate

class DoctorContract {
    data class State (
        val doctorData: DoctorData = DoctorData(),
        val acceptList: DoctorData = DoctorData(),
        val memberId: Int? = null,
        val connectionStatus: Boolean = false,
        val rate: Rate = Rate(),
        val moodChartData: Mood = Mood(true, emptyList()),
        val xAxisData: List<String> = emptyList(),
        val yAxisData: List<Int> = emptyList(),
        val prescription: Prescription = Prescription(),
        val getMonthlyMedicineResponse: GetMonthlyMedicineResponse = GetMonthlyMedicineResponse(),
        val feelingTotalPerCent: List<PercentList> = emptyList(),
    ): UiState

    sealed class Event: UiEvent {
        data class RegisterButtonClicked(val context: Context): Event()
        data class acceptButtonClicked(val memberId: Int): Event()
        data class ClickedToManage(val memberId: Int): Event()
        object ClickToUpdate:Event()
        object ClickToMedicine:Event()
        object ClickToMood:Event()
        object ClickToBottomSheet:Event()
    }

    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null,
            val builder: NavOptionsBuilder.() -> Unit = {}
        ): Effect()
    }
}