package com.example.remind.feature.screens.auth.onboarding

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.request.OnBoardingRequest
import com.example.remind.feature.screens.auth.login.LoginContract

class OnBoardingContract {
    data class State(
        val selectedType: String? = null,
        val moveAble: Boolean = false,
        val fcmToken: String = ""
    ): UiState

    sealed class Event: UiEvent {
        data class DoctorButtonClicked(val context: Context): Event()
        data class CenterButtonClicked(val context: Context): Event()
        data class PatienceButtonClicked(val context: Context): Event()
        data class NextButtonClicked(val context: Context): Event()
        data class NextButtonFinal(val onBoardingData: OnBoardingRequest): Event()
        data class NextButtonToPatience(val context: Context): Event()
    }

    sealed class Effect: UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null,
            val builder: NavOptionsBuilder.() -> Unit = {}
        ): Effect()
        data class Toastmessage(val message: String): Effect()
    }
}