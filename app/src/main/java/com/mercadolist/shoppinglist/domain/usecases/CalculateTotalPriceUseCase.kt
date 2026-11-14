package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository

/**
 * Use case for calculating total price of a shopping list.
 * Encapsulates business logic for price calculations.
 */
class CalculateTotalPriceUseCase(
    private val shoppingListItemRepository: ShoppingListItemRepository
) {
    suspend fun execute(shoppingListId: Long): Double {
        return shoppingListItemRepository.getTotalPrice(shoppingListId)
    }
}
