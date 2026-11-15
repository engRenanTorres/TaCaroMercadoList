package com.mercadolist.shoppinglist.data.repository

import kotlinx.coroutines.flow.Flow
import com.mercadolist.shoppinglist.data.dao.ShoppingListDao
import com.mercadolist.shoppinglist.data.entities.ShoppingList

/**
 * Repository for ShoppingList entity operations.
 * Provides a clean interface between data layer and presentation layer.
 */
class ShoppingListRepository(private val shoppingListDao: ShoppingListDao) {
    
    /**
     * Insert a new shopping list.
     * @param shoppingList The shopping list to insert
     * @return The inserted shopping list's ID
     */
    suspend fun insertShoppingList(shoppingList: ShoppingList): Long = shoppingListDao.insert(shoppingList)
    
    /**
     * Update an existing shopping list.
     * @param shoppingList The shopping list to update
     */
    suspend fun updateShoppingList(shoppingList: ShoppingList) = shoppingListDao.update(shoppingList)
    
    /**
     * Get a shopping list by ID.
     * @param shoppingListId The shopping list ID
     * @return The shopping list or null if not found
     */
    suspend fun getShoppingListById(shoppingListId: Long): ShoppingList? = shoppingListDao.getShoppingListById(shoppingListId)
    
    /**
     * Get all shopping lists for a specific user as a reactive flow.
     * @param userId The user ID
     * @return Flow of shopping lists for the user
     */
    fun getShoppingListsByUserId(userId: Long): Flow<List<ShoppingList>> = shoppingListDao.getShoppingListsByUserId(userId)
    
    /**
     * Get all shopping lists as a reactive flow.
     * @return Flow of all shopping lists
     */
    fun getAllShoppingLists(): Flow<List<ShoppingList>> = shoppingListDao.getAllShoppingLists()
    
    /**
     * Get shopping lists by name.
     * @param name The shopping list name
     * @return Flow of shopping lists matching the name
     */
    fun getShoppingListsByName(name: String): Flow<List<ShoppingList>> = shoppingListDao.getShoppingListsByName(name)
    
    /**
     * Delete a shopping list.
     * @param shoppingListId The shopping list ID to delete
     */
    suspend fun deleteShoppingList(shoppingListId: Long) = shoppingListDao.deleteShoppingList(shoppingListId)
    
    /**
     * Count total shopping lists.
     * @return The number of shopping lists
     */
    suspend fun getShoppingListCount(): Int = shoppingListDao.getShoppingListCount()
    
    /**
     * Get shopping lists created after a specific timestamp.
     * @param timestamp The timestamp
     * @return Flow of shopping lists created after the timestamp
     */
    fun getShoppingListsAfter(timestamp: Long): Flow<List<ShoppingList>> = shoppingListDao.getShoppingListsAfter(timestamp)
}