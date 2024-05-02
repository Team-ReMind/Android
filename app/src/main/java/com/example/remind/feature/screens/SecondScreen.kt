package com.example.remind.feature.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

@Composable
fun SecondScreen(){
    val context = LocalContext.current
    Column() {

        Button(onClick = { KakaoLogin(context) }) {
            Text(text = "로그인")
        }
    }
}

fun KakaoLogin(context : Context) {
    //val context = LocalContext.current
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if(error != null) {
            Log.e("kakao", "카카오 로그인 실패")
        } else if(token != null) {
            UserApiClient.instance.me { user, error ->
                Log.e("kakao", "${token.accessToken} && ${user}")
            }
        }
    }
    if(UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token,error ->
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