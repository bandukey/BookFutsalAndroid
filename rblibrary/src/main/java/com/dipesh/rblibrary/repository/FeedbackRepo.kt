package com.dipesh.rblibrary.repository


import com.dipesh.rblibrary.api.FeedbackAPI
import com.dipesh.rblibrary.response.AddItemResponse
import com.dipesh.rblibrary.api.MyApiRequest
import com.dipesh.rblibrary.api.ServiceBuilder
import com.dipesh.rblibrary.entity.Feedback


class FeedbackRepo: MyApiRequest() {
    private val itemAPI= ServiceBuilder.buildService(FeedbackAPI::class.java)

    suspend fun addFeedback(feedback: Feedback): AddItemResponse {
        return apiRequest {
            itemAPI.addFeedback(
                    ServiceBuilder.token!!, feedback
            )
        }
    }

}