package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository

/**
 * Use case for reordering items within a shopping list.
 * Encapsulates business logic for item position updates.
 */
class ReorderItemUseCase(
    private val shoppingListItemRepository: ShoppingListItemRepository
) {
    suspend fun execute(itemId: Long, newPosition: Int) {
        val shoppingListItem = shoppingListItemRepository.getShoppingListItemById(itemId)
        if (shoppingListItem != null) {
            shoppingListItem.position = newPosition
            shoppingListItemRepository.updateShoppingListItem(shoppingListItem)
        }
    }
}
