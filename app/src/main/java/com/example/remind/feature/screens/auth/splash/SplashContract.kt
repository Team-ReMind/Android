package com.example.remind.feature.screens.auth.splash

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState

class SplashContract {
    data class State(
        val userType: String? = null
    ): UiState

    sealed class Event: UiEvent {
    }

    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null,
            val builder: NavOptionsBuilder.() -> Unit = {}
        ): Effect()
    }
}