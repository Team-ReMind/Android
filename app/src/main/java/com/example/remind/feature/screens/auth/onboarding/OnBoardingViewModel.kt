package com.example.remind.feature.screens.auth.onboarding

import android.util.Log
import androidx.core.text.isDigitsOnly
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
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
            //환자가 마지막 끝나는 화면으로 가는 기능
            is OnBoardingContract.Event.NextButtonFinalPatience -> {
                viewModelScope.launch {
                    updateState(currentState.copy(
                        userInfo = currentState.userInfo.copy(
                            fcmToken = currentState.fcmToken,
                            protectorPhoneNumber = event.number,
                            doctorLicenseNumber = "",
                            district = "",
                            city = "",
                            centerName = ""
                        )
                    ))
                    postOnBoarding(currentState.userInfo)
                    navigateToRoute(Screens.Register.OnBoardingFinal.route, Screens.Register.OnBoardingPatience.route, true)
                }
            }
            //의사가 마지막 끝나는 화면으로 가는 기능
            is OnBoardingContract.Event.NextButtonFinalDoctor -> {
                viewModelScope.launch {
                    updateState(currentState.copy(
                        userInfo = currentState.userInfo.copy(
                            fcmToken = currentState.fcmToken,
                            doctorLicenseNumber = event.certifinumber,
                            protectorPhoneNumber = "",
                            district = "",
                            city = "",
                            centerName = ""
                        )
                    ))
                    postOnBoarding(currentState.userInfo)
                    navigateToRoute(Screens.Register.OnBoardingLoadingDoctor.route, Screens.Register.OnBoardingCheckDoctor.route, true)
                }
            }
            //사용자 정보 작성하고 각자 입장의 온보딩으로 이동
            is OnBoardingContract.Event.StoreUserInfoButtonClicked -> {
                updateState(currentState.copy(
                    userInfo = currentState.userInfo.copy(
                        rolesType = currentState.selectedType!!,
                        name = event.name,
                        gender = event.gender,
                        phoneNumber = event.phoneNumber,
                        birthday = event.birthday,
                        hospitalName = event.hospitalName
                    )
                ))
                navigateToNext(currentState.selectedType!!)
            }
            is OnBoardingContract.Event.ErrorMsg -> {
                postEffect(OnBoardingContract.Effect.Toastmessage("생년월일을 형식에 맞게 입력해주세요."))
            }
            //입장 상관없이 공통의 경로를 명시한
            is OnBoardingContract.Event.NavigateButtonClicked -> {
                navigateToRoute(event.destinationRoute, event.currentRoute, event.inclusive)
            }
            else ->{}
        }
    }

    fun isValidDate(date: String): Boolean {
        if (date.length != 8 || !date.isDigitsOnly()) return false

        val year = date.substring(0, 4).toIntOrNull() ?: return false
        val month = date.substring(4, 6).toIntOrNull() ?: return false
        val day = date.substring(6, 8).toIntOrNull() ?: return false

        if (year !in 1000..9999 || month !in 1..12) return false

        return try {
            val dateString = "$year-${"%02d".format(month)}-${"%02d".format(day)}"
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            LocalDate.parse(dateString, formatter)
            true
        } catch (e: DateTimeParseException) {
            false
        }
    }

    private fun saveUserType(userType: String) {
        viewModelScope.launch {
            runBlocking { tokenManager.saveUserType(userType) }
        }
    }

    fun navigateToNext(selectType: String) {
        if(selectType == "ROLE_DOCTOR") {
            navigateToRoute(Screens.Register.OnBoardingCheckDoctor.route, Screens.Register.OnBoardingUserInfo.route, false)
        }
        if(selectType == "ROLE_PATIENT") {
            navigateToRoute(Screens.Register.OnBoardingPatience.route, Screens.Register.OnBoardingUserInfo.route,false)
        }
        if(selectType == "ROLE_CENTER") {
            navigateToRoute(Screens.Register.OnBoardingFinal.route, Screens.Register.OnBoardingUserInfo.route,false)
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
    //로딩창 이후 final 화면으로 이동 위함
    fun navigateToFinal() {
        postEffect(
            OnBoardingContract.Effect.NavigateTo(
                destination = Screens.Register.OnBoardingFinal.route,
                navOptions = navOptions {
                    popUpTo(Screens.Register.OnBoardingLoadingDoctor.route) {
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