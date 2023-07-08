package com.acout.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Response<T>(
    val data: T,
    val error:Error=Error(""),
    val success: Boolean
)

