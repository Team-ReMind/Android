package com.example.remind.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetPrescriptionResponse(
    val code: Int,
    val `data`: Prescription,
    val message: String
)
@Serializable
data class Prescription(
    val breakfastImportance: Int = 0,
    val dinnerImportance: Int = 0,
    val etcImportance: Int = 0,
    val isExist: Boolean = true,
    val lunchImportance: Int = 0,
    val memo: String = "",
    val name: String = "",
    val period: Int = 0,
    val prescriptionDate: String = "",
    val prescriptionId: Int = 0
)