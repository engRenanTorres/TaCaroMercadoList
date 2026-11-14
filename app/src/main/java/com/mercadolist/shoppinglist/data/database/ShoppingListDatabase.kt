package com.mercadolist.shoppinglist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mercadolist.shoppinglist.data.dao.UserDao
import com.mercadolist.shoppinglist.data.dao.ShoppingListDao
import com.mercadolist.shoppinglist.data.dao.ShoppingListItemDao
import com.mercadolist.shoppinglist.data.dao.SyncStateDao
import com.mercadolist.shoppinglist.data.entities.User
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.entities.SyncState

/**
 * Room database for the shopping list application.
 * Provides offline-first storage with Room entities and DAOs.
 */
@Database(
    entities = [
        User::class,
        ShoppingList::class,
        ShoppingListItem::class,
        SyncState::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract class ShoppingListDatabase : RoomDatabase() {
    /**
     * DAO for User entity operations.
     */
    abstract fun userDao(): UserDao

    /**
     * DAO for ShoppingList entity operations.
     */
    abstract fun shoppingListDao(): ShoppingListDao

    /**
     * DAO for ShoppingListItem entity operations.
     */
    abstract fun shoppingListItemDao(): ShoppingListItemDao

    /**
     * DAO for SyncState entity operations.
     */
    abstract fun syncStateDao(): SyncStateDao

    companion object {
        /**
         * Singleton instance of the database.
         */
        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        /**
         * Get the database instance.
         * Uses singleton pattern to ensure single database instance per app.
         * @param context The application context
         * @return The database instance
         */
        fun getDatabase(context: Context): ShoppingListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingListDatabase::class.java,
                    "shopping_list_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
