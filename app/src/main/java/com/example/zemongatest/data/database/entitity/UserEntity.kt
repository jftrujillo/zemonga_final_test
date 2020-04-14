package com.example.zemongatest.data.database.entitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey var userId: Int,
    val name: String,
    val email: String,
    val phone: String,
    val website: String
)