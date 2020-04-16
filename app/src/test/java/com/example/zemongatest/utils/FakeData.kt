package com.example.zemongatest.utils

import com.example.zemongatest.data.database.entitity.CommentEntity
import com.example.zemongatest.data.database.entitity.PostEntity
import com.example.zemongatest.data.network.responses.CommentResponse
import com.example.zemongatest.data.network.responses.PostResponse
import com.example.zemongatest.data.network.responses.UserResponse
import kotlin.random.Random


class FakeData {

    companion object Factory {
        const val TITLE = "fake title"
        const val BODY = "fake body"


        const val NAME = "name"
        const val EMAIL = "email@gmail.com "
        const val PHONE = "1234"
        const val WEB_SITE = "www.web.com"


        fun getDummyListPostEntity(): List<PostEntity> = (1..10).map {
            PostEntity(
                it,
                it,
                "$TITLE$it",
                "$BODY$it",
                true,
                Random.nextBoolean()
            )
        }

        fun getDummyListCommentEntity(): List<CommentEntity> = (1..10).map {
            CommentEntity(
                it,
                it,
                "$BODY$it"
            )
        }

        fun getDummyListPostResponse(): List<PostResponse> = (1..10).map {
            PostResponse(
                it,
                it,
                "$TITLE$it",
                "$BODY$it"
            )
        }

        fun getDummyListUserResponse(): List<UserResponse> = (1..10).map {
            UserResponse(
                it,
                "$NAME$it",
                "$EMAIL$it",
                "$PHONE$it",
                "$WEB_SITE$it"
            )
        }

        fun getDummyListCommentResponse(): List<CommentResponse> = (1..10).map {
            CommentResponse(
                it,
                it,
                "$BODY$it"
            )
        }
    }
}