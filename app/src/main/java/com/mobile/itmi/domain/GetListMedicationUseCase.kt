package com.mobile.itmi.domain

import com.mobile.itmi.base.ResponseError
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.data.repository.AppRepository
import com.mobile.itmi.network.NetworkResponse
import com.mobile.itmi.network.UseCase

class GetListMedicationUseCase(
    private val appRepository: AppRepository
) : UseCase<List<Medication>, ResponseError, Unit>() {

    override suspend fun build(params: Unit?): NetworkResponse<List<Medication>, ResponseError> {
        return appRepository.getMedication()
    }

}