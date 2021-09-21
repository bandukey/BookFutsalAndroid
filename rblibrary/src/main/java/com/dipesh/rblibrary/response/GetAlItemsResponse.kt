package com.dipesh.rblibrary.response

import com.dipesh.rblibrary.entity.Item

data class GetAlItemsResponse (
        val success: Boolean? = null,
        val count: Int? =null,
        val data: MutableList<Item>? = null
)