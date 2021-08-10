package com.mobile.itmi.data.model

import com.google.gson.annotations.SerializedName

class Patient(
    val id: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)