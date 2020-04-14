package com.example.zemongatest.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zemongatest.data.database.entitity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * from user WHERE userId = :userId")
    fun getUserById(userId: Int): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<UserEntity>)
}