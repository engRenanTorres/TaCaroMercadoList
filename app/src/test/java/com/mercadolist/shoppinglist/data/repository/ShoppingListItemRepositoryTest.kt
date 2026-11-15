package com.mercadolist.shoppinglist.data.repository

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.mercadolist.shoppinglist.data.database.ShoppingListDatabase
import com.mercadolist.shoppinglist.data.dao.ShoppingListItemDao
import com.mercadolist.shoppinglist.data.dao.ShoppingListDao
import com.mercadolist.shoppinglist.data.dao.UserDao
import com.mercadolist.shoppinglist.data.entities.ShoppingListItem
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.entities.User

@RunWith(AndroidJUnit4::class)
class ShoppingListItemRepositoryTest {
    private lateinit var database: ShoppingListDatabase
    private lateinit var shoppingListItemDao: ShoppingListItemDao
    private lateinit var shoppingListDao: ShoppingListDao
    private lateinit var userDao: UserDao
    private lateinit var repository: ShoppingListItemRepository
    
    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ShoppingListDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        
        shoppingListItemDao = database.shoppingListItemDao()
        shoppingListDao = database.shoppingListDao()
        userDao = database.userDao()
        repository = ShoppingListItemRepository(shoppingListItemDao, shoppingListDao, userDao)
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun testShoppingListItemCRUDOperations() = runBlocking {
        // Create and insert a user
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Test insert
        val item = ShoppingListItem(
            shoppingListId = shoppingListId,
            name = "Test Item",
            quantity = 2,
            price = 5.99
        )
        val insertedId = repository.insert(item)
        
        // Test read by ID
        val retrievedItem = repository.getShoppingListItemById(insertedId)
        assertEquals("Test Item", retrievedItem?.name)
        assertEquals(2, retrievedItem?.quantity)
        assertEquals(5.99, retrievedItem?.price)
        assertEquals(shoppingListId, retrievedItem?.shoppingListId)
        
        // Test update
        val updatedItem = item.copy(name = "Updated Item", quantity = 3)
        repository.update(updatedItem)
        
        val updatedRetrievedItem = repository.getShoppingListItemById(insertedId)
        assertEquals("Updated Item", updatedRetrievedItem?.name)
        assertEquals(3, updatedRetrievedItem?.quantity)
        
        // Test get by shopping list ID
        val itemsByList = repository.getShoppingListItems(shoppingListId).first()
        assertEquals(1, itemsByList.size)
        assertEquals("Updated Item", itemsByList[0].name)
        
        // Test delete
        repository.delete(insertedId)
        val deletedItem = repository.getShoppingListItemById(insertedId)
        assertEquals(null, deletedItem)
    }
}