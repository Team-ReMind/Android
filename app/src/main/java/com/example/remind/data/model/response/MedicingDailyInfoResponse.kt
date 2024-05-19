package com.example.remind.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MedicingDailyInfoResponse(
    val code: Int,
    val `data`: MedicineData,
    val message: String
)
@Serializable
data class MedicineData(
    val dailyTakingMedicineDtos: List<DailyTakingMedicineList>
)

@Serializable
data class DailyTakingMedicineList(
    val importance: Int,
    val isTaking: Boolean,
    val medicinesType: String,
    val notTakingReason: String,
    val prescriptionId: Int,
    val takingTime: String
)