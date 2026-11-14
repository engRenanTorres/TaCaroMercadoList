package com.mercadolist.shoppinglist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * SyncState entity tracking synchronization status and timestamps.
 * Used for offline-first sync and peer-to-peer sync tracking.
 */
@Entity(tableName = "sync_states")
data class SyncState(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val shoppingListId: Long,
    val lastSyncedAt: LocalDateTime = LocalDateTime.now(),
    val syncStatus: String = "synced",
    val syncMethod: String = "local"
)
