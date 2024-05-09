package com.example.remind.data.di
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.remind.BuildConfig
import com.example.remind.data.network.adapter.ApiResultCallAdapterFactory
import com.example.remind.data.network.interceptor.AuthInterceptor
import com.example.remind.data.network.interceptor.TokenManager
import com.example.remind.data.network.service.AuthService
import com.example.remind.data.repository.auth.AuthRepository
import com.example.remind.data.repository.auth.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(
        tokenInterceptor: AuthInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(ChuckerInterceptor(context))
            .build()

    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ApiResultCallAdapterFactory())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {
        @Singleton
        @Binds
        abstract fun providesAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
    }
}