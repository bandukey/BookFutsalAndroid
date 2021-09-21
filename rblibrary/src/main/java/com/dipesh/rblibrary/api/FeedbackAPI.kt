package com.dipesh.rblibrary.api

import com.dipesh.rblibrary.entity.Feedback
import com.dipesh.rblibrary.response.AddItemResponse
import retrofit2.Response
import retrofit2.http.*

interface FeedbackAPI {

    @POST ("/add/feedback")
    suspend fun addFeedback(
        @Header ("Authorization") token: String,
        @Body feedback: Feedback
    ):Response<AddItemResponse>

}