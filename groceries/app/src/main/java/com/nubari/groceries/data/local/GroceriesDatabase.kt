package com.nubari.groceries.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nubari.groceries.data.local.dao.GroceriesDAO
import com.nubari.groceries.data.local.models.Grocery
import com.nubari.groceries.data.local.typeconverters.ApplicationTypeConverter

@Database(
    entities = [
        Grocery::class
    ],
    version = 1
)
@TypeConverters(ApplicationTypeConverter::class)
abstract class GroceriesDatabase : RoomDatabase() {
    abstract val groceryDao: GroceriesDAO

    companion object {
        const val DATABASE_NAME = "groceries_db"
    }
}