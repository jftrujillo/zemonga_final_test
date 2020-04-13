package com.example.zemongatest.data.network.retrofit

import com.example.zemongatest.data.network.responses.CommentResponse
import com.example.zemongatest.data.network.responses.PostResponse
import com.example.zemongatest.data.network.responses.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceHolderApi {
    @GET("/posts")
    suspend fun getAllPostRequest(): List<PostResponse>

    @GET("/users")
    suspend fun getAllUsersRequest(): List<UserResponse>

    @GET("/comments")
    suspend fun getAllCommentsRequest(@Query("postId") postId: Int): List<CommentResponse>
}