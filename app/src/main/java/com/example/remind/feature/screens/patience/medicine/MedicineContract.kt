package com.example.remind.feature.screens.patience.medicine

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.response.Prescription
import com.example.remind.data.model.response.Rate

class MedicineContract {
    data class State(
        val prescription: Prescription = Prescription(),
        val alarmDialogState: Boolean = false,
        val rate: Rate = Rate()
    ): UiState

    sealed class Event: UiEvent {

    }
    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destinaton: String,
            val navOptions: NavOptions? = null,
            val builder: NavOptionsBuilder.() -> Unit = {}
        ): MedicineContract.Effect()
        data class Toastmessage(val message: String): MedicineContract.Effect()
    }
}