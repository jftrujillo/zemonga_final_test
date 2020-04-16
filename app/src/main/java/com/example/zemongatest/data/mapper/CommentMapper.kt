package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.CommentEntity
import com.example.zemongatest.data.network.responses.CommentResponse
import com.example.zemongatest.domain.model.Comment
import java.util.*

class CommentMapper : BaseMapper<CommentResponse, CommentEntity, Comment> {
    //Pendig presentation model

    override fun transformResponseToEntity(service: CommentResponse) =
        CommentEntity(service.commentId, service.postId, service.body)

    override fun transformEntityToPresentation(entity: CommentEntity) =
        Comment(entity.commentId, entity.postId, entity.body)
}