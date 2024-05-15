package com.example.remind.data.network.interceptor

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.remind.core.util.Constants.ACCESS_TOKEN
import com.example.remind.core.util.Constants.REFRESH_TOKEN
import com.example.remind.core.util.Constants.USER_NAME
import com.example.remind.core.util.Constants.USER_TYPE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val datastore: DataStore<Preferences>
) {
    fun getAccessToken(): Flow<String?> {
        return datastore.data.map { prefs ->
            prefs[ACCESS_TOKEN]
        }
    }

    fun getRefreshToken(): Flow<String?> {
        return datastore.data.map { prefs ->
            prefs[REFRESH_TOKEN]
        }
    }

    fun getUserType(): Flow<String?> {
        return datastore.data.map { prefs->
            prefs[USER_TYPE]
        }
    }

    suspend fun saveAccessToken(accessToken: String, refreshToken: String) {
        datastore.edit { prefs ->
            prefs[ACCESS_TOKEN] = accessToken
            prefs[REFRESH_TOKEN] = refreshToken
        }
        Log.d("TokenManager","액세스: $accessToken 리프레시: $refreshToken")
    }

    suspend fun saveUserName(userName: String) {
        datastore.edit { prefs ->
            prefs[USER_NAME] = userName
        }
    }
    fun getUserName(): Flow<String?> {
        return datastore.data.map { prefs->
            prefs[USER_NAME]
        }
    }

    suspend fun saveUserType(userType: String) {
        datastore.edit { prefs->
            prefs[USER_TYPE] = userType
        }
        Timber.d("사용자 유형: $userType" )
    }

    suspend fun deleteAccessToken() {
        datastore.edit { prefs->
            prefs.remove(ACCESS_TOKEN)
            prefs.remove(REFRESH_TOKEN)
        }
    }
}