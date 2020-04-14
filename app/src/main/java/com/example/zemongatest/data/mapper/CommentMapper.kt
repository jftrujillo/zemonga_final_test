package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.CommentEntity
import com.example.zemongatest.data.network.responses.CommentResponse
import java.util.*

class CommentMapper : BaseMapper<CommentResponse, CommentEntity, Objects> {
    //Pendig presentation model

    override fun transformResponseToEntity(service: CommentResponse) =
        CommentEntity(service.commentId, service.postId, service.body)

    override fun transformEntityToPresentation(entity: CommentEntity) = {
        //Pending
    }

}