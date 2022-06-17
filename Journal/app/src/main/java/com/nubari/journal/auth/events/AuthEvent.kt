package com.nubari.journal.auth.events

import com.nubari.journal.data.model.RegistrationRequest

sealed class AuthEvent {
    data class RegistrationEvent(val request: RegistrationRequest) : AuthEvent()
}
