package com.dipesh.finalassignment.response

import com.dipesh.rblibrary.entity.User

data class LoginResponse(
        val success: Boolean? = null,
        val token: String? = null,
        val data: User? = null
)

