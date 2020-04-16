package com.example.zemongatest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zemongatest.data.CommentRepository
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.data.UserRepository
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
    private val postId: Int,
    userId: Int
) : ViewModel() {
    val postInfo = postRepository.getPostById(postId)
    val userInfo = userRepository.getUserById(userId)
    val comments = commentRepository.getCommentsByPostId(postId)

    val commentsStateRequest: MutableLiveData<String> = MutableLiveData()

    fun fetchComments() {
        viewModelScope.launch {
            val resultService = commentRepository.fetchCommentByPostId(postId)
            if (resultService.error.isEmpty()) {
                commentRepository.insertAll(resultService.listComments)
            } else {
                commentsStateRequest.postValue(resultService.error)
            }
        }
    }

    fun updateStatus() {
        viewModelScope.launch {
            postRepository.updatePost(true, postId)
        }
    }

    fun maskAsAFavorite() {
        viewModelScope.launch {
            postRepository.favPost(true, postId)
        }
    }

    fun removeFromFavorite() {
        viewModelScope.launch {
            postRepository.favPost(false, postId)
        }
    }
}