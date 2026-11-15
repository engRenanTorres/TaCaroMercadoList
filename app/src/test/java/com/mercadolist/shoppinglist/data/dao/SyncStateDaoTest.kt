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
import com.mercadolist.shoppinglist.data.entities.SyncState
import com.mercadolist.shoppinglist.data.entities.ShoppingList
import com.mercadolist.shoppinglist.data.entities.User

@RunWith(AndroidJUnit4::class)
class SyncStateDaoTest {
    private lateinit var database: ShoppingListDatabase
    private lateinit var syncStateDao: SyncStateDao
    private lateinit var shoppingListDao: ShoppingListDao
    private lateinit var userDao: UserDao
    
    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ShoppingListDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        
        syncStateDao = database.syncStateDao()
        shoppingListDao = database.shoppingListDao()
        userDao = database.userDao()
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun insertAndReadSyncState() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert a sync state
        val syncState = SyncState(
            shoppingListId = shoppingListId,
            lastSyncedAt = System.currentTimeMillis(),
            syncStatus = "synced",
            lastModifiedBy = userId
        )
        val insertedId = syncStateDao.insert(syncState)
        
        // Read the sync state back
        val retrievedSyncState = syncStateDao.getSyncStateByShoppingListId(shoppingListId)
        
        // Verify the data matches
        assertEquals(shoppingListId, retrievedSyncState?.shoppingListId)
        assertEquals("synced", retrievedSyncState?.syncStatus)
        assertEquals(userId, retrievedSyncState?.lastModifiedBy)
        assertEquals(insertedId, retrievedSyncState?.id)
    }
    
    @Test
    fun updateSyncState() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert a sync state
        val syncState = SyncState(
            shoppingListId = shoppingListId,
            lastSyncedAt = System.currentTimeMillis(),
            syncStatus = "synced",
            lastModifiedBy = userId
        )
        val insertedId = syncStateDao.insert(syncState)
        
        // Update the sync state
        val updatedSyncState = syncState.copy(
            syncStatus = "pending"
        )
        syncStateDao.update(updatedSyncState)
        
        // Read the sync state back
        val retrievedSyncState = syncStateDao.getSyncStateByShoppingListId(shoppingListId)
        
        // Verify the update worked
        assertEquals("pending", retrievedSyncState?.syncStatus)
    }
    
    @Test
    fun deleteSyncState() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert a sync state
        val syncState = SyncState(
            shoppingListId = shoppingListId,
            lastSyncedAt = System.currentTimeMillis(),
            syncStatus = "synced",
            lastModifiedBy = userId
        )
        syncStateDao.insert(syncState)
        
        // Delete the sync state
        syncStateDao.deleteSyncStateByShoppingListId(shoppingListId)
        
        // Try to read the deleted sync state
        val retrievedSyncState = syncStateDao.getSyncStateByShoppingListId(shoppingListId)
        
        // Verify the deletion worked
        assertNull(retrievedSyncState)
    }
    
    @Test
    fun getSyncStatesByStatus() = runBlocking {
        // Insert a user first (required for foreign key)
        val user = User(name = "Test User")
        val userId = userDao.insert(user)
        
        // Create and insert a shopping list
        val shoppingList = ShoppingList(
            name = "Test List",
            userId = userId
        )
        val shoppingListId = shoppingListDao.insert(shoppingList)
        
        // Create and insert sync states with different statuses
        val syncState1 = SyncState(
            shoppingListId = shoppingListId,
            lastSyncedAt = System.currentTimeMillis(),
            syncStatus = "synced",
            lastModifiedBy = userId
        )
        val syncState2 = SyncState(
            shoppingListId = shoppingListId,
            lastSyncedAt = System.currentTimeMillis(),
            syncStatus = "pending",
            lastModifiedBy = userId
        )
        
        syncStateDao.insert(syncState1)
        syncStateDao.insert(syncState2)
        
        // Get sync states by status
        val syncedStates = syncStateDao.getSyncStatesByStatus("synced").first()
        val pendingStates = syncStateDao.getSyncStatesByStatus("pending").first()
        
        // Verify we get the right number of sync states for each status
        assertEquals(1, syncedStates.size)
        assertEquals(1, pendingStates.size)
        assertEquals("synced", syncedStates[0].syncStatus)
        assertEquals("pending", pendingStates[0].syncStatus)
    }
}