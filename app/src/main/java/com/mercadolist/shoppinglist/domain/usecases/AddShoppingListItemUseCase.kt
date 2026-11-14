package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository

/**
 * Use case for adding a new shopping list item.
 * Encapsulates business logic for item creation.
 */
class AddShoppingListItemUseCase(
    private val shoppingListItemRepository: ShoppingListItemRepository
) {
    suspend fun execute(
        shoppingListId: Long,
        name: String,
        quantity: Int = 1,
        price: Double = 0.0,
        category: String? = null,
        purchased: Boolean = false
    ): ShoppingListItem {
        val shoppingListItem = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = name,
            quantity = quantity,
            price = price,
            category = category,
            purchased = purchased,
            position = 0
        )
        return shoppingListItemRepository.insertShoppingListItem(shoppingListItem)
    }
}
