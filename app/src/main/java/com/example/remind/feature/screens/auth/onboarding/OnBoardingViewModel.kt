package com.example.remind.feature.screens.auth.onboarding

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.network.interceptor.TokenManager
import com.example.remind.feature.screens.auth.login.LoginContract
import com.example.remind.feature.screens.auth.splash.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val tokenManager: TokenManager
): BaseViewModel<OnBoardingContract.Event, OnBoardingContract.State, OnBoardingContract.Effect>(
    initialState = OnBoardingContract.State()
) {
    override fun reduceState(event: OnBoardingContract.Event) {
        when(event) {
            is OnBoardingContract.Event.DoctorButtonClicked -> {
                saveUserType("Doctor")
                navigateToDoctor()
            }
            is OnBoardingContract.Event.CenterButtonClicked -> {
                saveUserType("Center")
                navigateToCenter()
            }
            else -> {
                saveUserType("Patience")
                navigateToPatience()
            }
        }
    }

    private fun saveUserType(userType: String) {
        viewModelScope.launch {
            runBlocking { tokenManager.saveUserType(userType) }
        }
    }

    fun navigateToDoctor() {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = Screens.Doctor.DoctorMain.route,
                navOptions = navOptions {
                    popUpTo(Screens.Register.SelectType.route) {
                        inclusive = true
                    }
                }
            ))
    }

    fun navigateToCenter() {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = Screens.Center.CenterMain.route,
                navOptions = navOptions {
                    popUpTo(Screens.Register.SelectType.route) {
                        inclusive = true
                    }
                }
            ))
    }

    fun navigateToPatience() {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = Screens.Patience.route,
                navOptions = navOptions {
                    popUpTo(Screens.Register.SelectType.route) {
                        inclusive = true
                    }
                }
            ))
    }
}