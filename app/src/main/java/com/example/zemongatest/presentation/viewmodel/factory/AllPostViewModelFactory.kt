package com.example.zemongatest.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.presentation.viewmodel.AllPostViewModel

@Suppress("UNCHECKED_CAST")
class AllPostViewModelFactory(private val postRepository: PostRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        AllPostViewModel(postRepository) as T
}