package com.nubari.groceries.data.remote

import com.nubari.groceries.data.remote.request.LoginRequest
import com.nubari.groceries.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("api/v1/auth/login")
    suspend fun login(@Body request : LoginRequest) : LoginResponse

}