package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository

/**
 * Use case for deleting a shopping list item.
 * Encapsulates business logic for item deletion.
 */
class DeleteShoppingListItemUseCase(
    private val shoppingListItemRepository: ShoppingListItemRepository
) {
    suspend fun execute(itemId: Long) {
        shoppingListItemRepository.deleteShoppingListItem(itemId)
    }
}
