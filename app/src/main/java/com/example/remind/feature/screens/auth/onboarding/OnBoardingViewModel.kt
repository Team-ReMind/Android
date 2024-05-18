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
        }
    }


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
            is OnBoardingContract.Event.NextButtonFinal -> {
                getFcmToken()
                postOnBoarding(event.onBoardingData.copy(fcmToken = currentState.fcmToken))
                if(currentState.moveAble == true) navigateToFinal()
            }
            is OnBoardingContract.Event.NextButtonToPatience -> {
                //navigateToRoute(event.destinationRoute, event.currentRoute)
            }
            is OnBoardingContract.Event.NextButtonClicked -> {
                saveUserType(currentState.selectedType!!)
                navigateToNext(currentState.selectedType!!)
            }
            //의사부터 이걸로 통합시킴
            is OnBoardingContract.Event.NavigateButtonClicked -> {
                navigateToRoute(event.destinationRoute, event.currentRoute)
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
    fun navigateToFinal() {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = Screens.Register.OnBoardingFinal.route,
                navOptions = navOptions {
                    popUpTo(Screens.Register.OnBoardingPatience.route) {
                        inclusive = true
                    }
                }
            ))
    }
//    fun navigateToPatience() {
//        postEffect(
//            OnBoardingContract.Effect.NavigateTo(
//                destination = Screens.Patience.route,
//                navOptions = navOptions {
//                    popUpTo(Screens.Register.OnBoardingFinal.route) {
//                        inclusive = true
//                    }
//                }
//            ))
//    }


    //나중에 이걸로 통합 수정하기
    fun navigateToRoute(destinationRoute: String, currentRoute: String) {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = destinationRoute,
                navOptions = navOptions {
                    popUpTo(currentRoute) {
                        inclusive = true
                    }
                }
            )
        )
    }

    private fun getFcmToken() {
        viewModelScope.launch {
            val fcmToken = tokenUseCase.invoke()
            updateState(currentState.copy(fcmToken = fcmToken))
        }
    }

    private fun postOnBoarding(data: OnBoardingRequest) {
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