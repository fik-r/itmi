package com.mobile.itmi.domain

import com.mobile.itmi.base.ResponseError
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.data.repository.AppRepository
import com.mobile.itmi.network.NetworkResponse
import com.mobile.itmi.network.UseCase

class GetMedicationDetailUseCase(
    private val appRepository: AppRepository
) : UseCase<Medication, ResponseError, String>() {

    override suspend fun build(params: String?): NetworkResponse<Medication, ResponseError> {
        requireNotNull(params) { "params must not be null" }
        return appRepository.getMedicationDetail(params)
    }

}