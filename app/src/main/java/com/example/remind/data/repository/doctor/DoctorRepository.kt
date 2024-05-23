package com.example.remind.data.repository.doctor

import com.example.remind.data.model.request.SetAcceptrequest
import com.example.remind.data.model.response.GetAcceptResponse
import com.example.remind.data.model.response.GetPatientResponse
import com.example.remind.data.model.response.MoodChartResponse
import com.example.remind.data.network.adapter.ApiResult

interface DoctorRepository {
    suspend fun getPatientList(status: String): ApiResult<GetPatientResponse>
    suspend fun getRequest(body: SetAcceptrequest): ApiResult<GetAcceptResponse>
    suspend fun getMoodChart(year: Int, month: Int, day: Int, size: Int, memberId: Int): ApiResult<MoodChartResponse>
}