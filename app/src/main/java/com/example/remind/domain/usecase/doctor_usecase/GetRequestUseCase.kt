package com.example.remind.domain.usecase.doctor_usecase

import com.example.remind.data.model.request.SetAcceptrequest
import com.example.remind.data.model.response.GetAcceptResponse
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.data.repository.doctor.DoctorRepository
import javax.inject.Inject

class GetRequestUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(body: SetAcceptrequest): ApiResult<GetAcceptResponse> {
        return doctorRepository.getRequest(body)
    }
}