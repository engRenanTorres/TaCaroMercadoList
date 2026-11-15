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
import com.mercadolist.shoppinglist.data.dao.UserDao
import com.mercadolist.shoppinglist.data.entities.User

@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    private lateinit var database: ShoppingListDatabase
    private lateinit var userDao: UserDao
    private lateinit var repository: UserRepository
    
    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ShoppingListDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        
        userDao = database.userDao()
        repository = UserRepository(userDao)
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun testUserCRUDOperations() = runBlocking {
        // Test insert
        val user = User(
            name = "Test User",
            avatar = "test_avatar.png"
        )
        val insertedId = repository.insert(user)
        
        // Test read by ID
        val retrievedUser = repository.getUserById(insertedId)
        assertEquals("Test User", retrievedUser?.name)
        assertEquals("test_avatar.png", retrievedUser?.avatar)
        
        // Test update
        val updatedUser = user.copy(name = "Updated User")
        repository.update(updatedUser)
        
        val updatedRetrievedUser = repository.getUserById(insertedId)
        assertEquals("Updated User", updatedRetrievedUser?.name)
        
        // Test get all users
        val allUsers = repository.getAllUsers().first()
        assertEquals(1, allUsers.size)
        assertEquals("Updated User", allUsers[0].name)
        
        // Test get by name
        val userByName = repository.getUserByName("Updated User")
        assertEquals("Updated User", userByName?.name)
        
        // Test delete
        repository.delete(insertedId)
        val deletedUser = repository.getUserById(insertedId)
        assertEquals(null, deletedUser)
    }
}