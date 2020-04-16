package com.example.zemongatest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zemongatest.data.CommentRepository
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.data.UserRepository
import com.example.zemongatest.data.network.responses.FetchCommentState
import com.example.zemongatest.presentation.viewmodel.PostDetailViewModel
import com.example.zemongatest.utils.CoroutinesTestRule
import com.example.zemongatest.utils.FakeData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class PostDetailViewModelTest {

    private val userId = 1
    private val postId = 1
    private val postRepository = mock<PostRepository>()
    private val userRepository = mock<UserRepository>()
    private val commentRepository = mock<CommentRepository>()
    private val serviceResponse = FakeData.getDummyListCommentEntity()


    private lateinit var viewModel: PostDetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        runBlocking {
            Mockito.`when`(commentRepository.fetchCommentByPostId(1))
                .thenReturn(FetchCommentState("", serviceResponse))
            viewModel = PostDetailViewModel(
                postRepository,
                userRepository,
                commentRepository,
                postId,
                userId
            )
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun fetchComments() {
        viewModel.fetchComments()
        runBlocking {
            verify(commentRepository).fetchCommentByPostId(postId)
            verify(commentRepository).insertAll(serviceResponse)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchCommentsError() {
        runBlocking {
            Mockito.`when`(commentRepository.fetchCommentByPostId(1))
                .thenReturn(FetchCommentState("error", listOf()))
            val viewModel = PostDetailViewModel(
                postRepository,
                userRepository,
                commentRepository,
                postId,
                userId
            )
            viewModel.fetchComments()
            verify(commentRepository).fetchCommentByPostId(postId)
        }
    }

    @Test
    fun updateStatus() {
        viewModel.updateStatus()
        runBlocking {
            verify(postRepository).updatePost( true, postId)
        }
    }

    @Test
    fun maskAsAFavorite() {
        viewModel.maskAsAFavorite()
        runBlocking {
            verify(postRepository).favPost( true, postId)
        }
    }

    @Test
    fun removeFromFavorite() {
        viewModel.removeFromFavorite()
        runBlocking {
            verify(postRepository).favPost( false, postId)
        }
    }
}