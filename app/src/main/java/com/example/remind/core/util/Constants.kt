package com.example.remind.core.util

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val REMIND_DATASTORE = "Remind_datastore"

    //preferencekeys
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
}