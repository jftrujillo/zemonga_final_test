package com.example.zemongatest.domain.model

data class Comment (
    val commentId: Int,
    val postId: Int,
    val body: String
)