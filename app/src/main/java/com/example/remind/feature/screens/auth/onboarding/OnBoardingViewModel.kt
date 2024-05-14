package com.example.remind.feature.screens.auth.onboarding

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.network.interceptor.TokenManager
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
                updateState(currentState.copy("ROLE_DOCTOR"))
            }
            is OnBoardingContract.Event.CenterButtonClicked -> {
                updateState(currentState.copy("ROLE_CENTER"))
            }
            is OnBoardingContract.Event.PatienceButtonClicked -> {
                updateState(currentState.copy("ROLE_PATIENT"))
            }
            is OnBoardingContract.Event.NextButtonPatienceMain -> {
                navigateToPatience()
            }
            else -> {
                saveUserType(currentState.selectedType!!)
                navigateToNext(currentState.selectedType!!)
            }
        }
    }

    private fun saveUserType(userType: String) {
        viewModelScope.launch {
            runBlocking { tokenManager.saveUserType(userType) }
        }
    }

    fun navigateToNext(selectType: String) {
        if(selectType == "ROLE_DOCTOR") {
            postEffect(
                OnBoardingContract.Effect.NavigateTo(
                    destination = Screens.Register.OnBoardingCheckDoctor.route,
                    navOptions = navOptions {
                        popUpTo(Screens.Register.SelectType.route) {
                            inclusive = true
                        }
                    }
                ))
        }
        if(selectType == "ROLE_PATIENT") {
            postEffect(
                OnBoardingContract.Effect.NavigateTo(
                    destination = Screens.Register.OnBoardingPatience.route,
                    navOptions = navOptions {
                        popUpTo(Screens.Register.SelectType.route) {
                            inclusive = true
                        }
                    }
                ))
        }
        if(selectType == "ROLE_CENTER") {
            postEffect(
                OnBoardingContract.Effect.NavigateTo(
                    destination = Screens.Register.OnBoardingCenter.route,
                    navOptions = navOptions {
                        popUpTo(Screens.Register.SelectType.route) {
                            inclusive = true
                        }
                    }
                ))
        }
    }
    fun navigateToPatience() {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = Screens.Register.OnBoardingFinal.route,
                navOptions = navOptions {
                    popUpTo(Screens.Register.SelectType.route) {
                        inclusive = true
                    }
                }
            ))
    }
}