package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.response.ActivityListResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMedicineRepository
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val patientMedicineRepository: PatientMedicineRepository
) {
    suspend operator fun invoke(): ApiResult<ActivityListResponse> {
        return patientMedicineRepository.getActivityInfo()
    }
}