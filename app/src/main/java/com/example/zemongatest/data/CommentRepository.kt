package com.example.zemongatest.data

import androidx.lifecycle.LiveData
import com.example.zemongatest.data.database.entitity.CommentEntity
import com.example.zemongatest.data.network.responses.FetchCommentState
import com.example.zemongatest.domain.model.Comment

interface CommentRepository {
    fun getCommentsByPostId(postId: Int): LiveData<List<Comment>>
    suspend fun insertAll(comments: List<CommentEntity>)
    suspend fun fetchCommentByPostId(postId: Int): FetchCommentState
}