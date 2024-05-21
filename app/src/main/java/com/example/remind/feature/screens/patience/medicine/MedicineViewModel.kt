package com.example.remind.feature.screens.patience.medicine

import androidx.lifecycle.viewModelScope
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.domain.usecase.patience_usecase.GetMedicineRateUseCase
import com.example.remind.domain.usecase.patience_usecase.GetMonthlyMedicineUseCase
import com.example.remind.domain.usecase.patience_usecase.PrescriptionUseCase
import com.example.remind.feature.screens.patience.moodchart.MoodChartContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val prescriptionUseCase: PrescriptionUseCase,
    private val getMonthlyMedicineUseCase: GetMonthlyMedicineUseCase,
    private val getMedicineRateUseCase: GetMedicineRateUseCase
): BaseViewModel<MedicineContract.Event, MedicineContract.State, MedicineContract.Effect>(
    initialState = MedicineContract.State()
) {
    init {
        viewModelScope.launch {
            getPrescription()
            getMedicineRate()
        }
    }
    override fun reduceState(event: MedicineContract.Event) {
//        when(event) {
//
//        }
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

}