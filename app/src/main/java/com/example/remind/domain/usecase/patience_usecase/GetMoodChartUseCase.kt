package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.response.MoodChartResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMedicineRepository
import javax.inject.Inject

class GetMoodChartUseCase @Inject constructor(
    private val patientMedicineRepository: PatientMedicineRepository
) {
    suspend operator fun invoke(
        year: Int,
        month: Int,
        day: Int,
        size: Int
    ): ApiResult<MoodChartResponse> {
        return patientMedicineRepository.getMoodChart(year, month, day, size)
    }
}