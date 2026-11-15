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
import com.mercadolist.shoppinglist.data.entities.User

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var database: ShoppingListDatabase
    private lateinit var userDao: UserDao
    
    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ShoppingListDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        
        userDao = database.userDao()
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun insertAndReadUser() = runBlocking {
        // Create and insert a user
        val user = User(
            name = "Test User",
            avatar = "test_avatar.png"
        )
        val insertedId = userDao.insert(user)
        
        // Read the user back
        val retrievedUser = userDao.getUserById(insertedId)
        
        // Verify the data matches
        assertEquals("Test User", retrievedUser?.name)
        assertEquals("test_avatar.png", retrievedUser?.avatar)
        assertEquals(insertedId, retrievedUser?.id)
    }
    
    @Test
    fun updateUser() = runBlocking {
        // Create and insert a user
        val user = User(
            name = "Original Name",
            avatar = "old_avatar.png"
        )
        val insertedId = userDao.insert(user)
        
        // Update the user
        val updatedUser = user.copy(
            name = "Updated Name",
            avatar = "new_avatar.png"
        )
        userDao.update(updatedUser)
        
        // Read the user back
        val retrievedUser = userDao.getUserById(insertedId)
        
        // Verify the update worked
        assertEquals("Updated Name", retrievedUser?.name)
        assertEquals("new_avatar.png", retrievedUser?.avatar)
    }
    
    @Test
    fun deleteUser() = runBlocking {
        // Create and insert a user
        val user = User(
            name = "Test User"
        )
        val insertedId = userDao.insert(user)
        
        // Delete the user
        userDao.deleteUser(insertedId)
        
        // Try to read the deleted user
        val retrievedUser = userDao.getUserById(insertedId)
        
        // Verify the deletion worked
        assertNull(retrievedUser)
    }
    
    @Test
    fun getUserByName() = runBlocking {
        // Create and insert a user
        val user = User(
            name = "John Doe"
        )
        userDao.insert(user)
        
        // Get user by name
        val retrievedUser = userDao.getUserByName("John Doe")
        
        // Verify we get the right user
        assertEquals("John Doe", retrievedUser?.name)
    }
    
    @Test
    fun getAllUsers() = runBlocking {
        // Create and insert multiple users
        val user1 = User(name = "User 1")
        val user2 = User(name = "User 2")
        
        userDao.insert(user1)
        userDao.insert(user2)
        
        // Get all users
        val users = userDao.getAllUsers().first()
        
        // Verify we get the right number of users
        assertEquals(2, users.size)
    }
}