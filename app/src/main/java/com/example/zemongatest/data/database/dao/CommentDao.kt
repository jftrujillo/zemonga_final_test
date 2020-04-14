package com.example.zemongatest.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zemongatest.data.database.entitity.CommentEntity

@Dao
interface CommentDao {
    @Query("SELECT * from comment WHERE postId = :idPost")
    fun getCommentsByPostId(idPost: Int): LiveData<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(comments: List<CommentEntity>)
}