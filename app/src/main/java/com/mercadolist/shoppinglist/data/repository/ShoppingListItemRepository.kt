package com.mercadolist.shoppinglist.data.repository

import com.mercadolist.shoppinglist.data.dao.ShoppingListItemDao
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import kotlinx.coroutines.flow.Flow

/**
 * Repository for ShoppingListItem operations.
 * Abstracts data access logic from the domain layer.
 */
class ShoppingListItemRepository(private val shoppingListItemDao: ShoppingListItemDao) {
    suspend fun insertShoppingListItem(item: ShoppingListItem): Long = shoppingListItemDao.insertShoppingListItem(item)

    suspend fun updateShoppingListItem(item: ShoppingListItem) = shoppingListItemDao.updateShoppingListItem(item)

    suspend fun getShoppingListItemById(itemId: Long): ShoppingListItem? = shoppingListItemDao.getShoppingListItemById(itemId)

    fun getShoppingListItems(shoppingListId: Long): Flow<List<ShoppingListItem>> = shoppingListItemDao.getShoppingListItems(shoppingListId)

    fun getShoppingListItemsByPurchasedStatus(shoppingListId: Long, purchased: Boolean): Flow<List<ShoppingListItem>> = shoppingListItemDao.getShoppingListItemsByPurchasedStatus(shoppingListId, purchased)

    fun getShoppingListItemsByPosition(shoppingListId: Long): Flow<List<ShoppingListItem>> = shoppingListItemDao.getShoppingListItemsByPosition(shoppingListId)

    fun searchShoppingListItems(shoppingListId: Long, query: String): Flow<List<ShoppingListItem>> = shoppingListItemDao.searchShoppingListItems(shoppingListId, query)

    suspend fun deleteShoppingListItem(itemId: Long) = shoppingListItemDao.deleteShoppingListItem(itemId)

    suspend fun deleteAllShoppingListItems(shoppingListId: Long) = shoppingListItemDao.deleteAllShoppingListItems(shoppingListId)

    suspend fun getPurchasedItemCount(shoppingListId: Long, purchased: Boolean): Int = shoppingListItemDao.getPurchasedItemCount(shoppingListId, purchased)

    suspend fun getTotalItemCount(shoppingListId: Long): Int = shoppingListItemDao.getTotalItemCount(shoppingListId)

    suspend fun getTotalPrice(shoppingListId: Long): Double = shoppingListItemDao.getTotalPrice(shoppingListId)
}
