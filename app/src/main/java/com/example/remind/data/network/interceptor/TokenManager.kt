package com.example.remind.data.network.interceptor

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.remind.core.util.Constants.ACCESS_TOKEN
import com.example.remind.core.util.Constants.REFRESH_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    suspend fun saveAccessToken(accessToken: String, refreshToken: String) {
        datastore.edit { prefs ->
            prefs[ACCESS_TOKEN] = accessToken
            prefs[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun deleteAccessToken() {
        datastore.edit { prefs->
            prefs.remove(ACCESS_TOKEN)
            prefs.remove(REFRESH_TOKEN)
        }
    }
}