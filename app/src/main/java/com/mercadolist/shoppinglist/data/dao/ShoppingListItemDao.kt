package com.mercadolist.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * DAO for ShoppingListItem entity operations.
 * Provides reactive data streams using Flow.
 */
@Dao
interface ShoppingListItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingListItem(item: ShoppingListItem): Long

    @Update
    suspend fun updateShoppingListItem(item: ShoppingListItem)

    @Query("SELECT * FROM shopping_list_items WHERE id = :itemId")
    suspend fun getShoppingListItemById(itemId: Long): ShoppingListItem?

    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    fun getShoppingListItems(shoppingListId: Long): Flow<List<ShoppingListItem>>

    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND purchased = :purchased")
    fun getShoppingListItemsByPurchasedStatus(shoppingListId: Long, purchased: Boolean): Flow<List<ShoppingListItem>>

    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY position ASC")
    fun getShoppingListItemsByPosition(shoppingListId: Long): Flow<List<ShoppingListItem>>

    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND name LIKE '%' || :query || '%'")
    fun searchShoppingListItems(shoppingListId: Long, query: String): Flow<List<ShoppingListItem>>

    @Query("DELETE FROM shopping_list_items WHERE id = :itemId")
    suspend fun deleteShoppingListItem(itemId: Long)

    @Query("DELETE FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    suspend fun deleteAllShoppingListItems(shoppingListId: Long)

    @Query("SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND purchased = :purchased")
    suspend fun getPurchasedItemCount(shoppingListId: Long, purchased: Boolean): Int

    @Query("SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    suspend fun getTotalItemCount(shoppingListId: Long): Int

    @Query("SELECT SUM(price * quantity) FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    suspend fun getTotalPrice(shoppingListId: Long): Double
}
