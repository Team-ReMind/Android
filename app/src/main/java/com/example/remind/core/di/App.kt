package com.example.remind.core.di
import com.example.remind.BuildConfig
import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    val KAKAO = BuildConfig.KAKAO_NATIVE_APP_KEY
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,KAKAO)
    }
}