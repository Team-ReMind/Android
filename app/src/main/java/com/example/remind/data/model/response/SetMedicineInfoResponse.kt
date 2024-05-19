package com.example.remind.data.model.response

data class SetMedicineInfoResponse(
    val code: Int,
    val `data`: MedicineInfo,
    val message: String
)

data class MedicineInfo(
    val isTaking: Boolean,
    val notTakingReason: String
)