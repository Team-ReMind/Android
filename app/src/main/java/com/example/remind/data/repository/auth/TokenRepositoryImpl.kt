package com.example.remind.data.repository.auth

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(): TokenRepository {
    override suspend fun getFCMToken(): String {
        return FirebaseMessaging.getInstance().token.await()
    }
}