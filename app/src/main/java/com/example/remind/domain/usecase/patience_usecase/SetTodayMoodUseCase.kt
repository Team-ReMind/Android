package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.request.WritingMoodRequest
import com.example.remind.data.model.response.WritingMoodResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMedicineRepository
import javax.inject.Inject

class SetTodayMoodUseCase @Inject constructor(
    private val patientMedicineRepository: PatientMedicineRepository
) {
    suspend operator fun invoke(body: WritingMoodRequest): ApiResult<WritingMoodResponse> {
        return patientMedicineRepository.setTodayMood(body)
    }
}