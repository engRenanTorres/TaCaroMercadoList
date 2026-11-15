package com.mercadolist.shoppinglist.data.repository

import kotlinx.coroutines.flow.Flow
import com.mercadolist.shoppinglist.data.dao.ShoppingListItemDao
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem

/**
 * Repository for ShoppingListItem entity operations.
 * Provides a clean interface between data layer and presentation layer.
 */
class ShoppingListItemRepository(private val shoppingListItemDao: ShoppingListItemDao) {
    
    /**
     * Insert a new shopping list item.
     * @param item The shopping list item to insert
     * @return The inserted item's ID
     */
    suspend fun insertItem(item: ShoppingListItem): Long = shoppingListItemDao.insert(item)
    
    /**
     * Update an existing shopping list item.
     * @param item The shopping list item to update
     */
    suspend fun updateItem(item: ShoppingListItem) = shoppingListItemDao.update(item)
    
    /**
     * Get a shopping list item by ID.
     * @param itemId The item ID
     * @return The shopping list item or null if not found
     */
    suspend fun getShoppingListItemById(itemId: Long): ShoppingListItem? = shoppingListItemDao.getShoppingListItemById(itemId)
    
    /**
     * Get all items for a specific shopping list as a reactive flow.
     * @param shoppingListId The shopping list ID
     * @return Flow of shopping list items
     */
    fun getShoppingListItems(shoppingListId: Long): Flow<List<ShoppingListItem>> = shoppingListItemDao.getShoppingListItems(shoppingListId)
    
    /**
     * Get all items for a specific shopping list, ordered by position.
     * @param shoppingListId The shopping list ID
     * @return Flow of shopping list items ordered by position
     */
    fun getShoppingListItemsOrdered(shoppingListId: Long): Flow<List<ShoppingListItem>> = shoppingListItemDao.getShoppingListItemsOrdered(shoppingListId)
    
    /**
     * Get purchased items for a shopping list.
     * @param shoppingListId The shopping list ID
     * @return Flow of purchased items
     */
    fun getPurchasedItems(shoppingListId: Long): Flow<List<ShoppingListItem>> = shoppingListItemDao.getPurchasedItems(shoppingListId)
    
    /**
     * Get to-buy items for a shopping list.
     * @param shoppingListId The shopping list ID
     * @return Flow of to-buy items
     */
    fun getToBuyItems(shoppingListId: Long): Flow<List<ShoppingListItem>> = shoppingListItemDao.getToBuyItems(shoppingListId)
    
    /**
     * Get items by category.
     * @param shoppingListId The shopping list ID
     * @param category The category name
     * @return Flow of items in the category
     */
    fun getItemsByCategory(shoppingListId: Long, category: String): Flow<List<ShoppingListItem>> = shoppingListItemDao.getItemsByCategory(shoppingListId, category)
    
    /**
     * Delete a shopping list item.
     * @param itemId The item ID to delete
     */
    suspend fun deleteShoppingListItem(itemId: Long) = shoppingListItemDao.deleteShoppingListItem(itemId)
    
    /**
     * Delete all items for a shopping list.
     * @param shoppingListId The shopping list ID
     */
    suspend fun deleteAllShoppingListItems(shoppingListId: Long) = shoppingListItemDao.deleteAllShoppingListItems(shoppingListId)
    
    /**
     * Count total items for a shopping list.
     * @param shoppingListId The shopping list ID
     * @return The number of items
     */
    suspend fun getShoppingListItemCount(shoppingListId: Long): Int = shoppingListItemDao.getShoppingListItemCount(shoppingListId)
    
    /**
     * Get items with a specific name.
     * @param shoppingListId The shopping list ID
     * @param name The item name
     * @return Flow of items matching the name
     */
    fun getItemsByName(shoppingListId: Long, name: String): Flow<List<ShoppingListItem>> = shoppingListItemDao.getItemsByName(shoppingListId, name)
    
    /**
     * Get items with a specific price range.
     * @param shoppingListId The shopping list ID
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
     * @return Flow of items in the price range
     */
    fun getItemsByPriceRange(shoppingListId: Long, minPrice: Double, maxPrice: Double): Flow<List<ShoppingListItem>> = shoppingListItemDao.getItemsByPriceRange(shoppingListId, minPrice, maxPrice)
    
    /**
     * Get items with a specific quantity.
     * @param shoppingListId The shopping list ID
     * @param quantity The quantity
     * @return Flow of items with the quantity
     */
    fun getItemsByQuantity(shoppingListId: Long, quantity: Int): Flow<List<ShoppingListItem>> = shoppingListItemDao.getItemsByQuantity(shoppingListId, quantity)
    
    /**
     * Get items with a specific purchased status.
     * @param shoppingListId The shopping list ID
     * @param purchased The purchased status
     * @return Flow of items with the status
     */
    fun getItemsByPurchasedStatus(shoppingListId: Long, purchased: Boolean): Flow<List<ShoppingListItem>> = shoppingListItemDao.getItemsByPurchasedStatus(shoppingListId, purchased)
}