package com.nubari.introtocompose.ui

import com.nubari.introtocompose.R

object Util {
    fun generateSushiItem(): List<Meal> {
        return listOf(
            Meal("Original Sushi", 21.0, 4.8, imageLink = R.drawable.sushsi),
            Meal("Shrimp Sushi", 87.0, 4.2, imageLink = R.drawable.sushsi),
            Meal("Kaizen Sushi", 201.0, 3.8, imageLink = R.drawable.sushsi),
            Meal("Wagyu Sushi", 11.0, 4.4, imageLink = R.drawable.sushsi),
            Meal("Mushu Sushi", 27.0, 5.0, imageLink = R.drawable.sushsi),
            Meal("Croaker Sushi", 91.0, 2.2, imageLink = R.drawable.sushsi),
            Meal("Titus Sushi", 56.0, 4.1, imageLink = R.drawable.sushsi),
            Meal("Salmon Sushi", 19.0, 2.0, imageLink = R.drawable.sushsi),
        )
    }

    fun generateRamenItem(): List<Meal> {
        return listOf(
            Meal("Pepper Ramen", 11.0, 4.8, imageLink = R.drawable.ramen),
            Meal("Beef Ramen", 77.0, 4.2, imageLink = R.drawable.ramen),
            Meal("Chicken Ramen", 101.0, 3.8, imageLink = R.drawable.ramen),
            Meal("Fish Ramen", 1.0, 4.4, imageLink = R.drawable.ramen),
            Meal("Mushu Ramen", 17.0, 5.0, imageLink = R.drawable.ramen),
            Meal("Snail Ramen", 81.0, 2.2, imageLink = R.drawable.ramen),
            Meal("Titus Ramen", 46.0, 4.1, imageLink = R.drawable.ramen),
            Meal("Deep Fried Ramen", 9.0, 2.0, imageLink = R.drawable.ramen),
        )
    }
}

data class Meal(
    val name: String,
    val price: Double,
    val rating: Double,
    var isFav: Boolean = false,
    val imageLink: Int = -1
)