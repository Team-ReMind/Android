package com.example.remind.data.model.response

data class MedicineInfoResponse(
    val code: Int,
    val data: DailyMedicine,
    val message: String
)

data class DailyMedicine(
    val dailyTakingMedicineDtos: List<DailyTakingMedicineDto>
)

data class DailyTakingMedicineDto(
    val importance: Int,
    val isTaking: Boolean,
    val medicinesType: String,
    val notTakingReason: String,
    val takingMedicineId: Int,
    val takingTime: String
)