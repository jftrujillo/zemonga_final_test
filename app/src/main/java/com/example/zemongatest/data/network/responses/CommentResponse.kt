package com.example.zemongatest.data.network.responses

import com.google.gson.annotations.SerializedName

class CommentResponse (
    @SerializedName("id")
    val commentId: Int,
    val postId: Int,
    val body: String
)