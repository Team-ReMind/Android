package com.example.remind.feature.screens.doctor

import android.content.Context
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState

class DoctorContract {
    data class State (
        val isLoading: Boolean = false
    ): UiState

    sealed class Event: UiEvent {
        data class RegisterButtonClicked(val context: Context): Event()
    }

    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ): Effect()
    }
}