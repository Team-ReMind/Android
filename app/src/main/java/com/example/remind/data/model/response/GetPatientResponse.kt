package com.example.remind.data.model.response

data class GetPatientResponse(
    val code: Int,
    val `data`: DoctorData,
    val message: String
)
data class DoctorData(
    val patientDtos: List<PatientDto> = emptyList(),
    val patientNumber: Int = 0,
    val targetMemberCode: String = ""
)
data class PatientDto(
    val age: Int=0,
    val gender: String="",
    val memberId: Int=0,
    val name: String="",
    val birthYear: Int = 0
)

