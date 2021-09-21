package com.dipesh.rblibrary.response

import com.dipesh.rblibrary.entity.Feedback

data class AddFeedbackResponse (
    val success: Boolean? = null,
    val data: Feedback? = null
        )