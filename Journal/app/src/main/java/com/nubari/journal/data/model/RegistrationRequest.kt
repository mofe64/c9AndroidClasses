package com.nubari.journal.data.model

data class RegistrationRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
