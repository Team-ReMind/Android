package com.example.remind.domain.usecase.doctor_usecase

import com.example.remind.data.model.response.GetPatientResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.doctor.DoctorRepository
import javax.inject.Inject

class GetPatientUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(status: String): ApiResult<GetPatientResponse> {
        return doctorRepository.getPatientList(status)
    }
}