package com.example.remind.data.repository.patient

import com.example.remind.data.model.request.SetMedicineInfoRequest
import com.example.remind.data.model.request.WritingMoodRequest
import com.example.remind.data.model.response.ActivityListResponse
import com.example.remind.data.model.response.MedicingDailyInfoResponse
import com.example.remind.data.model.response.MoodChartResponse
import com.example.remind.data.model.response.OnBoardingResponse
import com.example.remind.data.model.response.SetMedicineInfoResponse
import com.example.remind.data.model.response.WritingMoodResponse
import com.example.remind.data.network.adapter.ApiResult

interface PatientMedicineRepository {
    suspend fun getMedicineDaily(memberId: Int, date:String): ApiResult<MedicingDailyInfoResponse>
    suspend fun setMedicineInfo(body: SetMedicineInfoRequest): ApiResult<SetMedicineInfoResponse>
    suspend fun getActivityInfo():  ApiResult<ActivityListResponse>
    suspend fun setTodayMood(body: WritingMoodRequest): ApiResult<WritingMoodResponse>
    suspend fun getMoodChart(year: Int, month: Int, day: Int, size: Int): ApiResult<MoodChartResponse>
}