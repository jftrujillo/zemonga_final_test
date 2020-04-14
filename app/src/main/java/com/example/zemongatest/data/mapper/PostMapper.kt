package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.PostEntity
import com.example.zemongatest.data.network.responses.PostResponse
import com.example.zemongatest.domain.model.Post
import java.util.*

class PostMapper : BaseMapper<PostResponse, PostEntity, Post> {
    override fun transformResponseToEntity(service: PostResponse): PostEntity {
        return PostEntity(
            service.postId, service.userId, service.title, service.body,
            wasRead = true,
            isFavorite = false
        )
    }

    override fun transformEntityToPresentation(entity: PostEntity): Post {
        return Post(
            entity.idPost,
            entity.userId,
            entity.title,
            entity.body.capitalize(),
            entity.wasRead,
            entity.isFavorite
        )
    }
}