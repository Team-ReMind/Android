package com.example.remind.data.model.response

data class GetMedicineRateResponse(
    val code: Int,
    val `data`: Rate,
    val message: String
)
data class Rate(
    val breakfastRate: Int=0,
    val dinnerRate: Int=0,
    val lunchRate: Int=0,
    val totalRate: Int=0
)
