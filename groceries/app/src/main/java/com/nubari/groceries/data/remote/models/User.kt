package com.nubari.groceries.data.remote.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: String,
    val email: String,
    val firstname: String,
    val lastname: String
)
