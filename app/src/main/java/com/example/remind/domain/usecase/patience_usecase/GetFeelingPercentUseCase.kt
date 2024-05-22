package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.response.GetFeelingPercentResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMoodChartRepository
import javax.inject.Inject

class GetFeelingPercentUseCase @Inject constructor(
    private val patientMoodChartRepository: PatientMoodChartRepository
) {
    suspend operator fun invoke(): ApiResult<GetFeelingPercentResponse> {
        return patientMoodChartRepository.getFeelingPercentChart()
    }
}