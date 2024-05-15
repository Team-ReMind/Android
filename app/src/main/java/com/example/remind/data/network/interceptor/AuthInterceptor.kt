package com.example.remind.data.network.interceptor

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.remind.BuildConfig
import com.example.remind.core.util.Constants.ACCESS_TOKEN
import com.example.remind.core.util.Constants.REFRESH_TOKEN
import com.example.remind.data.model.request.TokenRequest
import com.example.remind.data.model.response.TokenResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.service.AuthService
import com.google.android.gms.common.ConnectionResult.NETWORK_ERROR
import com.google.common.net.HttpHeaders.AUTHORIZATION
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
    @ApplicationContext private val context: Context
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { tokenManager.getAccessToken().first() } ?: ""
        val refreshToken = runBlocking { tokenManager.getRefreshToken().first() } ?: ""
        val originalRequest = chain.request()
        val authenticationRequest = accessToken?.let {
            originalRequest.newBuilder()
                .addHeader(AUTHORIZATION, "Bearer $accessToken")
                .build()
        }
        val response = authenticationRequest?.let { chain.proceed(it) }
        if(response?.code == 401 && refreshToken.isNotEmpty()) {
            Timber.d("들어옴")
            val newToken = runBlocking { updateToken(accessToken,refreshToken, context) }
            if(newToken is ApiResult.Success) {
                val newAccessToken = newToken.data.data.accessToken
                val refreshToken = newToken.data.data.refreshToken
                runBlocking { tokenManager.saveAccessToken(newAccessToken,refreshToken ) }
                response.close()
                val newAuthenticationRequest = originalRequest.newBuilder()
                    .header(AUTHORIZATION, "Bearer $newAccessToken")
                    .build()
                return chain.proceed(newAuthenticationRequest)
            } else {
                runBlocking { tokenManager.deleteAccessToken() }
            }
        }
        return response!!
    }
}

private suspend fun updateToken(
    accessToken: String,
    refreshToken: String,
    context: Context
): ApiResult<TokenResponse> {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient(accessToken, context))
        .build()
    val service = retrofit.create(AuthService::class.java)

    return service.updateToken(TokenRequest(refreshToken))
}

private fun createOkHttpClient(
    refreshToken: String,
    context: Context
): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
        )
        .addNetworkInterceptor(ChuckerInterceptor(context))
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header(AUTHORIZATION, "Bearer $refreshToken")
                .build()

            chain.proceed(modifiedRequest)
        }
        .build()
}
