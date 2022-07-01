package com.nubari.groceries.navigation.destinations

import com.nubari.groceries.R

sealed class PrimaryDestination(
    val name: String,
    val startRoute: String,
    val rootRoute: String,
    val icon: Int
) {
    object Store : PrimaryDestination(
        name = "store",
        startRoute = "store",
        rootRoute = "store_root",
        icon = R.drawable.store
    )
    object Explore : PrimaryDestination(
        name = "explore",
        startRoute = "explore",
        rootRoute = "explore_root",
        icon = R.drawable.search
    )
    object Cart : PrimaryDestination(
        name = "cart",
        startRoute = "cart",
        rootRoute = "cart_root",
        icon = R.drawable.cart
    )
    object Favorite : PrimaryDestination(
        name = "favorite",
        startRoute = "favorite",
        rootRoute = "favorite_root",
        icon = R.drawable.liked
    )
    object Profile : PrimaryDestination(
        name = "profile",
        startRoute = "profile",
        rootRoute = "profile_root",
        icon = R.drawable.profile
    )
}