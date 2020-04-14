package com.example.zemongatest.data.database.entitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class CommentEntity(
    @PrimaryKey var commentId: Int,
    val postId: Int,
    val body: String
)