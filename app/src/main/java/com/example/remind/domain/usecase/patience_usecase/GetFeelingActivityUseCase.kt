package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.response.GetFeelingActivityResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMoodChartRepository
import javax.inject.Inject

class GetFeelingActivityUseCase @Inject constructor(
    private val patientMoodChartRepository: PatientMoodChartRepository
) {
    suspend operator fun invoke(feelingType: String): ApiResult<GetFeelingActivityResponse> {
        return patientMoodChartRepository.getFeelingTypeActivity(feelingType)
    }
}