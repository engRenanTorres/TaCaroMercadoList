package com.mercadolist.shoppinglist.data

import android.content.Context
import com.mercadolist.shoppinglist.data.database.ShoppingListDatabase
import com.mercadolist.shoppinglist.data.repository.*

/**
 * Initializes and provides access to the database repositories.
 * This class ensures that all repository instances are properly created
 * and can be accessed throughout the application.
 */
object DatabaseInitializer {
    
    private var database: ShoppingListDatabase? = null
    
    /**
     * Initialize the database with the given context.
     * @param context The application context
     */
    fun initialize(context: Context) {
        database = ShoppingListDatabase.getDatabase(context)
    }
    
    /**
     * Get the User repository instance.
     * @return UserRepository instance
     */
    fun getUserRepository(): UserRepository {
        return UserRepository(database!!.userDao())
    }
    
    /**
     * Get the ShoppingList repository instance.
     * @return ShoppingListRepository instance
     */
    fun getShoppingListRepository(): ShoppingListRepository {
        return ShoppingListRepository(database!!.shoppingListDao())
    }
    
    /**
     * Get the ShoppingListItem repository instance.
     * @return ShoppingListItemRepository instance
     */
    fun getShoppingListItemRepository(): ShoppingListItemRepository {
        return ShoppingListItemRepository(database!!.shoppingListItemDao())
    }
    
    /**
     * Get the SyncState repository instance.
     * @return SyncStateRepository instance
     */
    fun getSyncStateRepository(): SyncStateRepository {
        return SyncStateRepository(database!!.syncStateDao())
    }
}