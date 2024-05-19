package com.example.remind.domain.usecase.patience_usecase

import com.example.remind.data.model.request.SetMedicineInfoRequest
import com.example.remind.data.model.response.SetMedicineInfoResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.patient.PatientMedicineRepository
import javax.inject.Inject

class SetMedicineInfoUseCase @Inject constructor(
    private val patientMedicineRepository: PatientMedicineRepository
) {
    suspend operator fun invoke(body: SetMedicineInfoRequest): ApiResult<SetMedicineInfoResponse> {
        return patientMedicineRepository.setMedicineInfo((body))
    }
}