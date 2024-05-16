package com.example.remind.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class OnBoardingRequest(
    val centerName: String,
    val city: String,
    val district: String,
    val protectorPhoneNumber: String,
    val rolesType: String,
    val fcmToken: String
)