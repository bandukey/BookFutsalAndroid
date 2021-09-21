package com.dipesh.rblibrary.api

import com.dipesh.rblibrary.entity.Item
import com.dipesh.rblibrary.response.AddItemResponse
import com.dipesh.rblibrary.response.GetAlItemsResponse
import com.dipesh.rblibrary.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ItemAPI {

    @POST ("/add/item")
    suspend fun addItem(
        @Header ("Authorization") token: String,
        @Body item: Item
    ):Response<AddItemResponse>

    @GET ("item/all")
    suspend fun getAllItems(
            @Header ("Authorization") token: String,
    ):Response<GetAlItemsResponse>

    @GET ("item/drink")
    suspend fun getDrinks(
            @Header ("Authorization") token: String,
    ):Response<GetAlItemsResponse>

    @GET ("item/vege")
    suspend fun getVege(
            @Header ("Authorization") token: String,
    ):Response<GetAlItemsResponse>

    @GET ("item/non-vege")
    suspend fun getNonVege(
            @Header ("Authorization") token: String,
    ):Response<GetAlItemsResponse>

    @Multipart
    @PUT("item/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}