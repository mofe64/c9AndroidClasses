package com.nubari.groceries.domain.repositories

import com.nubari.groceries.data.local.models.Grocery
import kotlinx.coroutines.flow.Flow

interface GroceryRepository {
    suspend fun saveProduct(grocery: Grocery)
    fun getAllProducts(): Flow<List<Grocery>>
}