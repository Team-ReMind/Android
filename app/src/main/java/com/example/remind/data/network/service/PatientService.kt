package com.example.remind.data.network.service

import com.example.remind.data.model.request.SetMedicineInfoRequest
import com.example.remind.data.model.response.MedicineInfoResponse
import com.example.remind.data.model.response.MedicingDailyInfoResponse
import com.example.remind.data.model.response.SetMedicineInfoResponse
import com.example.remind.data.network.adapter.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PatientService {
    @GET("/taking-medicine")
    suspend fun getMedicineInfo(
        @Path("memberId") memberId:Int,
        @Path("date") string:Int,
    ): ApiResult<MedicineInfoResponse>

    @GET("/taking-medicine/daily")
    suspend fun getMedicineDailyInfo(
        @Query("memberId") memberId:Int,
        @Query("date") string:String,
    ): ApiResult<MedicingDailyInfoResponse>

    @POST("//taking-medicine")
    suspend fun setMedicineInfo(
        @Body body: SetMedicineInfoRequest
    ): ApiResult<SetMedicineInfoResponse>
}