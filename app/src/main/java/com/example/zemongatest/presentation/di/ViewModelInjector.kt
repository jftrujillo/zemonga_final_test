package com.example.zemongatest.presentation.di

import android.content.Context
import com.example.zemongatest.data.RepositoryFactory
import com.example.zemongatest.data.database.room.PostDatabase
import com.example.zemongatest.data.mapper.CommentMapper
import com.example.zemongatest.data.mapper.PostMapper
import com.example.zemongatest.data.mapper.UserMapper
import com.example.zemongatest.data.network.retrofit.RetrofitClient
import com.example.zemongatest.presentation.viewmodel.factory.AllPostViewModelFactory
import com.example.zemongatest.presentation.viewmodel.factory.FavoritePostViewModelFactory
import com.example.zemongatest.presentation.viewmodel.factory.PostDetailViewModelFactory

object ViewModelInjector {
    private fun providePostRepository(context: Context) =
        RepositoryFactory.getPostRespositoryInstace(
            PostDatabase.getInstance(context.applicationContext).postDao(),
            PostMapper(),
            RetrofitClient.getServiceClient()
        )

    private fun provideUserRepository(context: Context) = RepositoryFactory.getUserRepositoryInstace(
        PostDatabase.getInstance(context.applicationContext).userDao(),
        UserMapper(),
        RetrofitClient.getServiceClient()
    )

    private fun provideCommentRepository(context: Context) = RepositoryFactory.getCommentRepositoryInstance(
        PostDatabase.getInstance(context.applicationContext).commentDao(),
        CommentMapper(),
        RetrofitClient.getServiceClient()
    )

    fun provideAllPostViewModel(context: Context) =
        AllPostViewModelFactory(
            providePostRepository(context)
        )


    fun provideFavoritePostViewModel(context: Context) =
        FavoritePostViewModelFactory(
            providePostRepository(context)
        )

    fun providePostDetailsViewModel(context: Context, postId: Int, userId: Int) =
        PostDetailViewModelFactory(
            providePostRepository(context),
            provideUserRepository(context),
            provideCommentRepository(context),
            postId,
            userId
        )
}