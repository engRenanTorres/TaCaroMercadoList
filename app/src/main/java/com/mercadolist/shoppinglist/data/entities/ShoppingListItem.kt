package com.mercadolist.shoppinglist.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * ShoppingListItem entity representing an item in a shopping list.
 * Supports quantity, price, category, purchased status, and position.
 */
@Entity(
    tableName = "shopping_list_items",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingList::class,
            parentColumns = ["id"],
            childColumns = ["shoppingListId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("shoppingListId"),
        Index("position")
    ]
)
data class ShoppingListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val shoppingListId: Long,
    val name: String,
    val quantity: Int = 1,
    val price: Double = 0.0,
    val category: String? = null,
    val purchased: Boolean = false,
    val position: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
