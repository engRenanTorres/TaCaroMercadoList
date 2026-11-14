package com.mercadolist.shoppinglist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tracks sync status and timestamps for shopping lists.
 * Used for offline-first sync tracking and conflict resolution.
 */
@Entity(tableName = "sync_state")
data class SyncState(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val shoppingListId: Long,
    val lastSyncedAt: Long = System.currentTimeMillis(),
    val syncStatus: String = "synced",
    val lastModifiedBy: Long? = null
)
