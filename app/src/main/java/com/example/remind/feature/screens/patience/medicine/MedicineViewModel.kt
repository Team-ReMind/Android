package com.example.remind.feature.screens.patience.medicine

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.interceptor.TokenManager
import com.example.remind.domain.usecase.patience_usecase.GetMedicineRateUseCase
import com.example.remind.domain.usecase.patience_usecase.GetMonthlyMedicineUseCase
import com.example.remind.domain.usecase.patience_usecase.PrescriptionUseCase
import com.example.remind.feature.screens.patience.home.HomeContract
import com.example.remind.feature.screens.patience.moodchart.MoodChartContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val prescriptionUseCase: PrescriptionUseCase,
    private val getMonthlyMedicineUseCase: GetMonthlyMedicineUseCase,
    private val getMedicineRateUseCase: GetMedicineRateUseCase,
    private val tokenManager: TokenManager
): BaseViewModel<MedicineContract.Event, MedicineContract.State, MedicineContract.Effect>(
    initialState = MedicineContract.State()
) {
    init {
        viewModelScope.launch {
            val userName = tokenManager.getUserName().first()
            updateState(currentState.copy(userName = userName ?: "김말랑"))
            getPrescription()
            getMedicineRate()
            getMonthMedicine()
        }
    }
    override fun reduceState(event: MedicineContract.Event) {
        when(event) {
            is MedicineContract.Event.moveToButtonClicked -> {
                navigateToRoute(
                    destination = Screens.Patience.Medicine.AlarmSetting.route,
                    current = Screens.Patience.Medicine.route,
                    inclusiveData = false
                )
            }
            is MedicineContract.Event.popDialog -> {
                updateState(currentState.copy(alarmDialogState = true))
            }
            is MedicineContract.Event.dismissDialog -> {
                updateState(currentState.copy(alarmDialogState = false))
            }
        }
    }


    private fun getPrescription() {
        viewModelScope.launch {
            val result = prescriptionUseCase.invoke(0)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(
                        prescription = result.data.data
                    ))
                }
                else ->{}
            }
        }
    }
    private fun getMedicineRate() {
        viewModelScope.launch {
            val result = getMedicineRateUseCase.invoke(0)
            when(result){
                is ApiResult.Success -> {
                    updateState(currentState.copy(
                        rate = result.data.data
                    ))
                }
                else ->{}
            }
        }
    }

    private fun getMonthMedicine() {
        viewModelScope.launch {
            val result = getMonthlyMedicineUseCase.invoke(0, 2024, 5)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(
                        getMonthlyMedicineResponse = result.data
                    ))
                }
                else -> {}
            }
        }
    }

    fun navigateToRoute(destination: String, current: String, inclusiveData: Boolean) {
        postEffect(
            MedicineContract.Effect.NavigateTo(
                destinaton = destination,
                navOptions = navOptions {
                    popUpTo(current) {
                        inclusive = inclusiveData
                    }
                }
            )
        )
    }

}