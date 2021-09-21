package com.dipesh.rblibrary.response

import com.dipesh.rblibrary.entity.User

data class GetUserProfileResponse (
        val success: Boolean? = null,
        val data: User? = null
)