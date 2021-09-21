package com.dipesh.rblibrary.response

import com.dipesh.rblibrary.entity.User

data class GetMeResponse (
        val success: Boolean? = null,
        val data: MutableList<User>? = null
)