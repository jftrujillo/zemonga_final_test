package com.example.zemongatest.data.database.entitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(
    @PrimaryKey var idPost: Int,
    val userId: Int,
    val title: String,
    val body: String,
    var wasRead: Boolean,
    val isFavorite: Boolean
)