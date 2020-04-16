package com.example.zemongatest.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zemongatest.data.CommentRepository
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.data.UserRepository
import com.example.zemongatest.presentation.viewmodel.PostDetailViewModel

@Suppress("UNCHECKED_CAST")
class PostDetailViewModelFactory(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
    private val postId: Int,
    private val userId: Int
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        PostDetailViewModel(
            postRepository,
            userRepository,
            commentRepository,
            postId,
            userId
        ) as T
}