package com.nubari.groceries.domain.usecases.auth

import android.util.Log
import com.nubari.groceries.data.remote.request.LoginRequest
import com.nubari.groceries.data.remote.response.LoginResponse
import com.nubari.groceries.domain.repositories.UserRepository
import com.nubari.groceries.general.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class Login(
    private val userRepository: UserRepository
) {
    operator fun invoke(request: LoginRequest): Flow<Resource<LoginResponse>> =
        flow {
            try {
                Log.i("login", "flow running")
                emit(Resource.Loading<LoginResponse>())
                Log.i("login", "emitted loading")
                val response = userRepository.login(request)
                emit(Resource.Success<LoginResponse>(response))
                Log.i("login", "emitted success")
            } catch (e: HttpException) {
                Log.i("login", "http exception")
                Log.i("login", e.message())
                emit(
                    Resource.Error<LoginResponse>(
                        message = e.localizedMessage ?: "Something went wrong"
                    )
                )
            } catch (e: IOException) {
                Log.i("login", "exception")
                Log.i("login", e.toString())
                emit(
                    Resource.Error<LoginResponse>(
                        message = e.localizedMessage ?: "Could not reach server, " +
                        "please check your internet"
                    )
                )
            } catch (e: Exception) {
                Log.i("login", "exception")
                Log.i("login", e.toString())
                emit(
                    Resource.Error<LoginResponse>(
                        message = e.localizedMessage ?: "Something went wrong"
                    )
                )
            }
        }

}