package com.example.remind.data.network.interceptor

import com.example.remind.BuildConfig
import com.example.remind.core.util.Constants.ACCESS_TOKEN
import com.example.remind.core.util.Constants.REFRESH_TOKEN
import com.example.remind.data.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

//class AuthInterceptor @Inject constructor(
//    private val dataStoreRepository: DataStoreRepository
//) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val accessToken = runBlocking { dataStoreRepository.getStringValue(ACCESS_TOKEN).first() }
//        val refreshToken = runBlocking { dataStoreRepository.getStringValue(REFRESH_TOKEN).first() }
//
//        val originalRequest = chain.request()
//        val authenticationRequest = originalRequest.newBuilder()
//            .addHeader("AUTHORIZATION", "Bearer $accessToken")
//            .build()
//        val response = chain.proceed(authenticationRequest)
//
//        when (response.code) {
//            401 -> {
//                val newToken = runBlocking { updateToken(refreshToken) }
//
//
//            }
//        }
//    }
//}


