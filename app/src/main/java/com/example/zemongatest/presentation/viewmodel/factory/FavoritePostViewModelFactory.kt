package com.example.zemongatest.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.presentation.viewmodel.FavoritePostViewModel


@Suppress("UNCHECKED_CAST")
class FavoritePostViewModelFactory(
    private val postRepository: PostRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        FavoritePostViewModel(postRepository) as T
}