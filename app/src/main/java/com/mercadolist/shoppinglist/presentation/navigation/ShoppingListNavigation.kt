package com.mercadolist.shoppinglist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mercadolist.shoppinglist.presentation.screens.ShoppingListScreen
import com.mercadolist.shoppinglist.data.ShoppingListDatabaseInitializer

/**
 * Navigation setup for ShoppingList app.
 * Defines routes and screens.
 */
object ShoppingListNavigation {
    const val ROUTE_SHOPPING_LIST = "shopping_list"
}

@Composable
fun ShoppingListNavHost(
    navController: NavHostController,
    userId: Long
) {
    NavHost(
        navController = navController,
        startDestination = ShoppingListNavigation.ROUTE_SHOPPING_LIST
    ) {
        composable(ShoppingListNavigation.ROUTE_SHOPPING_LIST) {
            ShoppingListScreen(
                viewModel = ShoppingListViewModel(
                    shoppingListRepository = com.mercadolist.shoppinglist.data.repository.ShoppingListRepository(
                        ShoppingListDatabaseInitializer.getShoppingListDao(navController.context)
                    ),
                    shoppingListItemRepository = com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository(
                        ShoppingListDatabaseInitializer.getShoppingListItemDao(navController.context)
                    ),
                    shoppingListSyncRepository = com.mercadolist.shoppinglist.data.repository.ShoppingListSyncRepository(
                        ShoppingListDatabaseInitializer.getSyncStateDao(navController.context)
                    ),
                    createShoppingListUseCase = com.mercadolist.shoppinglist.domain.usecases.CreateShoppingListUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListRepository(
                        ShoppingListDatabaseInitializer.getShoppingListDao(navController.context)
                    ),
                    deleteShoppingListUseCase = com.mercadolist.shoppinglist.domain.usecases.DeleteShoppingListUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListRepository(
                        ShoppingListDatabaseInitializer.getShoppingListDao(navController.context)
                    ),
                    addShoppingListItemUseCase = com.mercadolist.shoppinglist.domain.usecases.AddShoppingListItemUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository(
                        ShoppingListDatabaseInitializer.getShoppingListItemDao(navController.context)
                    ),
                    deleteShoppingListItemUseCase = com.mercadolist.shoppinglist.domain.usecases.DeleteShoppingListItemUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository(
                        ShoppingListDatabaseInitializer.getShoppingListItemDao(navController.context)
                    ),
                    markItemPurchasedUseCase = com.mercadolist.shoppinglist.domain.usecases.MarkItemPurchasedUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository(
                        ShoppingListDatabaseInitializer.getShoppingListItemDao(navController.context)
                    ),
                    reorderItemUseCase = com.mercadolist.shoppinglist.domain.usecases.ReorderItemUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository(
                        ShoppingListDatabaseInitializer.getShoppingListItemDao(navController.context)
                    ),
                    updateItemDetailsUseCase = com.mercadolist.shoppinglist.domain.usecases.UpdateItemDetailsUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository(
                        ShoppingListDatabaseInitializer.getShoppingListItemDao(navController.context)
                    ),
                    calculateTotalPriceUseCase = com.mercadolist.shoppinglist.domain.usecases.CalculateTotalPriceUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository(
                        ShoppingListDatabaseInitializer.getShoppingListItemDao(navController.context)
                    ),
                    getShoppingListUseCase = com.mercadolist.shoppinglist.domain.usecases.GetShoppingListUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListRepository(
                        ShoppingListDatabaseInitializer.getShoppingListDao(navController.context)
                    ),
                    getShoppingListItemsUseCase = com.mercadolist.shoppinglist.domain.usecases.GetShoppingListItemsUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository(
                        ShoppingListDatabaseInitializer.getShoppingListItemDao(navController.context)
                    ),
                    getSyncStateUseCase = com.mercadolist.shoppinglist.domain.usecases.GetSyncStateUseCase(
                        com.mercadolist.shoppinglist.data.repository.ShoppingListSyncRepository(
                        ShoppingListDatabaseInitializer.getSyncStateDao(navController.context)
                    ),
                    userId = userId
                ),
                userId = userId
            )
        }
    }
}
