package com.example.remind.feature.screens.auth.onboarding

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.example.remind.core.base.UiEffect
import com.example.remind.core.base.UiEvent
import com.example.remind.core.base.UiState
import com.example.remind.data.model.request.OnBoardingRequest

class OnBoardingContract {
    data class State(
        val selectedType: String? = null,
        val userName: String = "",
        val moveAble: Boolean = false,
        val fcmToken: String = "",
        val userInfo: OnBoardingRequest = OnBoardingRequest(),
        val errorMsg: Boolean = false
    ): UiState

    sealed class Event: UiEvent {
        data class DoctorButtonClicked(val context: Context): Event()
        data class CenterButtonClicked(val context: Context): Event()
        data class PatienceButtonClicked(val context: Context): Event()
        data class NextButtonFinalPatience(val number: String): Event()
        data class NextButtonFinalDoctor(val certifinumber: String): Event()
        data class NavigateButtonClicked(val destinationRoute: String, val currentRoute: String, val inclusive: Boolean): Event()
        data class StoreUserInfoButtonClicked(val info: OnBoardingRequest): Event()
        object ErrorMsg: Event()
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