package com.nubari.groceries.auth.events

sealed class LoginFormEvent {
    data class EnteredEmail(val value: String) : LoginFormEvent()
    data class EnteredPassword(val value: String) : LoginFormEvent()
    data class FocusChange(val focusFieldName: String) : LoginFormEvent()
}
