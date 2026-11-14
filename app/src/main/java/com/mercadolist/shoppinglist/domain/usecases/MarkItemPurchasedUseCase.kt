package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository

/**
 * Use case for marking an item as purchased.
 * Encapsulates business logic for toggling purchased status.
 */
class MarkItemPurchasedUseCase(
    private val shoppingListItemRepository: ShoppingListItemRepository
) {
    suspend fun execute(itemId: Long, purchased: Boolean) {
        val shoppingListItem = shoppingListItemRepository.getShoppingListItemById(itemId)
        if (shoppingListItem != null) {
            shoppingListItem.purchased = purchased
            shoppingListItemRepository.updateShoppingListItem(shoppingListItem)
        }
    }
}
