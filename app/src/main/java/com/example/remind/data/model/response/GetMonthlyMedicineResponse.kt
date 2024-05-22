package com.example.remind.data.model.response

data class GetMonthlyMedicineResponse(
    val code: Int=0,
    val `data`: Monthly = Monthly(),
    val message: String=""
)
data class Monthly(
    val monthlyTakingMedicineDtos: List<MonthlyTakingMedicineDto> = emptyList()
)
data class MonthlyTakingMedicineDto(
    val date: Int= 0,
    val needMedicine: Boolean = false,
    val takingCount: Int = 0,
    val takingLevel: Int = 0
)