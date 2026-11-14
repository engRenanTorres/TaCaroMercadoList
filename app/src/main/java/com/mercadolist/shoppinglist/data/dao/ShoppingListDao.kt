package com.mercadolist.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * DAO for ShoppingList entity operations.
 * Provides reactive data streams using Flow.
 */
@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(list: ShoppingList): Long

    @Update
    suspend fun updateShoppingList(list: ShoppingList)

    @Query("SELECT * FROM shopping_lists WHERE id = :listId")
    suspend fun getShoppingListById(listId: Long): ShoppingList?

    @Query("SELECT * FROM shopping_lists WHERE userId = :userId")
    fun getUserShoppingLists(userId: Long): Flow<List<ShoppingList>>

    @Query("SELECT * FROM shopping_lists")
    fun getAllShoppingLists(): Flow<List<ShoppingList>>

    @Query("DELETE FROM shopping_lists WHERE id = :listId")
    suspend fun deleteShoppingList(listId: Long)

    @Query("SELECT COUNT(*) FROM shopping_lists WHERE userId = :userId")
    suspend fun getUserShoppingListCount(userId: Long): Int

    @Query("SELECT * FROM shopping_lists WHERE name LIKE '%' || :query || '%'")
    fun searchShoppingLists(query: String): Flow<List<ShoppingList>>
}
