package com.mercadolist.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.mercadolist.shoppinglist.data.entities.User

/**
 * Data Access Object for User entity.
 * Provides CRUD operations and reactive data streams.
 */
@Dao
interface UserDao {
    /**
     * Insert a new user.
     * @param user The user to insert
     * @return The inserted user's ID
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    /**
     * Update an existing user.
     * @param user The user to update
     */
    @Update
    suspend fun update(user: User)

    /**
     * Get a user by ID.
     * @param userId The user ID
     * @return The user or null if not found
     */
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Long): User?

    /**
     * Get all users as a reactive flow.
     * @return Flow of all users
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    /**
     * Get user by name.
     * @param name The user's name
     * @return The user or null if not found
     */
    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun getUserByName(name: String): User?

    /**
     * Delete a user.
     * @param userId The user ID to delete
     */
    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUser(userId: Long)

    /**
     * Count total users.
     * @return The number of users
     */
    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int
}
