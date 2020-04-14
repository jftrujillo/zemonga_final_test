package com.example.zemongatest.data

import androidx.lifecycle.LiveData
import com.example.zemongatest.data.database.entitity.UserEntity
import com.example.zemongatest.domain.model.User

interface UserRepository {
    fun getUserById(userId: Int): LiveData<User>
    suspend fun fetchAllUsers(): List<UserEntity>
}