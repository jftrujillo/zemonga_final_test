package com.example.zemongatest.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.data.database.dao.PostDao
import com.example.zemongatest.data.database.entitity.PostEntity
import com.example.zemongatest.data.mapper.PostMapper
import com.example.zemongatest.data.network.retrofit.JsonPlaceHolderApi
import com.example.zemongatest.domain.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepositoryImpl(
    private val postDao: PostDao,
    private val jsonPlaceHolderApi: JsonPlaceHolderApi,
    private val postMapper: PostMapper
) : PostRepository {
    override fun getAllPost(): LiveData<List<Post>> {
        return Transformations.map(postDao.getAllPost()) {entityList ->
            entityList.map {
                postMapper.transformEntityToPresentation(it)
            }
        }
    }

    override fun getAllFavoritePost(): LiveData<List<Post>> {
        return Transformations.map(postDao.getAllFavoritePost()) { entityList ->
            entityList.map {
                postMapper.transformEntityToPresentation(it)
            }
        }
    }

    override fun getPostById(idPost: Int): LiveData<Post> {
        return Transformations.map(postDao.getPostById(idPost)) {
            postMapper.transformEntityToPresentation(it)
        }
    }

    override suspend fun deleteAll() {
        postDao.deleteAll()
    }

    override suspend fun deletePostById(postId: Int) {
        postDao.deleteByIdPost(postId)
    }

    override suspend fun updatePost(wasRead: Boolean, idPost: Int) {
        postDao.updateWasRead(idPost, wasRead)
    }

    override suspend fun favPost(isFavorite: Boolean, idPost: Int) {
        postDao.updateIsFavorite(idPost, isFavorite)
    }

    override suspend fun fetchAllPost(): List<PostEntity> {
        return jsonPlaceHolderApi.getAllPostRequest().map {
            postMapper.transformResponseToEntity(it)
        }
    }

    override suspend fun deleteByIdPost(idPost: Int) = withContext(Dispatchers.IO) {
        postDao.deleteByIdPost(idPost)
    }
}