package com.mercadolist.shoppinglist.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.presentation.viewmodels.ShoppingListViewModel

/**
 * Main ShoppingList screen UI.
 * Displays shopping list items with Material Design 3 components.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel,
    userId: Long
) {
    val shoppingList by viewModel.shoppingList.collectAsState()
    val shoppingListItems by viewModel.shoppingListItems.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()
    val syncStatus by viewModel.syncStatus.collectAsState()
    val editMode by viewModel.editMode.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(shoppingList?.name ?: "Shopping List") },
                actions = {
                    IconButton(onClick = { viewModel.toggleEditMode() }) {
                        Icon(
                            imageVector = MaterialIcons.Default.Edit,
                            contentDescription = "Edit Mode"
                        )
                    }
                    IconButton(onClick = { viewModel.loadShoppingList() }) {
                        Icon(
                            imageVector = MaterialIcons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Sync status indicator
            if (syncStatus != "synced") {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text(
                        text = "Sync Status: $syncStatus",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Total price display
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Price",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "R$ ${totalPrice.toString()}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Edit mode indicator
            if (editMode) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Text(
                        text = "Edit Mode Enabled",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Shopping list items
            if (shoppingListItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No items in this list",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(shoppingListItems) { item ->
                        ShoppingListItemCard(
                            item = item,
                            editMode = editMode,
                            viewModel = viewModel
                        )
                    }
                }
            }

            // Error message
            if (error != null) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    snackbarData = SnackbarData(
                        message = error ?: "Unknown error"
                    )
                )
            }
        }
    }
}

@Composable
fun ShoppingListItemCard(
    item: ShoppingListItem,
    editMode: Boolean,
    viewModel: ShoppingListViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Normal
                )
                if (item.purchased) {
                    Text(
                        text = "(Purchased)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                if (item.category != null) {
                    Text(
                        text = "Category: ${item.category}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Qty: ${item.quantity}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "R$ ${item.price}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
