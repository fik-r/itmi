package com.mobile.itmi.base

import com.google.gson.Gson

interface ResponseBodyInterface {
    fun toErrorJson(status: Int, message: String): String
}

data class ResponseError(
    val status: Int = 0,
    val error: String,
    val message: String
): ResponseBodyInterface {
    override fun toErrorJson(status: Int, message: String): String {
        return Gson().toJson(ResponseError(status = status, error = error, message = message))
    }
}