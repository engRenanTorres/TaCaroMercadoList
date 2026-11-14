package com.mercadolist.shoppinglist.data

import android.content.Context
import androidx.room.Room
import com.mercadolist.shoppinglist.data.dao.ShoppingListDao
import com.mercadolist.shoppinglist.data.dao.ShoppingListItemDao
import com.mercadolist.shoppinglist.data.dao.SyncStateDao
import com.mercadolist.shoppinglist.data.dao.UserDao

/**
 * Initializes and provides the Room database instance for the ShoppingList app.
 */
object ShoppingListDatabaseInitializer {
    private var database: ShoppingListDatabase? = null

    fun getDatabase(context: Context): ShoppingListDatabase {
        if (database == null) {
            database = Room.databaseBuilder(
                context,
                ShoppingListDatabase::class.java,
                "shopping_list_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
        return database!!
    }

    fun getShoppingListDao(context: Context): ShoppingListDao {
        return getDatabase(context).shoppingListDao()
    }

    fun getShoppingListItemDao(context: Context): ShoppingListItemDao {
        return getDatabase(context).shoppingListItemDao()
    }

    fun getSyncStateDao(context: Context): SyncStateDao {
        return getDatabase(context).syncStateDao()
    }

    fun getUserDao(context: Context): UserDao {
        return getDatabase(context).userDao()
    }

    fun clearDatabase() {
        database?.close()
        database = null
    }
}
