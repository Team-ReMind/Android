package com.example.remind.feature.screens.auth.onboarding

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.model.request.OnBoardingRequest
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.interceptor.TokenManager
import com.example.remind.domain.usecase.onboarding_usecase.FcmTokenUseCase
import com.example.remind.domain.usecase.onboarding_usecase.OnBoardingUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onBoardingUserCase: OnBoardingUserCase,
    private val tokenUseCase: FcmTokenUseCase,
    private val tokenManager: TokenManager
): BaseViewModel<OnBoardingContract.Event, OnBoardingContract.State, OnBoardingContract.Effect>(
    initialState = OnBoardingContract.State()
) {
    init {
        viewModelScope.launch {
            val userName = tokenManager.getUserName().first()
            userName?.let { currentState.copy(userName = it) }?.let {
                updateState(
                    it
                )
            }
            getFcmToken()
        }
    }


    override fun reduceState(event: OnBoardingContract.Event) {
        when(event) {
            is OnBoardingContract.Event.DoctorButtonClicked -> {
                updateState(currentState.copy(selectedType = "ROLE_DOCTOR"))
                saveUserType("ROLE_DOCTOR")
            }
            is OnBoardingContract.Event.CenterButtonClicked -> {
                updateState(currentState.copy(selectedType = "ROLE_CENTER"))
                saveUserType("ROLE_CENTER")
            }
            is OnBoardingContract.Event.PatienceButtonClicked -> {
                updateState(currentState.copy(selectedType = "ROLE_PATIENT"))
                saveUserType("ROLE_PATIENT")
            }
            is OnBoardingContract.Event.NextButtonFinal -> {
                viewModelScope.launch {
                    postOnBoarding(event.onBoardingData.copy(fcmToken = currentState.fcmToken))
                    navigateToRoute(Screens.Register.OnBoardingFinal.route, Screens.Register.OnBoardingPatience.route, true)
                    Log.d("dklfawek", "${currentState.selectedType}")
                }
            }
            is OnBoardingContract.Event.NextButtonToPatience -> {
                //navigateToRoute(event.destinationRoute, event.currentRoute)
            }
            is OnBoardingContract.Event.NextButtonClicked -> {
                navigateToNext(currentState.selectedType!!)
            }
            //의사부터 이걸로 통합시킴
            is OnBoardingContract.Event.NavigateButtonClicked -> {
                navigateToRoute(event.destinationRoute, event.currentRoute, false)
            }
            else ->{}
        }
    }

    private fun saveUserType(userType: String) {
        viewModelScope.launch {
            runBlocking { tokenManager.saveUserType(userType) }
        }
    }

    fun navigateToNext(selectType: String) {
        if(selectType == "ROLE_DOCTOR") {
            navigateToRoute(Screens.Register.OnBoardingCheckDoctor.route, Screens.Register.SelectType.route, false)
        }
        if(selectType == "ROLE_PATIENT") {
            navigateToRoute(Screens.Register.OnBoardingPatience.route, Screens.Register.SelectType.route,false)
        }
        if(selectType == "ROLE_CENTER") {
            navigateToRoute(Screens.Register.OnBoardingCenter.route, Screens.Register.SelectType.route,false)
        }
    }

    fun navigateToRoute(destinationRoute: String, currentRoute: String, inclusiveData: Boolean) {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = destinationRoute,
                navOptions = navOptions {
                    popUpTo(currentRoute) {
                        inclusive = inclusiveData
                    }
                }
            )
        )
    }

    private fun getFcmToken() {
        viewModelScope.launch {
            val fcmToken = tokenUseCase.invoke()
            updateState(currentState.copy(fcmToken = fcmToken))
            Log.d("alwkejflakwe", "${fcmToken}")
        }
    }

    private suspend fun postOnBoarding(data: OnBoardingRequest) {
        viewModelScope.launch {
            val result = onBoardingUserCase.invoke(data)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(moveAble = true))
                    tokenManager.saveUserId(result.data.data.userId)
                }
                is ApiResult.Failure.UnknownApiError -> {
                    postEffect(OnBoardingContract.Effect.Toastmessage("리마인드 서버 관리자에게 문의하세요"))
                }
                is ApiResult.Failure.NetworkError -> {
                    postEffect(OnBoardingContract.Effect.Toastmessage("네트워크 설정을 확인해주세요"))
                }
                is ApiResult.Failure.HttpError -> {
                    postEffect(OnBoardingContract.Effect.Toastmessage("Http 오류가 발생했습니다"))
                }
                else -> {}
            }
        }
    }
}