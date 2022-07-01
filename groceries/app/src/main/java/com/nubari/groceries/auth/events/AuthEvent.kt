package com.nubari.groceries.auth.events

sealed class AuthEvent {
    data class Login(
        val email: String,
        val password: String
    ) : AuthEvent()

    object LoginSuccess : AuthEvent()
    data class LoginFailure(val message: String) : AuthEvent()
}

