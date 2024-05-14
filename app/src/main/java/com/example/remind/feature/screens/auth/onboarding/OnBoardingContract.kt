package com.example.remind.feature.screens.auth.onboarding

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState

class OnBoardingContract {
    data class State(
        val blah: String? = null
    ): UiState

    sealed class Event: UiEvent {
        data class DoctorButtonClicked(val context: Context): Event()
        data class CenterButtonClicked(val context: Context): Event()
        data class PatienceButtonClicked(val context: Context): Event()
    }

    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null,
            val builder: NavOptionsBuilder.() -> Unit = {}
        ): Effect()
    }
}