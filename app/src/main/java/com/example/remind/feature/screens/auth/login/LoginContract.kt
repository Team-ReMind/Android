package com.example.remind.feature.screens.auth.login

import android.content.Context
import androidx.navigation.NavOptions
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState

class LoginContract {
    data class State(
        val isLoading: Boolean = false
    ): UiState

    sealed class Event: UiEvent {
        data class KakaoLoginButtonClicked(val context: Context): Event()
    }

    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destinaton: String,
            val navOptions: NavOptions? = null
        ): Effect()
    }
}