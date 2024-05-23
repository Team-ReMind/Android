package com.example.remind.feature.screens.patience.home

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.response.DailyTakingMedicineList
import com.example.remind.data.model.response.Data

class HomeContract {
    data class State(
        val sosDialogState: Boolean = false,
        val medicineDialogState:Boolean = false,
        val medicineDailyData: List<DailyTakingMedicineList> = emptyList(),
        val notTakingReason: String? = null,
        val clickTime: String = "",
        val dailyMood: Data = Data(),
    ): UiState

    sealed class Event: UiEvent {
        data class WritingButtonClicked(val context: Context): Event()
        object showSosDialog: Event()
        //data class showSosDialog(val medicineTime: String): Event()
        object DismissDialog: Event()
        //object showMediDialog: Event()
        data class showMediDialog(val medicineTime: String): Event()
        object dismissMediDialog: Event()
        data class CallButtonClicked(val context: Context, val number: String):Event()
        data class SendNotTakingReason(val context: Context):Event()
        data class medicineSuccess(val medicineTime: String):Event()
        data class setNotTakingReason(val notTakingReason: String):Event()
    }
    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destinaton: String,
            val navOptions: NavOptions? = null,
            val builder: NavOptionsBuilder.() -> Unit = {}
        ): HomeContract.Effect()
        data class Toastmessage(val message: String): Effect()
        data class getCall(
            val context: Context,
            val number: String
        ): Effect()
    }


}