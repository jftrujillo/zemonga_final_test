package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.CommentEntity
import com.example.zemongatest.data.network.responses.CommentResponse
import com.example.zemongatest.domain.model.Comment
import com.example.zemongatest.utils.FakeData.Factory.BODY
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CommentMapperTest {
    private lateinit var mapper: CommentMapper
    @Before
    fun setUp() {
        mapper = CommentMapper()
    }

    @Test
    fun transformServiceToEntity() {
        val serviceResponse = CommentResponse(9, 9, BODY)
        val result = mapper.transformResponseToEntity(serviceResponse)
        assertDataEquality(serviceResponse, result)
    }

    @Test
    fun transformEntityToPresentation() {
        val mockEntity = CommentEntity(9, 9, BODY)
        val result = mapper.transformEntityToPresentation(mockEntity)
        assertDataEquality(mockEntity, result)
    }

    private fun assertDataEquality(
        commentResponse: CommentResponse,
        commentEntity: CommentEntity
    ) {
        Assert.assertEquals(commentResponse.commentId, commentEntity.commentId)
        Assert.assertEquals(commentResponse.postId, commentEntity.postId)
        Assert.assertEquals(commentResponse.body, commentEntity.body)
    }

    private fun assertDataEquality(commentEntity: CommentEntity, comment: Comment) {
        Assert.assertEquals(commentEntity.commentId, comment.commentId)
        Assert.assertEquals(commentEntity.postId, comment.postId)
        Assert.assertEquals(commentEntity.body, comment.body)

    }
}
