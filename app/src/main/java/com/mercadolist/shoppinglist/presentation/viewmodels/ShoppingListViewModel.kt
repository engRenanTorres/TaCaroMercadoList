package com.mercadolist.shoppinglist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.repository.ShoppingListRepository
import com.mercadolist.shoppinglist.data.repository.ShoppingListItemRepository
import com.mercadolist.shoppinglist.data.repository.ShoppingListSyncRepository
import com.mercadolist.shoppinglist.domain.usecases.CreateShoppingListUseCase
import com.mercadolist.shoppinglist.domain.usecases.DeleteShoppingListUseCase
import com.mercadolist.shoppinglist.domain.usecases.AddShoppingListItemUseCase
import com.mercadolist.shoppinglist.domain.usecases.DeleteShoppingListItemUseCase
import com.mercadolist.shoppinglist.domain.usecases.MarkItemPurchasedUseCase
import com.mercadolist.shoppinglist.domain.usecases.ReorderItemUseCase
import com.mercadolist.shoppinglist.domain.usecases.UpdateItemDetailsUseCase
import com.mercadolist.shoppinglist.domain.usecases.CalculateTotalPriceUseCase
import com.mercadolist.shoppinglist.domain.usecases.GetShoppingListUseCase
import com.mercadolist.shoppinglist.domain.usecases.GetShoppingListItemsUseCase
import com.mercadolist.shoppinglist.domain.usecases.GetSyncStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for ShoppingList screen.
 * Manages UI state and coordinates between domain and data layers.
 */
class ShoppingListViewModel(
    private val shoppingListRepository: ShoppingListRepository,
    private val shoppingListItemRepository: ShoppingListItemRepository,
    private val shoppingListSyncRepository: ShoppingListSyncRepository,
    private val createShoppingListUseCase: CreateShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase,
    private val addShoppingListItemUseCase: AddShoppingListItemUseCase,
    private val deleteShoppingListItemUseCase: DeleteShoppingListItemUseCase,
    private val markItemPurchasedUseCase: MarkItemPurchasedUseCase,
    private val reorderItemUseCase: ReorderItemUseCase,
    private val updateItemDetailsUseCase: UpdateItemDetailsUseCase,
    private val calculateTotalPriceUseCase: CalculateTotalPriceUseCase,
    private val getShoppingListUseCase: GetShoppingListUseCase,
    private val getShoppingListItemsUseCase: GetShoppingListItemsUseCase,
    private val getSyncStateUseCase: GetSyncStateUseCase
) : ViewModel() {

    private val _shoppingList = MutableStateFlow<ShoppingList?>(null)
    val shoppingList: StateFlow<ShoppingList?> = _shoppingList.asStateFlow()

    private val _shoppingListItems = MutableStateFlow<List<ShoppingListItem>>(emptyList())
    val shoppingListItems: StateFlow<List<ShoppingListItem>> = _shoppingListItems.asStateFlow()

    private val _totalPrice = MutableStateFlow<Double>(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice.asStateFlow()

    private val _syncStatus = MutableStateFlow<String>("synced")
    val syncStatus: StateFlow<String> = _syncStatus.asStateFlow()

    private val _editMode = MutableStateFlow(false)
    val editMode: StateFlow<Boolean> = _editMode.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadShoppingList()
    }

    fun loadShoppingList() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val shoppingList = getShoppingListUseCase.executeFlow(userId)
                shoppingList?.let {
                    _shoppingList.value = it
                    loadShoppingListItems(it.id)
                    calculateTotalPrice(it.id)
                    loadSyncStatus(it.id)
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadShoppingListItems(shoppingListId: Long) {
        viewModelScope.launch {
            try {
                _shoppingListItems.value = getShoppingListItemsUseCase.execute(shoppingListId)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun calculateTotalPrice(shoppingListId: Long) {
        viewModelScope.launch {
            try {
                _totalPrice.value = calculateTotalPriceUseCase.execute(shoppingListId)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun loadSyncStatus(shoppingListId: Long) {
        viewModelScope.launch {
            try {
                getSyncStateUseCase.execute(shoppingListId).collect { syncState ->
                    _syncStatus.value = syncState?.syncStatus ?: "synced"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun createShoppingList(name: String, userId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val shoppingList = createShoppingListUseCase.execute(name, userId)
                _shoppingList.value = shoppingList
                loadShoppingListItems(shoppingList.id)
                calculateTotalPrice(shoppingList.id)
                loadSyncStatus(shoppingList.id)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteShoppingList(listId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                deleteShoppingListUseCase.execute(listId)
                _shoppingList.value = null
                _shoppingListItems.value = emptyList()
                _totalPrice.value = 0.0
                _syncStatus.value = "synced"
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addShoppingListItem(
        shoppingListId: Long,
        name: String,
        quantity: Int = 1,
        price: Double = 0.0,
        category: String? = null,
        purchased: Boolean = false
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val shoppingListItem = addShoppingListItemUseCase.execute(
                    shoppingListId,
                    name,
                    quantity,
                    price,
                    category,
                    purchased
                )
                loadShoppingListItems(shoppingListId)
                calculateTotalPrice(shoppingListId)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteShoppingListItem(itemId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                deleteShoppingListItemUseCase.execute(itemId)
                loadShoppingListItems(_shoppingList.value?.id ?: 0)
                calculateTotalPrice(_shoppingList.value?.id ?: 0)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun markItemPurchased(itemId: Long, purchased: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                markItemPurchasedUseCase.execute(itemId, purchased)
                loadShoppingListItems(_shoppingList.value?.id ?: 0)
                calculateTotalPrice(_shoppingList.value?.id ?: 0)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun reorderItem(itemId: Long, newPosition: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                reorderItemUseCase.execute(itemId, newPosition)
                loadShoppingListItems(_shoppingList.value?.id ?: 0)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateItemDetails(
        itemId: Long,
        name: String? = null,
        quantity: Int? = null,
        price: Double? = null,
        category: String? = null
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                updateItemDetailsUseCase.execute(itemId, name, quantity, price, category)
                loadShoppingListItems(_shoppingList.value?.id ?: 0)
                calculateTotalPrice(_shoppingList.value?.id ?: 0)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleEditMode() {
        _editMode.value = !_editMode.value
    }

    fun clearError() {
        _error.value = null
    }
}
