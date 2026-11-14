package com.mercadolist.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.mercadolist.shoppinglist.data.entities.ShoppingList

/**
 * Data Access Object for ShoppingList entity.
 * Provides CRUD operations and reactive data streams.
 */
@Dao
interface ShoppingListDao {
    /**
     * Insert a new shopping list.
     * @param shoppingList The shopping list to insert
     * @return The inserted shopping list's ID
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoppingList: ShoppingList): Long

    /**
     * Update an existing shopping list.
     * @param shoppingList The shopping list to update
     */
    @Update
    suspend fun update(shoppingList: ShoppingList)

    /**
     * Get a shopping list by ID.
     * @param shoppingListId The shopping list ID
     * @return The shopping list or null if not found
     */
    @Query("SELECT * FROM shopping_lists WHERE id = :shoppingListId")
    suspend fun getShoppingListById(shoppingListId: Long): ShoppingList?

    /**
     * Get all shopping lists for a specific user as a reactive flow.
     * @param userId The user ID
     * @return Flow of shopping lists for the user
     */
    @Query("SELECT * FROM shopping_lists WHERE userId = :userId")
    fun getShoppingListsByUserId(userId: Long): Flow<List<ShoppingList>>

    /**
     * Get all shopping lists as a reactive flow.
     * @return Flow of all shopping lists
     */
    @Query("SELECT * FROM shopping_lists")
    fun getAllShoppingLists(): Flow<List<ShoppingList>>

    /**
     * Get shopping lists by name.
     * @param name The shopping list name
     * @return Flow of shopping lists matching the name
     */
    @Query("SELECT * FROM shopping_lists WHERE name LIKE '%' || :name || '%'")
    fun getShoppingListsByName(name: String): Flow<List<ShoppingList>>

    /**
     * Delete a shopping list.
     * @param shoppingListId The shopping list ID to delete
     */
    @Query("DELETE FROM shopping_lists WHERE id = :shoppingListId")
    suspend fun deleteShoppingList(shoppingListId: Long)

    /**
     * Count total shopping lists.
     * @return The number of shopping lists
     */
    @Query("SELECT COUNT(*) FROM shopping_lists")
    suspend fun getShoppingListCount(): Int

    /**
     * Get shopping lists created after a specific timestamp.
     * @param timestamp The timestamp
     * @return Flow of shopping lists created after the timestamp
     */
    @Query("SELECT * FROM shopping_lists WHERE createdAt > :timestamp")
    fun getShoppingListsAfter(timestamp: Long): Flow<List<ShoppingList>>
}
