package com.dipesh.rblibrary.repository


import com.dipesh.rblibrary.entity.User
import com.dipesh.rblibrary.response.GetUserProfileResponse
import com.dipesh.rblibrary.response.ImageResponse
import com.dipesh.finalassignment.response.LoginResponse
import com.dipesh.rblibrary.response.UpdateUserResponse
import com.dipesh.rblibrary.api.MyApiRequest
import com.dipesh.rblibrary.api.ServiceBuilder
import com.dipesh.rblibrary.api.UserAPI
import okhttp3.MultipartBody

class UserRepo : MyApiRequest() {
    private val userAPI= ServiceBuilder.buildService(UserAPI::class.java)

    //Register User
    suspend fun registerUser(user: User): LoginResponse{
        return apiRequest {
            userAPI.registerUser(user)
        }
    }

    //Login User
    suspend fun loginUser(email: String, password: String): LoginResponse{
        return apiRequest {
            userAPI.checkUser(email,password)
        }
    }

    suspend fun getMe(): GetUserProfileResponse {
        return apiRequest {
            userAPI.getMe(ServiceBuilder.token!!)
        }
    }

    suspend fun updateUser(id:String, user: User): UpdateUserResponse {
        return apiRequest {
            userAPI.updateUser(id, user)
        }
    }

    suspend fun userImageUpload(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            userAPI.userImageUpload(id, body)
        }
    }
}