package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving a shopping list.
 * Encapsulates business logic for list retrieval.
 */
class GetShoppingListUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend fun execute(listId: Long): com.mercadolist.shoppinglist.data.entities.ShoppingList? {
        return shoppingListRepository.getShoppingListById(listId)
    }

    fun executeFlow(userId: Long): Flow<com.mercadolist.shoppinglist.data.entities.ShoppingList?> {
        return shoppingListRepository.getUserShoppingLists(userId)
    }
}
