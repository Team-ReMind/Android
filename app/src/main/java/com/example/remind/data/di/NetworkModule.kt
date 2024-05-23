package com.example.remind.data.di
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.remind.BuildConfig
import com.example.remind.data.network.adapter.ApiResultCallAdapterFactory
import com.example.remind.data.network.interceptor.AuthInterceptor
import com.example.remind.data.network.service.AuthService
import com.example.remind.data.network.service.DoctorService
import com.example.remind.data.network.service.PatientService
import com.example.remind.data.repository.auth.AuthRepository
import com.example.remind.data.repository.auth.AuthRepositoryImpl
import com.example.remind.data.repository.auth.TokenRepository
import com.example.remind.data.repository.auth.TokenRepositoryImpl
import com.example.remind.data.repository.doctor.DoctorRepository
import com.example.remind.data.repository.doctor.DoctorRepositoryImpl
import com.example.remind.data.repository.patient.PatientMedicineRepository
import com.example.remind.data.repository.patient.PatientMedicineRepositoryImpl
import com.example.remind.data.repository.patient.PatientMoodChartRepository
import com.example.remind.data.repository.patient.PatientMoodChartRepositoryImpl
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

    @Singleton
    @Provides
    fun providePatientService(retrofit: Retrofit): PatientService {
        return retrofit.create(PatientService::class.java)
    }

    @Singleton
    @Provides
    fun provideDoctorService(retrofit: Retrofit): DoctorService {
        return retrofit.create(DoctorService::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {
        @Singleton
        @Binds
        abstract fun providesAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
        @Singleton
        @Binds
        abstract fun providesTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

        @Singleton
        @Binds
        abstract fun providesPatientMedicineRepository(patientMedicineRepositoryImpl: PatientMedicineRepositoryImpl): PatientMedicineRepository
        @Singleton
        @Binds
        abstract fun providesPatientMoodChartRepository(patientMoodChartRepositoryImpl: PatientMoodChartRepositoryImpl): PatientMoodChartRepository

        @Singleton
        @Binds
        abstract fun providesDoctorRepository(doctorRepositoryImpl: DoctorRepositoryImpl): DoctorRepository
    }

}