package com.example.remind.data.network.interceptor

import com.example.remind.data.model.request.TokenRequest
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.service.AuthService
import com.google.common.net.HttpHeaders.AUTHORIZATION
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

//class AuthAuthenticator @Inject constructor(
//    private val tokenManager: TokenManager,
//    //private val service: AuthService
//): Authenticator {
//    override fun authenticate(route: Route?, response: Response): Request? {
//        val refreshToken = runBlocking {
//            tokenManager.getRefreshToken().first()
//        }
//        val accessToken = runBlocking {
//            tokenManager.getAccessToken().first()
//        }
//        if(response.code == 401 || refreshToken == null) {
//            Timber.d("토큰 갱신해야함 401에러")
//            val newToken = runBlocking {
//                if (refreshToken != null) {
//                    getNewAccessToken(refreshToken)
//                }
//            }
//            if(newToken != null) {
//                return refreshToken?.let { newRequestWithToken(it, response.request) }
//            } else {
//                null
//            }
//        }
//        if(accessToken == null) {
//            response.close()
//            return null
//        }
//        return null
//    }
//    private fun newRequestWithToken(token: String, request: Request): Request =
//        request.newBuilder()
//            .header(AUTHORIZATION, token)
//            .build()
//
//    private suspend fun getNewAccessToken(refreshToken: String): String {
//        //val response = updateTokenuUseCase.invoke(TokenRequest(refreshToken))
//        val response = serviceProvider.get().updateToken(TokenRequest(refreshToken))
//        return when (response) {
//            is ApiResult.Success -> {
//                val newAccessToken = response.data.data.accessToken
//                val newRefreshToken = response.data.data.refreshToken
//                tokenManager.saveAccessToken(newAccessToken, newRefreshToken)
//                Timber.d("$newAccessToken &&& n$newRefreshToken")
//                newAccessToken
//            }
//            is ApiResult.Failure.HttpError -> {
//                Timber.d("http에러 발생 토큰 갱신 실패")
//                ""
//            }
//            is ApiResult.Failure.NetworkError -> {
//                Timber.d("NetworkError 발생 토큰 갱신 실패")
//                ""
//            }
//            is ApiResult.Failure.UnknownApiError -> {
//                Timber.d("UnknownApiError 발생 토큰 갱신 실패")
//                ""
//            }
//            else -> ""
//        }
//    }
//}