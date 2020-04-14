package com.example.zemongatest.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.zemongatest.data.CommentRepository
import com.example.zemongatest.data.database.dao.CommentDao
import com.example.zemongatest.data.database.entitity.CommentEntity
import com.example.zemongatest.data.mapper.CommentMapper
import com.example.zemongatest.data.network.responses.FetchCommentState
import com.example.zemongatest.data.network.retrofit.JsonPlaceHolderApi
import com.example.zemongatest.domain.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val NO_ERROR = ""

class CommentRepositoryImpl (
    private val commentDao: CommentDao,
    private val mapper: CommentMapper,
    private val jsonPlaceHolderApi: JsonPlaceHolderApi
) : CommentRepository {
    override fun getCommentsByPostId(postId: Int): LiveData<List<Comment>> {
        return Transformations.map(commentDao.getCommentsByPostId(postId)) { commentsEntity ->
            commentsEntity.map {
                mapper.transformEntityToPresentation(it)
            }
        }
    }

    override suspend fun insertAll(comments: List<CommentEntity>) {
        commentDao.insertAll(comments)
    }

    override suspend fun fetchCommentByPostId(postId: Int): FetchCommentState = withContext(
        Dispatchers.IO
    ) {
        return@withContext try {
            val response = jsonPlaceHolderApi.getAllCommentsRequest(postId).map {
                mapper.transformResponseToEntity(it)
            }
            FetchCommentState(NO_ERROR, response)
        } catch (ex: Exception) {
            FetchCommentState(ex.message.toString(), listOf())
        }
    }
}