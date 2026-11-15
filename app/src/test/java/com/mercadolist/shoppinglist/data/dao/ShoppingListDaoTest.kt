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
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.entities.User

@RunWith(AndroidJUnit4::class)
class ShoppingListDaoTest {
    private lateinit var database: ShoppingListDatabase
    private lateinit var shoppingListDao: ShoppingListDao
    private lateinit var userDao: UserDao
    
    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ShoppingListDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        
        shoppingListDao = database.shoppingListDao()
        userDao = database.userDao()
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun insertAndReadShoppingList() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val insertedId = shoppingListDao.insert(shoppingList)
        
        // Read the shopping list back
        val retrievedShoppingList = shoppingListDao.getShoppingListById(insertedId)
        
        // Verify the data matches
        assertEquals("Test List", retrievedShoppingList?.name)
        assertEquals(userId, retrievedShoppingList?.userId)
        assertEquals(insertedId, retrievedShoppingList?.id)
    }
    
    @Test
    fun updateShoppingList() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Original List",
            userId = userId
        )
        val insertedId = shoppingListDao.insert(shoppingList)
        
        // Update the shopping list
        val updatedShoppingList = shoppingList.copy(
            name = "Updated List"
        )
        shoppingListDao.update(updatedShoppingList)
        
        // Read the shopping list back
        val retrievedShoppingList = shoppingListDao.getShoppingListById(insertedId)
        
        // Verify the update worked
        assertEquals("Updated List", retrievedShoppingList?.name)
    }
    
    @Test
    fun deleteShoppingList() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val insertedId = shoppingListDao.insert(shoppingList)
        
        // Delete the shopping list
        shoppingListDao.deleteShoppingList(insertedId)
        
        // Try to read the deleted shopping list
        val retrievedShoppingList = shoppingListDao.getShoppingListById(insertedId)
        
        // Verify the deletion worked
        assertNull(retrievedShoppingList)
    }
    
    @Test
    fun getShoppingListsByUserId() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert multiple shopping lists for the same user
        val shoppingList1 = ShoppingList(
            name = "List 1",
            userId = userId
        )
        val shoppingList2 = ShoppingList(
            name = "List 2",
            userId = userId
        )
        
        shoppingListDao.insert(shoppingList1)
        shoppingListDao.insert(shoppingList2)
        
        // Get all shopping lists for this user
        val shoppingLists = shoppingListDao.getShoppingListsByUserId(userId).first()
        
        // Verify we get the right number of lists
        assertEquals(2, shoppingLists.size)
    }
}