package com.mercadolist.shoppinglist.data.repository

import kotlinx.coroutines.flow.Flow
import com.mercadolist.shoppinglist.data.dao.SyncStateDao
import com.mercadolist.shoppinglist.data.entities.SyncState

/**
 * Repository for SyncState entity operations.
 * Provides a clean interface between data layer and presentation layer.
 */
class SyncStateRepository(private val syncStateDao: SyncStateDao) {
    
    /**
     * Insert a new sync state.
     * @param syncState The sync state to insert
     * @return The inserted sync state's ID
     */
    suspend fun insertSyncState(syncState: SyncState): Long = syncStateDao.insert(syncState)
    
    /**
     * Update an existing sync state.
     * @param syncState The sync state to update
     */
    suspend fun updateSyncState(syncState: SyncState) = syncStateDao.update(syncState)
    
    /**
     * Get sync state by shopping list ID.
     * @param shoppingListId The shopping list ID
     * @return The sync state or null if not found
     */
    suspend fun getSyncStateByShoppingListId(shoppingListId: Long): SyncState? = syncStateDao.getSyncStateByShoppingListId(shoppingListId)
    
    /**
     * Get all sync states as a reactive flow.
     * @return Flow of all sync states
     */
    fun getAllSyncStates(): Flow<List<SyncState>> = syncStateDao.getAllSyncStates()
    
    /**
     * Get sync states for a specific shopping list.
     * @param shoppingListId The shopping list ID
     * @return Flow of sync states for the shopping list
     */
    fun getSyncStatesByShoppingListId(shoppingListId: Long): Flow<List<SyncState>> = syncStateDao.getSyncStatesByShoppingListId(shoppingListId)
    
    /**
     * Delete sync state by shopping list ID.
     * @param shoppingListId The shopping list ID to delete
     */
    suspend fun deleteSyncStateByShoppingListId(shoppingListId: Long) = syncStateDao.deleteSyncStateByShoppingListId(shoppingListId)
    
    /**
     * Count total sync states.
     * @return The number of sync states
     */
    suspend fun getSyncStateCount(): Int = syncStateDao.getSyncStateCount()
    
    /**
     * Get sync states with a specific status.
     * @param status The sync status
     * @return Flow of sync states with the status
     */
    fun getSyncStatesByStatus(status: String): Flow<List<SyncState>> = syncStateDao.getSyncStatesByStatus(status)
    
    /**
     * Get sync states modified by a specific user.
     * @param userId The user ID
     * @return Flow of sync states modified by the user
     */
    fun getSyncStatesByLastModifiedBy(userId: Long): Flow<List<SyncState>> = syncStateDao.getSyncStatesByLastModifiedBy(userId)
}