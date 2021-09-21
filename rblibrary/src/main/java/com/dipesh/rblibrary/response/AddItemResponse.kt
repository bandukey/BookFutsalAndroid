package com.dipesh.rblibrary.response

import com.dipesh.rblibrary.entity.Item

data class AddItemResponse (
    val success: Boolean? = null,
    val data: Item? = null
        )