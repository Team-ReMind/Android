package com.example.remind.feature.viewmodel.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.model.request.KakaoLoginRequest
import com.example.remind.data.network.adapter.onSuccess
import com.example.remind.domain.usecase.KakaoTokenUseCase
import com.example.remind.feature.contract.LoginContract
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: KakaoTokenUseCase
): BaseViewModel<LoginContract.Event,LoginContract.State, LoginContract.Effect>(
    initialState = LoginContract.State()
) {

    override fun reduceState(event: LoginContract.Event) {
        viewModelScope.launch {
            when(event) {
                is LoginContract.Event.KakaoLoginButtonClicked -> {
                    KakaoLogin(event.context)
                }
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
            authUseCase.invoke(KakaoLoginRequest(token))
                .onSuccess {
                    postEffect(LoginContract.Effect.NavigateTo(
                        destinaton = Screens.Register.SelectType.route,
                        navOptions = navOptions {
                            popUpTo(Screens.Register.Login.route) {
                                inclusive = true
                            }
                        }
                    ))
                }
        }
    }

}