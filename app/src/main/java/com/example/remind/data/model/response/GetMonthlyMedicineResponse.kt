package com.example.remind.data.model.response

data class GetMonthlyMedicineResponse(
    val code: Int,
    val `data`: Monthly,
    val message: String
)
data class Monthly(
    val monthlyTakingMedicineDtos: List<MonthlyTakingMedicineDto>
)
data class MonthlyTakingMedicineDto(
    val date: String,
    val needMedicine: Boolean,
    val takingCount: Int,
    val takingLevel: Int
)