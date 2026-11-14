package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository

/**
 * Use case for updating item details (name, quantity, price, category).
 * Encapsulates business logic for item modifications.
 */
class UpdateItemDetailsUseCase(
    private val shoppingListItemRepository: ShoppingListItemRepository
) {
    suspend fun execute(
        itemId: Long,
        name: String? = null,
        quantity: Int? = null,
        price: Double? = null,
        category: String? = null
    ) {
        val shoppingListItem = shoppingListItemRepository.getShoppingListItemById(itemId)
        if (shoppingListItem != null) {
            name?.let { shoppingListItem.name = it }
            quantity?.let { shoppingListItem.quantity = it }
            price?.let { shoppingListItem.price = it }
            category?.let { shoppingListItem.category = it }
            shoppingListItemRepository.updateShoppingListItem(shoppingListItem)
        }
    }
}
