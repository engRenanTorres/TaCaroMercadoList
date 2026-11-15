package com.mercadolist.shoppinglist.data.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.mercadolist.shoppinglist.data.database.ShoppingListDatabase
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.entities.User

@RunWith(AndroidJUnit4::class)
class ShoppingListItemDaoTest {
    private lateinit var database: ShoppingListDatabase
    private lateinit var shoppingListItemDao: ShoppingListItemDao
    private lateinit var shoppingListDao: ShoppingListDao
    private lateinit var userDao: UserDao
    
    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ShoppingListDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        
        shoppingListItemDao = database.shoppingListItemDao()
        shoppingListDao = database.shoppingListDao()
        userDao = database.userDao()
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun insertAndReadShoppingListItem() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert a shopping list item
        val shoppingListItem = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = "Test Item",
            quantity = 2,
            price = 5.99,
            category = "Groceries"
        )
        val insertedId = shoppingListItemDao.insert(shoppingListItem)
        
        // Read the shopping list item back
        val retrievedItem = shoppingListItemDao.getShoppingListItemById(insertedId)
        
        // Verify the data matches
        assertEquals("Test Item", retrievedItem?.name)
        assertEquals(2, retrievedItem?.quantity)
        assertEquals(5.99, retrievedItem?.price)
        assertEquals("Groceries", retrievedItem?.category)
        assertEquals(shoppingListId, retrievedItem?.shoppingListId)
        assertEquals(insertedId, retrievedItem?.id)
    }
    
    @Test
    fun updateShoppingListItem() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert a shopping list item
        val shoppingListItem = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = "Original Item",
            quantity = 1,
            price = 10.00
        )
        val insertedId = shoppingListItemDao.insert(shoppingListItem)
        
        // Update the shopping list item
        val updatedItem = shoppingListItem.copy(
            name = "Updated Item",
            quantity = 3,
            price = 15.00
        )
        shoppingListItemDao.update(updatedItem)
        
        // Read the shopping list item back
        val retrievedItem = shoppingListItemDao.getShoppingListItemById(insertedId)
        
        // Verify the update worked
        assertEquals("Updated Item", retrievedItem?.name)
        assertEquals(3, retrievedItem?.quantity)
        assertEquals(15.00, retrievedItem?.price)
    }
    
    @Test
    fun deleteShoppingListItem() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert a shopping list item
        val shoppingListItem = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = "Test Item"
        )
        val insertedId = shoppingListItemDao.insert(shoppingListItem)
        
        // Delete the shopping list item
        shoppingListItemDao.deleteShoppingListItem(insertedId)
        
        // Try to read the deleted shopping list item
        val retrievedItem = shoppingListItemDao.getShoppingListItemById(insertedId)
        
        // Verify the deletion worked
        assertNull(retrievedItem)
    }
    
    @Test
    fun getShoppingListItemsByShoppingListId() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert multiple shopping list items for the same list
        val item1 = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = "Item 1"
        )
        val item2 = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = "Item 2"
        )
        
        shoppingListItemDao.insert(item1)
        shoppingListItemDao.insert(item2)
        
        // Get all items for this shopping list
        val items = shoppingListItemDao.getShoppingListItems(shoppingListId).first()
        
        // Verify we get the right number of items
        assertEquals(2, items.size)
    }
    
    @Test
    fun getItemsByPurchasedStatus() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert items with different purchased statuses
        val item1 = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = "Item 1",
            purchased = true
        )
        val item2 = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = "Item 2",
            purchased = false
        )
        
        shoppingListItemDao.insert(item1)
        shoppingListItemDao.insert(item2)
        
        // Get purchased items
        val purchasedItems = shoppingListItemDao.getItemsByPurchasedStatus(shoppingListId, true).first()
        // Get to-buy items
        val toBuyItems = shoppingListItemDao.getItemsByPurchasedStatus(shoppingListId, false).first()
        
        // Verify we get the right number of items for each status
        assertEquals(1, purchasedItems.size)
        assertEquals(1, toBuyItems.size)
        assertEquals(true, purchasedItems[0].purchased)
        assertEquals(false, toBuyItems[0].purchased)
    }
}