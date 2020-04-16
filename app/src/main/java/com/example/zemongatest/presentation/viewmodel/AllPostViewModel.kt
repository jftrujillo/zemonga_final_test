package com.example.zemongatest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zemongatest.data.PostRepository
import kotlinx.coroutines.launch

class AllPostViewModel internal constructor(
    private val postStorageImpl: PostRepository
) : ViewModel() {

    val allPostLiveData = postStorageImpl.getAllPost()

    fun deleteAll() {
        viewModelScope.launch {
            postStorageImpl.deleteAll()
        }
    }

    fun deletePost(postId: Int) {
        viewModelScope.launch {
            postStorageImpl.deleteByIdPost(postId)
        }
    }
}
