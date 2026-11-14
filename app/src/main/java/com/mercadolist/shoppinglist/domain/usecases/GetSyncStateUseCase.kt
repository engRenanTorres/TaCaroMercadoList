package com.mercadolist.shoppinglist.domain.usecases

import com.mercadolist.shoppinglist.data.repository.ShoppingListSyncRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving sync state.
 * Encapsulates business logic for sync tracking.
 */
class GetSyncStateUseCase(
    private val shoppingListSyncRepository: ShoppingListSyncRepository
) {
    fun execute(shoppingListId: Long): Flow<com.mercadolist.shoppinglist.data.entities.SyncState?> {
        return shoppingListSyncRepository.getSyncStateByShoppingListId(shoppingListId)
    }
}
