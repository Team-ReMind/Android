package com.example.remind.feature.screens.auth.onboarding

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.model.request.OnBoardingRequest
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.interceptor.TokenManager
import com.example.remind.domain.usecase.OnBoardingUserCase
import com.example.remind.feature.screens.auth.login.LoginContract
import com.example.remind.feature.screens.auth.splash.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onBoardingUserCase: OnBoardingUserCase,
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
            is OnBoardingContract.Event.NextButtonFinal -> {
                postOnBoarding(event.onBoardingData)
                if(currentState.moveAble == true) navigateToFinal()
            }
            is OnBoardingContract.Event.NextButtonToPatience -> {
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
    fun navigateToFinal() {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = Screens.Register.OnBoardingFinal.route,
                navOptions = navOptions {

                }
            ))
    }
    fun navigateToPatience() {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = Screens.Patience.route,
                navOptions = navOptions {
                    popUpTo(Screens.Register.OnBoardingFinal.route) {
                        inclusive = true
                    }
                }
            ))
    }

    private fun postOnBoarding(data: OnBoardingRequest) {
        viewModelScope.launch {
            val result = onBoardingUserCase.invoke(data)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(moveAble = true))
                }
                is ApiResult.Failure.UnknownApiError -> {
                    postEffect(OnBoardingContract.Effect.Toastmessage("리마인드 서버 관리자에게 문의하세요"))
                }
                is ApiResult.Failure.NetworkError -> {
                    postEffect(OnBoardingContract.Effect.Toastmessage("네트워크 설정을 확인해주세요"))
                }
                is ApiResult.Failure.HttpError -> {
                    //나중에 지워야함!!!!!!!!!!!!!!!!!!!!!!!!!!!예비로 넣어둠
                    updateState(currentState.copy(moveAble = true))
                    postEffect(OnBoardingContract.Effect.Toastmessage("Http 오류가 발생했습니다"))
                }
            }
        }
    }
}