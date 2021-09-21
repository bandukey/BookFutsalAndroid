package com.dipesh.rblibrary.response

import com.dipesh.rblibrary.entity.Cart

data class GetCartItemsResponse (
        val success: Boolean? = null,
        val count: Int? =null,
        val data: MutableList<Cart>? = null
)