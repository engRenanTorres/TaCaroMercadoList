package com.mercadolist.shoppinglist.data.repository

import com.mercadolist.shoppinglist.data.dao.SyncStateDao
import com.mercadolist.shoppinglist.data.entities.SyncState
import kotlinx.coroutines.flow.Flow

/**
 * Repository for SyncState operations.
 * Abstracts data access logic for sync tracking.
 */
class ShoppingListSyncRepository(private val syncStateDao: SyncStateDao) {
    suspend fun insertSyncState(state: SyncState): Long = syncStateDao.insertSyncState(state)

    suspend fun updateSyncState(state: SyncState) = syncStateDao.updateSyncState(state)

    fun getSyncStateByShoppingListId(shoppingListId: Long): Flow<SyncState?> = syncStateDao.getSyncStateByShoppingListId(shoppingListId)

    fun getSyncStatesByStatus(shoppingListId: Long, status: String): Flow<List<SyncState>> = syncStateDao.getSyncStatesByStatus(shoppingListId, status)

    suspend fun deleteSyncStateByShoppingListId(shoppingListId: Long) = syncStateDao.deleteSyncStateByShoppingListId(shoppingListId)

    suspend fun getSyncStateCount(shoppingListId: Long): Int = syncStateDao.getSyncStateCount(shoppingListId)
}
