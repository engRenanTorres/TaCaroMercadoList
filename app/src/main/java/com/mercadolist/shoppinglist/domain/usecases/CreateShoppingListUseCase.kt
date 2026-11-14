package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.repository.ShoppingListRepository

/**
 * Use case for creating a new shopping list.
 * Encapsulates business logic for list creation.
 */
class CreateShoppingListUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend fun execute(name: String, userId: Long): ShoppingList {
        val shoppingList = ShoppingList(
            name = name,
            userId = userId
        )
        return shoppingListRepository.insertShoppingList(shoppingList)
    }
}
