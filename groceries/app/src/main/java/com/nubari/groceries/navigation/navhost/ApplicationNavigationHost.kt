package com.nubari.groceries.navigation.navhost

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nubari.groceries.auth.screens.Login
import com.nubari.groceries.auth.screens.Register
import com.nubari.groceries.cart.screens.Cart
import com.nubari.groceries.explore.screens.Explore
import com.nubari.groceries.favorites.screens.Favorites
import com.nubari.groceries.general.components.BottomNavBar
import com.nubari.groceries.navigation.destinations.Destination
import com.nubari.groceries.navigation.destinations.PrimaryDestination
import com.nubari.groceries.profile.screens.Profile
import com.nubari.groceries.store.screens.Store

@Composable
fun ApplicationNavigationHost() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(
                modifier = Modifier.fillMaxWidth(),
                navController = navController
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = PrimaryDestination.Store.startRoute
        ) {
            composable(route = PrimaryDestination.Store.startRoute) {
                Store()
            }
            composable(route = PrimaryDestination.Explore.startRoute) {
                Explore()
            }
            composable(route = PrimaryDestination.Cart.startRoute) {
                Cart()
            }
            composable(route = PrimaryDestination.Favorite.startRoute) {
                Favorites()
            }
            composable(route = PrimaryDestination.Profile.startRoute) {
                Profile()
            }

        }
    }
}