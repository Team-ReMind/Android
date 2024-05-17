package com.example.remind.feature.screens.patience.writing

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState

class WritingContract {
    data class State(
        val selectFeelingType: String? = null,
    ): UiState

    sealed class Event: UiEvent {
        data class FeelingButtonClicked(val feelingType: String): Event()
        data class NextButtonClicked(val destinationRoute: String, val currentRoute: String): Event()
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