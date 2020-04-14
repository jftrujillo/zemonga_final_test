package com.example.zemongatest.data

import androidx.lifecycle.LiveData
import com.example.zemongatest.data.database.entitity.PostEntity
import com.example.zemongatest.domain.model.Post

interface PostRepository {
    fun getAllPost(): LiveData<List<Post>>
    fun getAllFavoritePost(): LiveData<List<Post>>
    fun getPostById(idPost: Int): LiveData<Post>
    suspend fun deleteAll()
    suspend fun deletePostById(postId: Int)
    suspend fun updatePost(wasRead: Boolean, idPost: Int)
    suspend fun favPost(isFavorite: Boolean, idPost: Int)
    suspend fun fetchAllPost(): List<PostEntity>
}