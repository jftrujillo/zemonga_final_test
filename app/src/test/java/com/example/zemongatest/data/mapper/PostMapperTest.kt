package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.PostEntity
import com.example.zemongatest.data.network.responses.PostResponse
import com.example.zemongatest.domain.model.Post
import com.example.zemongatest.utils.FakeData.Factory.BODY
import com.example.zemongatest.utils.FakeData.Factory.TITLE
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class PostMapperTest {

    private lateinit var mapper: PostMapper

    @Before
    fun setUp() {
        mapper = PostMapper()
    }

    @Test
    fun transformServiceToEntity() {
        val mockPostServiceResponse =
            PostResponse(1, 2, TITLE, BODY)
        val result = mapper.transformResponseToEntity(mockPostServiceResponse)
        assertDataEquality(mockPostServiceResponse, result)
    }

    @Test
    fun transformEntityToPresentation() {
        val mockPostEntity =
            PostEntity(
                1, 2, TITLE, BODY, Random.nextBoolean(),
                Random.nextBoolean()
            )
        val result = mapper.transformEntityToPresentation(mockPostEntity)
        assertDataEquality(mockPostEntity, result)
    }


    private fun assertDataEquality(postResponse: PostResponse, postEntity: PostEntity) {
        Assert.assertEquals(postResponse.userId, postEntity.userId)
        Assert.assertEquals(postResponse.postId, postEntity.idPost)
        Assert.assertEquals(postResponse.title, postEntity.title)
        Assert.assertEquals(postResponse.body, postEntity.body)
    }

    private fun assertDataEquality(postEntity: PostEntity, post: Post) {
        Assert.assertEquals(postEntity.userId, post.userId)
        Assert.assertEquals(postEntity.idPost, post.postId)
        Assert.assertEquals(postEntity.title, post.title)
        Assert.assertEquals(postEntity.body, post.body)
    }
}