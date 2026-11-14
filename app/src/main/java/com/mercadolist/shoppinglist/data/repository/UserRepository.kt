package com.mercadolist.shoppinglist.data.repository

import com.mercadolist.shoppinglist.data.dao.UserDao
import com.mercadolist.shoppinglist.data.entities.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository for User operations.
 * Abstracts data access logic from the domain layer.
 */
class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User): Long = userDao.insertUser(user)

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun getUserById(userId: Long): User? = userDao.getUserById(userId)

    suspend fun getUserByName(name: String): User? = userDao.getUserByName(name)

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun deleteUser(userId: Long) = userDao.deleteUser(userId)

    suspend fun getUserCount(): Int = userDao.getUserCount()
}
