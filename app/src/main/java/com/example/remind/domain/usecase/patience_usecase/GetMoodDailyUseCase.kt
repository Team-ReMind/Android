package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.response.GetDailyMoodResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMoodChartRepository
import javax.inject.Inject

class GetMoodDailyUseCase @Inject constructor(
    private val patientMoodChartRepository: PatientMoodChartRepository
) {
    suspend operator fun invoke(moodDate: String): ApiResult<GetDailyMoodResponse> {
        return patientMoodChartRepository.getMoodDaily(moodDate)
    }
}