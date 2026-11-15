package com.mercadolist.shoppinglist.data.repository

import kotlinx.coroutines.flow.Flow
import com.mercadolist.shoppinglist.data.dao.UserDao
import com.mercadolist.shoppinglist.data.entities.User

/**
 * Repository for User entity operations.
 * Provides a clean interface between data layer and presentation layer.
 */
class UserRepository(private val userDao: UserDao) {
    
    /**
     * Insert a new user.
     * @param user The user to insert
     * @return The inserted user's ID
     */
    suspend fun insertUser(user: User): Long = userDao.insert(user)
    
    /**
     * Update an existing user.
     * @param user The user to update
     */
    suspend fun updateUser(user: User) = userDao.update(user)
    
    /**
     * Get a user by ID.
     * @param userId The user ID
     * @return The user or null if not found
     */
    suspend fun getUserById(userId: Long): User? = userDao.getUserById(userId)
    
    /**
     * Get all users as a reactive flow.
     * @return Flow of all users
     */
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
    
    /**
     * Get user by name.
     * @param name The user's name
     * @return The user or null if not found
     */
    suspend fun getUserByName(name: String): User? = userDao.getUserByName(name)
    
    /**
     * Delete a user.
     * @param userId The user ID to delete
     */
    suspend fun deleteUser(userId: Long) = userDao.deleteUser(userId)
    
    /**
     * Count total users.
     * @return The number of users
     */
    suspend fun getUserCount(): Int = userDao.getUserCount()
}