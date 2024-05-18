package com.example.remind.feature.screens.patience.home

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState

class HomeContract {
    data class State(
        val sosDialogState: Boolean = false
    ): UiState

    sealed class Event: UiEvent {
        data class WritingButtonClicked(val context: Context): Event()
        object showSosDialog: Event()
        object DismissDialog: Event()
        data class CallButtonClicked(val context: Context, val number: String):Event()
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