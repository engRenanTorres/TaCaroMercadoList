package com.mercadolist.shoppinglist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mercadolist.shoppinglist.data.dao.UserDao
import com.mercadolist.shoppinglist.data.dao.ShoppingListDao
import com.mercadolist.shoppinglist.data.dao.ShoppingListItemDao
import com.mercadolist.shoppinglist.data.dao.SyncStateDao
import com.mercadolist.shoppinglist.data.entities.User
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.entities.SyncState

/**
 * Room database for ShoppingList app.
 * Contains all entities and DAOs.
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
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun shoppingListItemDao(): ShoppingListItemDao
    abstract fun syncStateDao(): SyncStateDao
}
