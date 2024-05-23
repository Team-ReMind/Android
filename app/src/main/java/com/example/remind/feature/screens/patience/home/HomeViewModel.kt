package com.example.remind.feature.screens.patience.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.model.request.SetMedicineInfoRequest
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.domain.usecase.patience_usecase.GetMoodDailyUseCase
import com.example.remind.domain.usecase.patience_usecase.PatientMedicineDailyUseCase
import com.example.remind.domain.usecase.patience_usecase.SetMedicineInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val patientMedicineDailyUseCase: PatientMedicineDailyUseCase,
    private val setMedicineInfoUseCase: SetMedicineInfoUseCase,
    private val getMoodDailyUseCase: GetMoodDailyUseCase
): BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>(
    initialState = HomeContract.State()
) {
    var year = LocalDate.now().year
    var month = String.format("%02d", LocalDate.now().monthValue)
    var date = LocalDate.now().dayOfMonth
    init {
        viewModelScope.launch {
            getMedicineDaily(0,"${year}-${month}-${date}")
            getDailyMood("${year}-${month}-${date}")
        }
    }
    override fun reduceState(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.WritingButtonClicked -> {
                navigateToRoute(
                    destination = Screens.Patience.Home.WritingMoodStep1.route,
                    current = Screens.Patience.Home.route,
                    inclusiveData = false
                )
            }
            is HomeContract.Event.showSosDialog -> {
                //updateState(currentState.copy(clickTime = event.medicineTime))
                updateState(currentState.copy(sosDialogState = true))
            }
            is HomeContract.Event.DismissDialog -> {
                updateState(currentState.copy(sosDialogState = false))
            }
            is HomeContract.Event.showMediDialog -> {
                updateState(currentState.copy(clickTime = event.medicineTime))
                updateState(currentState.copy(medicineDialogState = true))
            }
            is HomeContract.Event.dismissMediDialog -> {
                updateState(currentState.copy(medicineDialogState = false))
            }
            is HomeContract.Event.CallButtonClicked -> {
                setSosCall(event.context, event.number)
            }
            is HomeContract.Event.SendNotTakingReason -> {
                sendNotTakingReason(currentState.clickTime, currentState.notTakingReason ?: "")
                updateState(currentState.copy(medicineDialogState = false))
            }
            //미복용 사유 클릭
            is HomeContract.Event.setNotTakingReason -> {
                updateState(currentState.copy(notTakingReason = event.notTakingReason))
            }
            //복용눌렀을때
            is HomeContract.Event.medicineSuccess -> {
                sendTakingReason(event.medicineTime)
                navigateToRoute(
                    destination = Screens.Patience.Home.SplashCheering.route,
                    current = Screens.Patience.Home.route,
                    inclusiveData = true
                )
            }
        }
    }
    fun navigateToHome() {
        postEffect(
            HomeContract.Effect.NavigateTo(
            destinaton = Screens.Patience.Home.route,
            navOptions = navOptions {
                popUpTo(Screens.Patience.Home.SplashCheering.route) {
                    inclusive = true
                }
            }
        ))
    }
    fun navigateToRoute(destination: String, current: String, inclusiveData: Boolean) {
        postEffect(
            HomeContract.Effect.NavigateTo(
                destinaton = destination,
                navOptions = navOptions {
                    popUpTo(current) {
                        inclusive = inclusiveData
                    }
                }
            )
        )
    }

    fun setSosCall( context: Context,  number:String) {
        postEffect(
            HomeContract.Effect.getCall(context, number)
        )
    }

    fun getMedicineDaily(memberId: Int, date: String) {
        viewModelScope.launch {
            val result = patientMedicineDailyUseCase.invoke(memberId, date)
            when(result) {
                is ApiResult.Success -> {
                    val dailyTakingMedicineDtos = result.data?.data?.dailyTakingMedicineDtos ?: emptyList()
                    updateState(currentState.copy(medicineDailyData = dailyTakingMedicineDtos))
                }
                is ApiResult.Failure.UnknownApiError -> {
                    setToastMessage("리마인드 고객센터에 문의하세요")
                }
                is ApiResult.Failure.NetworkError -> {
                    setToastMessage("네트워크 설정을 확인해주세요")
                }
                is ApiResult.Failure.HttpError -> {
                    setToastMessage("api 응답에러 ${result.code}")
                }
            }
        }
    }

    fun sendNotTakingReason(medicineTime: String, notTakingReason: String) {
        viewModelScope.launch {
            val result = setMedicineInfoUseCase.invoke(SetMedicineInfoRequest(
                isTaking = false,
                medicinesType = medicineTime,
                notTakingReason = notTakingReason
            ))
            when(result) {
                is ApiResult.Success -> {
                    Log.d("HomeViewModel", "success")
                }
                else -> {}
            }
        }
    }
    fun sendTakingReason(medicineTime: String) {
        viewModelScope.launch {
            val result = setMedicineInfoUseCase.invoke(SetMedicineInfoRequest(
                isTaking = true,
                medicinesType = medicineTime,
                notTakingReason = ""
            ))
            when(result) {
                is ApiResult.Success -> {
                    Log.d("HomeViewModel", "success")
                }
                else -> {}
            }
        }
    }

     fun getDailyMood(moodDate: String) {
        viewModelScope.launch {
            val result = getMoodDailyUseCase.invoke(moodDate)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(
                        dailyMood = result.data.data
                    ))
                }
                else -> {}
            }
        }
    }

    fun setToastMessage(text: String) {
        postEffect(HomeContract.Effect.Toastmessage(text))
    }
}