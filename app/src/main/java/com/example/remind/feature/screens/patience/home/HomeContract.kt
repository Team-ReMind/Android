package com.example.remind.feature.screens.patience.home

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.response.DailyTakingMedicineList
import com.example.remind.data.model.response.MedicineData

class HomeContract {
    data class State(
        val sosDialogState: Boolean = false,
        val medicineDialogState:Boolean = false,
        val medicineDailyData: List<DailyTakingMedicineList> = emptyList(),
        val notTakingReason: String? = null
    ): UiState

    sealed class Event: UiEvent {
        data class WritingButtonClicked(val context: Context): Event()
        object showSosDialog: Event()
        object DismissDialog: Event()
        object showMediDialog: Event()
        object dismissMediDialog: Event()
        data class CallButtonClicked(val context: Context, val number: String):Event()
        data class SendNotTakingReason(val medicineTime: String,val date: String, val notTakingReason: String):Event()
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