package com.example.remind.data.repository.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun getStringValue(type: Preferences.Key<String>): Flow<String>
    suspend fun setStringValue(type: Preferences.Key<String>, value:String)

    suspend fun deleteStringValue(type: Preferences.Key<String>)
}