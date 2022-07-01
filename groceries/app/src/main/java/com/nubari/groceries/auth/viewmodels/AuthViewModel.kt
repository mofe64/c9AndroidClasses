package com.nubari.groceries.auth.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nubari.groceries.auth.events.AuthEvent
import com.nubari.groceries.auth.state.AuthState
import com.nubari.groceries.data.remote.request.LoginRequest
import com.nubari.groceries.domain.usecases.auth.AuthenticationUseCases
import com.nubari.groceries.general.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    private val _state = mutableStateOf(
        AuthState(
            isAuthenticated = false
        )
    )
    val state: State<AuthState> = _state

    private val _eventFlow = MutableSharedFlow<AuthEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun createEvent(event: AuthEvent) {
        onEvent(event)
    }

    private fun onEvent(event: AuthEvent) {
        Log.i("login", "received event")
        when (event) {

            is AuthEvent.Login -> {
                val request = LoginRequest(
                    email = event.email,
                    password = event.password
                )
                Log.i("login", "calling login")
                login(request = request)
            }
            else -> {
            }
        }
    }

    private fun login(request: LoginRequest) {
        authenticationUseCases.login(request = request)
            .onEach {
                when (it) {
                    is Resource.Loading -> {
                        Log.i("login", "got loading")
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        val message = it.message ?: "An unexpected error occurred"
                        _state.value = state.value.copy(
                            isLoading = false,
                            errorOccurred = true,
                            errorMessage = message
                        )
                        _eventFlow.emit(
                            AuthEvent.LoginFailure(
                                message = message
                            )
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            isAuthenticated = true
                        )
                        _eventFlow.emit(
                            AuthEvent.LoginSuccess
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }


}