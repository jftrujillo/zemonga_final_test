package com.example.zemongatest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.zemongatest.data.PostRepository

class FavoritePostViewModel internal constructor(
    postRepository: PostRepository
) : ViewModel() {
    val allFavoritePostLiveData = postRepository.getAllFavoritePost()
}