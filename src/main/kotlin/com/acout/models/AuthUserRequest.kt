package com.acout.models

import kotlinx.serialization.Serializable

@Serializable

data class AuthUserRequest(val username: String, val password: String)
