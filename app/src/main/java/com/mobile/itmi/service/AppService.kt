package com.mobile.itmi.service

import com.mobile.itmi.base.ResponseError
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {
    @GET("medications")
    suspend fun getMedication(
    ): NetworkResponse<List<Medication>, ResponseError>

    @GET("medications/{id}")
    suspend fun getMedicationDetail(
        @Path("id") id: String
    ): NetworkResponse<Medication, ResponseError>

}