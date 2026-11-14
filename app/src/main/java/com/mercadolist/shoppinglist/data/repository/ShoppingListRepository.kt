package com.mercadolist.shoppinglist.data.repository

import com.mercadolist.shoppinglist.data.dao.ShoppingListDao
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import kotlinx.coroutines.flow.Flow

/**
 * Repository for ShoppingList operations.
 * Abstracts data access logic from the domain layer.
 */
class ShoppingListRepository(private val shoppingListDao: ShoppingListDao) {
    suspend fun insertShoppingList(list: ShoppingList): Long = shoppingListDao.insertShoppingList(list)

    suspend fun updateShoppingList(list: ShoppingList) = shoppingListDao.updateShoppingList(list)

    suspend fun getShoppingListById(listId: Long): ShoppingList? = shoppingListDao.getShoppingListById(listId)

    fun getUserShoppingLists(userId: Long): Flow<List<ShoppingList>> = shoppingListDao.getUserShoppingLists(userId)

    fun getAllShoppingLists(): Flow<List<ShoppingList>> = shoppingListDao.getAllShoppingLists()

    suspend fun deleteShoppingList(listId: Long) = shoppingListDao.deleteShoppingList(listId)

    suspend fun getUserShoppingListCount(userId: Long): Int = shoppingListDao.getUserShoppingListCount(userId)

    fun searchShoppingLists(query: String): Flow<List<ShoppingList>> = shoppingListDao.searchShoppingLists(query)
}
