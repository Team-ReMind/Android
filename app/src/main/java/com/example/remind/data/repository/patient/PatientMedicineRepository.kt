package com.example.remind.data.repository.patient

import com.example.remind.data.model.response.MedicingDailyInfoResponse
import com.example.remind.data.network.adapter.ApiResult

interface PatientMedicineRepository {
    suspend fun getMedicineDaily(memberId: Int, date:String): ApiResult<MedicingDailyInfoResponse>
}