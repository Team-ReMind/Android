package com.example.remind.domain.usecase.doctor_usecase

import com.example.remind.data.model.response.MoodChartResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.doctor.DoctorRepository
import javax.inject.Inject

class GetDoctorMoodChartUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(
        year: Int,
        month: Int,
        day: Int,
        size: Int,
        memberId: Int
    ): ApiResult<MoodChartResponse> {
        return doctorRepository.getMoodChart(year, month, day, size, memberId)
    }
}