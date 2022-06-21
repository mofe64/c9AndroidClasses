package com.nubari.journal.auth.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.Gson
import com.nubari.journal.auth.events.AuthEvent
import com.nubari.journal.data.model.RegistrationRequest
import com.nubari.journal.data.model.RegistrationResponse
import com.nubari.journal.data.model.Resource
import com.nubari.journal.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class RegisterViewModel : ViewModel() {

    val isFormValid: MutableLiveData<Boolean> = MutableLiveData(true)
    var status: MutableLiveData<Resource<*>> = MutableLiveData()
    private val api = RetrofitBuilder.gossipCentralAPI

    fun onEvent(event: AuthEvent) {
        Log.i("register ", "register view model received event")
        Log.i("register", event.toString())
        when (event) {
            is AuthEvent.RegistrationEvent -> {
                Log.i("register", " registration event received")
                val request = event.request
                if (
                    request.email.isEmpty() ||
                    request.firstName.isEmpty() ||
                    request.lastName.isEmpty() ||
                    request.password.isEmpty()
                ) {
                    Log.i("register", "missing field , set is form valid to false")
                    isFormValid.value = false
                    return
                }
                Log.i("register", "request valid calling register method")
                register(request)
            }

        }
    }


    private fun register(request: RegistrationRequest) {
        status.value = Resource.loading(data = null);
        viewModelScope.launch(Dispatchers.IO) {
            val threadInfo = Thread.currentThread().name
            Log.i("register", "register method running on thread $threadInfo")
            val result: RegistrationResponse?
            try {
                result = api.register(request)
                if (result.successful) {
                    Log.i("register-success", result.toString())
                    val resource = Resource.success(data = result.data)
                    status.postValue(resource)
                } else {
                    Log.i("register-fail", result.data.toString())
                    val resource = Resource.error(data = null, message = result.data.message)
                    status.postValue(resource)
                }
            } catch (e: HttpException) {
                val resource: Resource<*>
                if (e.code() == 400) {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(
                        e.response()?.errorBody()!!.charStream(),
                        RegistrationResponse::class.java
                    )
                    Log.i("register-error ", errorResponse.toString())
                    resource = Resource.error(data = null, message = errorResponse.data.message)
                    status.postValue(resource)
                } else {
                    Log.i("register-error", e.toString())
                    Log.i("register-error", e.message())
                    Log.i("register-error", e.response().toString())
                    resource = Resource.error(
                        data = null,
                        message = e.localizedMessage ?: "Looks like something went wrong"
                    )
                    status.postValue(resource)
                }

            } catch (e: Exception) {
                Log.i("register", e.toString())
                val resource = Resource.error(
                    data = null,
                    message = e.localizedMessage ?: "Looks like something went wrong"
                )
                status.postValue(resource)
            }

        }
    }


}