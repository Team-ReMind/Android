package com.example.remind.data.repository.auth

interface TokenRepository {
    suspend fun getFCMToken(): String
}