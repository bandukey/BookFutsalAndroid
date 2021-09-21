package com.dipesh.rblibrary.api

import com.dipesh.rblibrary.entity.User
import com.dipesh.rblibrary.response.GetUserProfileResponse
import com.dipesh.rblibrary.response.ImageResponse
import com.dipesh.finalassignment.response.LoginResponse
import com.dipesh.rblibrary.response.UpdateUserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    // Register User
    @POST ("/register")
    suspend fun registerUser(
            @Body user: User)
    :Response<LoginResponse>

    // Login User
    @FormUrlEncoded
    @POST ("/login")
    suspend fun checkUser(
            @Field ("email") email: String,
            @Field ("password") password: String
    ): Response<LoginResponse>

    @GET("/me")
    suspend fun getMe(
            @Header ("Authorization") token: String,
    ): Response<GetUserProfileResponse>

    @PUT("/update/user/{id}")
    suspend fun updateUser(
            @Path("id") id: String,
            @Body user: User
    ): Response<UpdateUserResponse>

    @Multipart
    @PUT("/user/{id}/photo")
    suspend fun userImageUpload(
            @Path("id") id: String,
            @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}