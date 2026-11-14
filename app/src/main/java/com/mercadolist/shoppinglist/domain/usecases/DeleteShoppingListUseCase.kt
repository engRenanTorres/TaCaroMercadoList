package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.repository.ShoppingListRepository

/**
 * Use case for deleting a shopping list.
 * Encapsulates business logic for list deletion.
 */
class DeleteShoppingListUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend fun execute(listId: Long) {
        shoppingListRepository.deleteShoppingList(listId)
    }
}
