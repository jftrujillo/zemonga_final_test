package com.example.zemongatest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.presentation.viewmodel.FavoritePostViewModel
import com.example.zemongatest.utils.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class FavoritePostViewModelTest {

    private val postRepository = mock<PostRepository>()

    private lateinit var viewModel: FavoritePostViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        viewModel = FavoritePostViewModel(postRepository)
    }

    @Test
    fun getAllFavoritePostLiveData() {
        viewModel.allFavoritePostLiveData
        runBlocking {
            verify(postRepository).getAllFavoritePost()
        }
    }
}