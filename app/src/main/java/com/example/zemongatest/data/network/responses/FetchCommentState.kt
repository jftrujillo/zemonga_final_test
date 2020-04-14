package com.example.zemongatest.data.network.responses

import com.example.zemongatest.data.database.entitity.CommentEntity

class FetchCommentState (
    val error: String,
    val listComments: List<CommentEntity>
)