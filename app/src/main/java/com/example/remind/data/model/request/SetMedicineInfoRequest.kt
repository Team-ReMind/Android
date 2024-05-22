package com.example.remind.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SetMedicineInfoRequest(
    val isTaking: Boolean,
    val medicinesType: String,
    val notTakingReason: String
)