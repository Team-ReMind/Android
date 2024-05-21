package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.response.GetPrescriptionResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMedicineRepository
import javax.inject.Inject

class PrescriptionUseCase @Inject constructor(
    private val patientMedicineRepository: PatientMedicineRepository
) {
    suspend operator fun invoke(memberId: Int): ApiResult<GetPrescriptionResponse> {
        return patientMedicineRepository.getPrescription(memberId)
    }
}