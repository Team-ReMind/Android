package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.response.MedicingDailyInfoResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMedicineRepository
import javax.inject.Inject

class PatientMedicineDailyUseCase @Inject constructor(
    private val patientMedicineRepository: PatientMedicineRepository
) {
    suspend operator fun invoke(memberId: Int, date: String): ApiResult<MedicingDailyInfoResponse> {
        return patientMedicineRepository.getMedicineDaily(memberId, date)
    }
}