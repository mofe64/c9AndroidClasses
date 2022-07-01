package com.nubari.groceries.data.remote.response

import com.nubari.groceries.data.remote.models.User

data class LoginResponse(
    val status: String,
    val token: String,
    val data: User
)
