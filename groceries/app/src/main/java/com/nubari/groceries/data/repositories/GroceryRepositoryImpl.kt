package com.nubari.groceries.data.repositories

import com.nubari.groceries.data.local.dao.GroceriesDAO
import com.nubari.groceries.data.local.models.Grocery
import com.nubari.groceries.domain.repositories.GroceryRepository
import kotlinx.coroutines.flow.Flow

class GroceryRepositoryImpl(
    private val groceryDao: GroceriesDAO
) : GroceryRepository {
    override suspend fun saveProduct(grocery: Grocery) {
        groceryDao.saveGrocery(grocery)
    }

    override fun getAllProducts(): Flow<List<Grocery>> {
        return groceryDao.getAllGroceries()
    }
}