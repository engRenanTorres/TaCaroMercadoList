package com.mercadolist.shoppinglist.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * ShoppingList entity representing a shopping list.
 * Each list can have multiple items and can be shared with multiple users.
 */
@Entity(
    tableName = "shopping_lists",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("userId")
    ]
)
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val userId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
