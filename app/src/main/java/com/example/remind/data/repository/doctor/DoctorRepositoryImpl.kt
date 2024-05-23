package com.example.remind.data.repository.doctor

import com.example.remind.data.model.request.SetAcceptrequest
import com.example.remind.data.model.response.GetAcceptResponse
import com.example.remind.data.model.response.GetPatientResponse
import com.example.remind.data.model.response.MoodChartResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.service.DoctorService
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val service: DoctorService
):DoctorRepository {
    override suspend fun getPatientList(status: String): ApiResult<GetPatientResponse> {
        return service.getPatientsList(status)
    }

    override suspend fun getRequest(body: SetAcceptrequest): ApiResult<GetAcceptResponse> {
        return service.setRequest(body)
    }

    override suspend fun getMoodChart(
        year: Int,
        month: Int,
        day: Int,
        size: Int,
        memberId: Int
    ): ApiResult<MoodChartResponse> {
        return service.getPatienceMoodChart(year, month, day, size, memberId)
    }

}