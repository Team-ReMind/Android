package com.example.remind.data.repository.patient

import com.example.remind.data.model.response.GetDailyMoodResponse
import com.example.remind.data.model.response.GetFeelingActivityResponse
import com.example.remind.data.model.response.GetFeelingPercentResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.service.PatientService
import javax.inject.Inject

class PatientMoodChartRepositoryImpl @Inject constructor(
    private val service: PatientService
):PatientMoodChartRepository {
    override suspend fun getFeelingPercentChart(): ApiResult<GetFeelingPercentResponse> {
        return service.getFeelingPercentChart()
    }

    override suspend fun getFeelingTypeActivity(feelingType: String): ApiResult<GetFeelingActivityResponse> {
        return service.getFeelingTypeActivity(feelingType)
    }

    override suspend fun getMoodDaily(moodDate: String): ApiResult<GetDailyMoodResponse> {
        return service.getDailyMood(moodDate)
    }
}