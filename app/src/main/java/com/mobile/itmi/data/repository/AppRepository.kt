package com.mobile.itmi.data.repository

import com.mobile.itmi.base.ResponseError
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.network.NetworkResponse
import com.mobile.itmi.service.AppService

interface AppRepository {
    suspend fun getMedication(): NetworkResponse<List<Medication>, ResponseError>
    suspend fun getMedicationDetail(id: String): NetworkResponse<Medication, ResponseError>
}

open class AppRepositoryImpl(private val appService: AppService) : AppRepository {
    override suspend fun getMedication(): NetworkResponse<List<Medication>, ResponseError> {
        return appService.getMedication()
    }

    override suspend fun getMedicationDetail(id: String): NetworkResponse<Medication, ResponseError> {
        return appService.getMedicationDetail(id)
    }
}