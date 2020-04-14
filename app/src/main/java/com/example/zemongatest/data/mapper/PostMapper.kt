package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.PostEntity
import com.example.zemongatest.data.network.responses.PostResponse
import java.util.*

class PostMapper : BaseMapper<PostResponse, PostEntity, Objects> {
    override fun transformResponseToEntity(service: PostResponse): PostEntity {
        return PostEntity(
            service.postId, service.userId, service.title, service.body,
            wasRead = true,
            isFavorite = false
        )
    }

    override fun transformEntityToPresentation(entity: PostEntity): Objects {
        //pending
    }
}