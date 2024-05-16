package com.example.remind.feature.screens.auth.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.model.request.KakaoLoginRequest
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.network.interceptor.TokenManager
import com.example.remind.domain.usecase.onboarding_usecase.KakaoTokenUseCase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: KakaoTokenUseCase,
    private val tokenManager: TokenManager
): BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>(
    initialState = LoginContract.State()
) {

    override fun reduceState(event: LoginContract.Event) {
        viewModelScope.launch {
            when(event) {
                is LoginContract.Event.KakaoLoginButtonClicked -> {
                    KakaoLogin(event.context)
                }
                else->{}
            }
        }
    }
    private fun KakaoLogin(context : Context) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if(error != null) {
                Log.e("kakao", "카카오 로그인 실패")
            } else if(token != null) {
                UserApiClient.instance.me { user, error ->
                    Timber.tag("kakao").e(token.accessToken + " && " + user)
                    viewModelScope.launch {
                        socialLogin(token.accessToken)
                    }
                }
            }
        }
        if(UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if(error != null) {
                    Log.e("kakao", "카카오톡으로 로그인 실패")
                    if(error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if(token != null) {
                    Log.e("kakao", "카카오톡으로 로그인 성공")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }


private fun socialLogin(token: String) {
    viewModelScope.launch {
        val result = authUseCase.invoke(KakaoLoginRequest(token))
        when(result) {
            is ApiResult.Success -> {
                runBlocking { tokenManager.saveAccessToken(
                    result.data.data.accessToken,
                    result.data.data.refreshToken
                    )
                    tokenManager.saveUserName(result.data.data.name)
                }
                postEffect(
                    LoginContract.Effect.NavigateTo(
                        destinaton = Screens.Register.SelectType.route,
                        navOptions = navOptions {
                            popUpTo(
                                Screens.Register.Login.route
                            )
                        }
                    )
                )
                Timber.d("${tokenManager.getAccessToken()} ||| ${tokenManager.getRefreshToken()}")
            }
            is ApiResult.Failure.UnknownApiError -> {
                postEffect(LoginContract.Effect.Toastmessage("리마인드 서버 관리자에게 문의하세요"))
            }
            is ApiResult.Failure.NetworkError -> {
                postEffect(LoginContract.Effect.Toastmessage("네트워크 설정을 확인해주세요"))
            }
            is ApiResult.Failure.HttpError -> {
                postEffect(LoginContract.Effect.Toastmessage("Http 오류가 발생했습니다"))
            }
            else -> {}
        }
    }
}


}