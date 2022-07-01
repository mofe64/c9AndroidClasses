package com.nubari.groceries.domain.repositories

import com.nubari.groceries.data.remote.request.LoginRequest
import com.nubari.groceries.data.remote.response.LoginResponse

interface UserRepository {
    suspend fun login(request: LoginRequest): LoginResponse
}