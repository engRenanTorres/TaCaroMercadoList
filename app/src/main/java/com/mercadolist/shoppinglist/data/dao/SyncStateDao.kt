package com.mercadolist.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.mercadolist.shoppinglist.data.entities.SyncState

/**
 * Data Access Object for SyncState entity.
 * Provides CRUD operations and reactive data streams.
 */
@Dao
interface SyncStateDao {
    /**
     * Insert a new sync state.
     * @param syncState The sync state to insert
     * @return The inserted sync state's ID
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(syncState: SyncState): Long

    /**
     * Update an existing sync state.
     * @param syncState The sync state to update
     */
    @Update
    suspend fun update(syncState: SyncState)

    /**
     * Get sync state by shopping list ID.
     * @param shoppingListId The shopping list ID
     * @return The sync state or null if not found
     */
    @Query("SELECT * FROM sync_state WHERE shoppingListId = :shoppingListId")
    suspend fun getSyncStateByShoppingListId(shoppingListId: Long): SyncState?

    /**
     * Get all sync states as a reactive flow.
     * @return Flow of all sync states
     */
    @Query("SELECT * FROM sync_state")
    fun getAllSyncStates(): Flow<List<SyncState>>

    /**
     * Get sync states for a specific shopping list.
     * @param shoppingListId The shopping list ID
     * @return Flow of sync states for the shopping list
     */
    @Query("SELECT * FROM sync_state WHERE shoppingListId = :shoppingListId")
    fun getSyncStatesByShoppingListId(shoppingListId: Long): Flow<List<SyncState>>

    /**
     * Delete sync state by shopping list ID.
     * @param shoppingListId The shopping list ID to delete
     */
    @Query("DELETE FROM sync_state WHERE shoppingListId = :shoppingListId")
    suspend fun deleteSyncStateByShoppingListId(shoppingListId: Long)

    /**
     * Count total sync states.
     * @return The number of sync states
     */
    @Query("SELECT COUNT(*) FROM sync_state")
    suspend fun getSyncStateCount(): Int

    /**
     * Get sync states with a specific status.
     * @param status The sync status
     * @return Flow of sync states with the status
     */
    @Query("SELECT * FROM sync_state WHERE syncStatus = :status")
    fun getSyncStatesByStatus(status: String): Flow<List<SyncState>>

    /**
     * Get sync states modified by a specific user.
     * @param userId The user ID
     * @return Flow of sync states modified by the user
     */
    @Query("SELECT * FROM sync_state WHERE lastModifiedBy = :userId")
    fun getSyncStatesByLastModifiedBy(userId: Long): Flow<List<SyncState>>
}
