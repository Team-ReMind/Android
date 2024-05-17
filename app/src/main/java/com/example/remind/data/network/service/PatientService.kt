package com.example.remind.data.network.service

import com.example.remind.data.model.response.MedicineInfoResponse
import com.example.remind.data.network.adapter.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path

interface PatientService {
    @GET("/taking-medicine")
    suspend fun getMedicineInfo(
        @Path("memberId") memberId:Int,
        @Path("date") string:Int,
    ): ApiResult<MedicineInfoResponse>
}