package com.example.remind.data.model.response

data class GetMedicineRateResponse(
    val code: Int,
    val `data`: Rate,
    val message: String
)
data class Rate(
    val breakfastRate: Double=0.0,
    val dinnerRate: Double=0.0,
    val lunchRate: Double=0.0,
    val totalRate: Double=0.0
)
