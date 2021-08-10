package com.mobile.itmi.data.model

import com.google.gson.annotations.SerializedName

data class Medication(
    val id: String,
    val status: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("total_price")
    val totalPrice: String?,
    val currency: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("prescription_image")
    val prescriptionImage: String?,
    val patient: Patient?,
    val address: Address?
)
