package com.example.remind.data.network.service

import com.example.remind.data.model.request.SetAcceptrequest
import com.example.remind.data.model.response.GetAcceptResponse
import com.example.remind.data.model.response.GetPatientResponse
import com.example.remind.data.model.response.MoodChartResponse
import com.example.remind.data.network.adapter.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DoctorService {
    @GET("/member/patients")
    suspend fun getPatientsList(
        @Query("status") status:String
    ): ApiResult<GetPatientResponse>

    @POST("/prescription/relation/accept")
    suspend fun setRequest(
        @Body body: SetAcceptrequest
    ): ApiResult<GetAcceptResponse>

    @GET("/mood/chart/connection")
    suspend fun getPatienceMoodChart(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int,
        @Query("size") size: Int,
        @Query("memberId") memberId: Int,
    ): ApiResult<MoodChartResponse>
}