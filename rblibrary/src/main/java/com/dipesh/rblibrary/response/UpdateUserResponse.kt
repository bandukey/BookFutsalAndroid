package com.dipesh.rblibrary.response

import com.dipesh.rblibrary.entity.User

data class UpdateUserResponse(
        val success: Boolean? = null,
        val data: User? = null
)

