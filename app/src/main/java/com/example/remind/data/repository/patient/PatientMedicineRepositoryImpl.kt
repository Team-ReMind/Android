package com.example.remind.data.repository.patient

import com.example.remind.data.model.request.SetMedicineInfoRequest
import com.example.remind.data.model.request.WritingMoodRequest
import com.example.remind.data.model.response.ActivityListResponse
import com.example.remind.data.model.response.GetMedicineRateResponse
import com.example.remind.data.model.response.GetMonthlyMedicineResponse
import com.example.remind.data.model.response.GetPrescriptionResponse
import com.example.remind.data.model.response.MedicingDailyInfoResponse
import com.example.remind.data.model.response.MoodChartResponse
import com.example.remind.data.model.response.SetMedicineInfoResponse
import com.example.remind.data.model.response.WritingMoodResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.service.PatientService
import javax.inject.Inject

class PatientMedicineRepositoryImpl @Inject constructor(
    private val service: PatientService
): PatientMedicineRepository {
    override suspend fun getMedicineDaily(
        memberId: Int,
        date: String
    ): ApiResult<MedicingDailyInfoResponse> {
        return service.getMedicineDailyInfo(memberId, date)
    }

    override suspend fun setMedicineInfo(body: SetMedicineInfoRequest): ApiResult<SetMedicineInfoResponse> {
        return service.setMedicineInfo(body)
    }

    override suspend fun getActivityInfo(): ApiResult<ActivityListResponse> {
        return service.getActivityList()
    }

    override suspend fun setTodayMood(body: WritingMoodRequest): ApiResult<WritingMoodResponse> {
        return service.setTodayMood(body)
    }

    override suspend fun getMoodChart(
        year: Int,
        month: Int,
        day: Int,
        size: Int
    ): ApiResult<MoodChartResponse> {
        return service.getMoodChart(year, month, day, size)
    }

    override suspend fun getPrescription(memberId: Int): ApiResult<GetPrescriptionResponse> {
        return service.getPrescription(memberId)
    }

    override suspend fun getMedicineRate(memberId: Int): ApiResult<GetMedicineRateResponse> {
       return service.getMedicineRate(memberId)
    }

    override suspend fun getMonthlyMedicine(
        memberId: Int,
        year: Int,
        month: Int
    ): ApiResult<GetMonthlyMedicineResponse> {
       return service.getMonthlyMedicine(memberId, year, month)
    }
}