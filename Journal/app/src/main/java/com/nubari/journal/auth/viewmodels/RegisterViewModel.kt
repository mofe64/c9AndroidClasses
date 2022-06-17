package com.nubari.journal.auth.viewmodels

import androidx.lifecycle.*
import com.nubari.journal.auth.events.AuthEvent
import com.nubari.journal.data.model.RegistrationRequest
import com.nubari.journal.data.model.Resource
import com.nubari.journal.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers


class RegisterViewModel : ViewModel() {

    val isFormValid: MutableLiveData<Boolean> = MutableLiveData(
        true
    )

    //    var status: LiveData<Resource<Pair<String, String>>> = liveData { }
    private val api = RetrofitBuilder.gossipCentralAPI

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.RegistrationEvent -> {
                val request = event.request
                if (
                    request.email.isEmpty() ||
                    request.firstName.isEmpty() ||
                    request.lastName.isEmpty() ||
                    request.password.isEmpty()
                ) {
                    isFormValid.value = false
                    return
                }
//                status = register(request)

            }
        }
    }


    fun register(request: RegistrationRequest) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            val result = api.register(request)
            if (result.successful) {
                emit(Resource.success(result.data))
            } else {
                emit(Resource.error(data = null, message = result.data.first))
            }

        }


}