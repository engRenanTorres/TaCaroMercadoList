package com.mercadolist.shoppinglist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user in the shopping list application.
 * Supports multiple users collaborating on the same shopping list.
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val avatar: String? = null
)
