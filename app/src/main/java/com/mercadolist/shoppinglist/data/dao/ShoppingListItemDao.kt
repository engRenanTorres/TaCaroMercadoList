package com.mercadolist.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem

/**
 * Data Access Object for ShoppingListItem entity.
 * Provides CRUD operations and reactive data streams.
 */
@Dao
interface ShoppingListItemDao {
    /**
     * Insert a new shopping list item.
     * @param item The shopping list item to insert
     * @return The inserted item's ID
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingListItem): Long

    /**
     * Update an existing shopping list item.
     * @param item The shopping list item to update
     */
    @Update
    suspend fun update(item: ShoppingListItem)

    /**
     * Get a shopping list item by ID.
     * @param itemId The item ID
     * @return The shopping list item or null if not found
     */
    @Query("SELECT * FROM shopping_list_items WHERE id = :itemId")
    suspend fun getShoppingListItemById(itemId: Long): ShoppingListItem?

    /**
     * Get all items for a specific shopping list as a reactive flow.
     * @param shoppingListId The shopping list ID
     * @return Flow of shopping list items
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY position ASC")
    fun getShoppingListItems(shoppingListId: Long): Flow<List<ShoppingListItem>>

    /**
     * Get all items for a specific shopping list, ordered by position.
     * @param shoppingListId The shopping list ID
     * @return Flow of shopping list items ordered by position
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY position ASC")
    fun getShoppingListItemsOrdered(shoppingListId: Long): Flow<List<ShoppingListItem>>

    /**
     * Get purchased items for a shopping list.
     * @param shoppingListId The shopping list ID
     * @return Flow of purchased items
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND purchased = true ORDER BY position ASC")
    fun getPurchasedItems(shoppingListId: Long): Flow<List<ShoppingListItem>>

    /**
     * Get to-buy items for a shopping list.
     * @param shoppingListId The shopping list ID
     * @return Flow of to-buy items
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND purchased = false ORDER BY position ASC")
    fun getToBuyItems(shoppingListId: Long): Flow<List<ShoppingListItem>>

    /**
     * Get items by category.
     * @param shoppingListId The shopping list ID
     * @param category The category name
     * @return Flow of items in the category
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND category = :category ORDER BY position ASC")
    fun getItemsByCategory(shoppingListId: Long, category: String): Flow<List<ShoppingListItem>>

    /**
     * Delete a shopping list item.
     * @param itemId The item ID to delete
     */
    @Query("DELETE FROM shopping_list_items WHERE id = :itemId")
    suspend fun deleteShoppingListItem(itemId: Long)

    /**
     * Delete all items for a shopping list.
     * @param shoppingListId The shopping list ID
     */
    @Query("DELETE FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    suspend fun deleteAllShoppingListItems(shoppingListId: Long)

    /**
     * Count total items for a shopping list.
     * @param shoppingListId The shopping list ID
     * @return The number of items
     */
    @Query("SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    suspend fun getShoppingListItemCount(shoppingListId: Long): Int

    /**
     * Get items with a specific name.
     * @param shoppingListId The shopping list ID
     * @param name The item name
     * @return Flow of items matching the name
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND name LIKE '%' || :name || '%' ORDER BY position ASC")
    fun getItemsByName(shoppingListId: Long, name: String): Flow<List<ShoppingListItem>>

    /**
     * Get items with a specific price range.
     * @param shoppingListId The shopping list ID
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
     * @return Flow of items in the price range
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND price BETWEEN :minPrice AND :maxPrice ORDER BY position ASC")
    fun getItemsByPriceRange(shoppingListId: Long, minPrice: Double, maxPrice: Double): Flow<List<ShoppingListItem>>

    /**
     * Get items with a specific quantity.
     * @param shoppingListId The shopping list ID
     * @param quantity The quantity
     * @return Flow of items with the quantity
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND quantity = :quantity ORDER BY position ASC")
    fun getItemsByQuantity(shoppingListId: Long, quantity: Int): Flow<List<ShoppingListItem>>

    /**
     * Get items with a specific purchased status.
     * @param shoppingListId The shopping list ID
     * @param purchased The purchased status
     * @return Flow of items with the status
     */
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND purchased = :purchased ORDER BY position ASC")
    fun getItemsByPurchasedStatus(shoppingListId: Long, purchased: Boolean): Flow<List<ShoppingListItem>>
}
