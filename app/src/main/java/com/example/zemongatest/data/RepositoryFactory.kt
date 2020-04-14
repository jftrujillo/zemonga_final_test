package com.example.zemongatest.data

import com.example.zemongatest.data.database.dao.CommentDao
import com.example.zemongatest.data.database.dao.PostDao
import com.example.zemongatest.data.database.dao.UserDao
import com.example.zemongatest.data.impl.CommentRepositoryImpl
import com.example.zemongatest.data.impl.PostRepositoryImpl
import com.example.zemongatest.data.impl.UserRepositoryImpl
import com.example.zemongatest.data.mapper.CommentMapper
import com.example.zemongatest.data.mapper.PostMapper
import com.example.zemongatest.data.mapper.UserMapper
import com.example.zemongatest.data.network.retrofit.JsonPlaceHolderApi

class RepositoryFactory {

    companion object {
        @Volatile
        private var postRepositoryInstance: PostRepository? = null

        @Volatile
        private var userRepositoryInstance: UserRepository? = null

        @Volatile
        private var commentRepositoryInstance: CommentRepository ?= null

        fun getPostRespositoryInstace(postDao: PostDao, mapper: PostMapper, jsonPlaceHolderApi: JsonPlaceHolderApi) =
            postRepositoryInstance ?: synchronized(this) {
                postRepositoryInstance
                    ?: PostRepositoryImpl(
                        postDao,
                        jsonPlaceHolderApi,
                        mapper
                    ).also { postRepositoryInstance = it }
            }

        fun getUserRepositoryInstace(userDao: UserDao, mapper: UserMapper, jsonPlaceHolderApi: JsonPlaceHolderApi) =
            userRepositoryInstance ?: synchronized(this) {
                userRepositoryInstance
                    ?: UserRepositoryImpl(
                        userDao,
                        mapper,
                        jsonPlaceHolderApi
                    ).also { userRepositoryInstance = it }
            }

        fun getCommentRepositoryInstance(commentDao: CommentDao, mapper: CommentMapper) =
            commentRepositoryInstance ?: synchronized(this) {
                commentRepositoryInstance
                    ?: CommentRepositoryImpl(
                        commentDao,
                        mapper
                    ).also { commentRepositoryInstance = it }
            }
    }
}