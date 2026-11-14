package com.mercadolist.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * DAO for SyncState entity operations.
 * Provides reactive data streams using Flow.
 */
@Dao
interface SyncStateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSyncState(state: SyncState): Long

    @Update
    suspend fun updateSyncState(state: SyncState)

    @Query("SELECT * FROM sync_states WHERE shoppingListId = :shoppingListId")
    fun getSyncStateByShoppingListId(shoppingListId: Long): Flow<SyncState?>

    @Query("SELECT * FROM sync_states WHERE shoppingListId = :shoppingListId AND syncStatus = :status")
    fun getSyncStatesByStatus(shoppingListId: Long, status: String): Flow<List<SyncState>>

    @Query("DELETE FROM sync_states WHERE shoppingListId = :shoppingListId")
    suspend fun deleteSyncStateByShoppingListId(shoppingListId: Long)

    @Query("SELECT COUNT(*) FROM sync_states WHERE shoppingListId = :shoppingListId")
    suspend fun getSyncStateCount(shoppingListId: Long): Int
}
