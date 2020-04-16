package com.example.zemongatest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.presentation.viewmodel.AllPostViewModel
import com.example.zemongatest.utils.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class AllPostViewModelTest {
    private val postRepository = mock<PostRepository>()

    private lateinit var allPostViewModel: AllPostViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        allPostViewModel = AllPostViewModel(postRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteAll() {
        allPostViewModel.deleteAll()
        runBlocking {
            verify(postRepository).deleteAll()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deletePost() {
        allPostViewModel.deletePost(1)
        runBlocking {
            verify(postRepository).deleteByIdPost(1)
        }
    }
}
