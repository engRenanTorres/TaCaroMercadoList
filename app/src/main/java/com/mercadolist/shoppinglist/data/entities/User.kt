package com.mercadolist.shoppinglist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * User entity representing a user in the shopping list app.
 * Supports multiple users collaborating on shared lists.
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val avatar: String? = null
)
