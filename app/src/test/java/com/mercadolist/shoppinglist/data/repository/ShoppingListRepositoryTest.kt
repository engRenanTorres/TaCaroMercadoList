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
import com.mercadolist.shoppinglist.data.dao.ShoppingListDao
import com.mercadolist.shoppinglist.data.dao.UserDao
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.entities.User

@RunWith(AndroidJUnit4::class)
class ShoppingListRepositoryTest {
    private lateinit var database: ShoppingListDatabase
    private lateinit var shoppingListDao: ShoppingListDao
    private lateinit var userDao: UserDao
    private lateinit var repository: ShoppingListRepository
    
    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ShoppingListDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        
        shoppingListDao = database.shoppingListDao()
        userDao = database.userDao()
        repository = ShoppingListRepository(shoppingListDao, userDao)
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun testShoppingListCRUDOperations() = runBlocking {
        // Create and insert a user
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Test insert
        val shoppingList = ShoppingList(
            name = "Test Shopping List",
            userId = userId
        )
        val insertedId = repository.insert(shoppingList)
        
        // Test read by ID
        val retrievedList = repository.getShoppingListById(insertedId)
        assertEquals("Test Shopping List", retrievedList?.name)
        assertEquals(userId, retrievedList?.userId)
        
        // Test update
        val updatedList = shoppingList.copy(name = "Updated Shopping List")
        repository.update(updatedList)
        
        val updatedRetrievedList = repository.getShoppingListById(insertedId)
        assertEquals("Updated Shopping List", updatedRetrievedList?.name)
        
        // Test get by user ID
        val listsByUser = repository.getShoppingListsByUserId(userId).first()
        assertEquals(1, listsByUser.size)
        assertEquals("Updated Shopping List", listsByUser[0].name)
        
        // Test delete
        repository.delete(insertedId)
        val deletedList = repository.getShoppingListById(insertedId)
        assertEquals(null, deletedList)
    }
}