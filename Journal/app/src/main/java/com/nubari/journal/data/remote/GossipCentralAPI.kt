package com.nubari.journal.data.remote

import com.nubari.journal.data.model.RegistrationRequest
import com.nubari.journal.data.model.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GossipCentralAPI {

    @POST("auth/user")
    suspend fun register(@Body request : RegistrationRequest) : RegistrationResponse
}