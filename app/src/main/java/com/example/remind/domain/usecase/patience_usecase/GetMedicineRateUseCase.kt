package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.response.GetMedicineRateResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMedicineRepository
import javax.inject.Inject

class GetMedicineRateUseCase @Inject constructor(
    private val patientMedicineRepository: PatientMedicineRepository
) {
    suspend operator fun invoke(memberId: Int): ApiResult<GetMedicineRateResponse> {
        return patientMedicineRepository.getMedicineRate(memberId)
    }
}