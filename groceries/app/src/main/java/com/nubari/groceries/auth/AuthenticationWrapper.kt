package com.nubari.groceries.auth

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.nubari.groceries.auth.viewmodels.AuthViewModel
import com.nubari.groceries.navigation.navhost.AuthenticationNavigationHost

@Composable
fun AuthenticationWrapper(
    viewModel: AuthViewModel
) {
    val navHostController = rememberNavController()
    AuthenticationNavigationHost(
        navHostController = navHostController,
        authViewModel = viewModel
    )
}