package com.dipesh.rblibrary.response

import com.dipesh.rblibrary.entity.Cart

data class AddCartResponse (
    val success: Boolean? = null,
    val data: Cart? = null
        )