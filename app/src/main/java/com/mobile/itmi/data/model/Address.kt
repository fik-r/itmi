package com.mobile.itmi.data.model

import com.google.gson.annotations.SerializedName

data class Address(
    val id: String,
    @SerializedName("postal_code")
    val postalCode: String,
    val latitude: String,
    val longitude: String,
    @SerializedName("first_line")
    val firstLine: String,
    @SerializedName("second_line")
    val secondLine: String,
    val note: String,
    val province: String,
    val district: String,
    @SerializedName("sub_district")
    val subDistrict: String
)