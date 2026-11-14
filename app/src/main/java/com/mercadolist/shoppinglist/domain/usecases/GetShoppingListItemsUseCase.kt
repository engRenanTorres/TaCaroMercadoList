package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving shopping list items.
 * Encapsulates business logic for item retrieval with various filters.
 */
class GetShoppingListItemsUseCase(
    private val shoppingListItemRepository: ShoppingListItemRepository
) {
    fun execute(shoppingListId: Long): Flow<com.mercadolist.shoppinglist.data.entities.ShoppingListItem> {
        return shoppingListItemRepository.getShoppingListItems(shoppingListId)
    }

    fun executeByPurchasedStatus(shoppingListId: Long, purchased: Boolean): Flow<com.mercadolist.shoppinglist.data.entities.ShoppingListItem> {
        return shoppingListItemRepository.getShoppingListItemsByPurchasedStatus(shoppingListId, purchased)
    }

    fun executeByPosition(shoppingListId: Long): Flow<com.mercadolist.shoppinglist.data.entities.ShoppingListItem> {
        return shoppingListItemRepository.getShoppingListItemsByPosition(shoppingListId)
    }

    fun executeSearch(shoppingListId: Long, query: String): Flow<com.mercadolist.shoppinglist.data.entities.ShoppingListItem> {
        return shoppingListItemRepository.searchShoppingListItems(shoppingListId, query)
    }
}
