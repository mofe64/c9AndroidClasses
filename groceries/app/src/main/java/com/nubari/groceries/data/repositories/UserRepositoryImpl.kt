package com.nubari.groceries.data.repositories

import com.nubari.groceries.data.remote.Api
import com.nubari.groceries.data.remote.request.LoginRequest
import com.nubari.groceries.data.remote.response.LoginResponse
import com.nubari.groceries.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val api: Api
) : UserRepository {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return api.login(request)
    }
}