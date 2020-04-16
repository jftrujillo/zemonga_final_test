package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.UserEntity
import com.example.zemongatest.data.network.responses.UserResponse
import com.example.zemongatest.domain.model.User
import com.example.zemongatest.utils.FakeData.Factory.EMAIL
import com.example.zemongatest.utils.FakeData.Factory.NAME
import com.example.zemongatest.utils.FakeData.Factory.PHONE
import com.example.zemongatest.utils.FakeData.Factory.WEB_SITE
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserMapperTest {

    private lateinit var userMapper: UserMapper

    @Before
    fun setUp() {
        userMapper = UserMapper()
    }

    @Test
    fun transformServiceToEntity() {
        val mockServiceResponse = UserResponse(1, NAME, EMAIL, PHONE, WEB_SITE)
        val resultEntity = userMapper.transformResponseToEntity(mockServiceResponse)
        assertDataEquals(mockServiceResponse, resultEntity)

    }

    @Test
    fun transformEntityToPresentation() {
        val mockEntity = UserEntity(1, NAME, EMAIL, PHONE, WEB_SITE)
        val result = userMapper.transformEntityToPresentation(mockEntity)
        assertDataEquals(mockEntity, result)
    }

    private fun assertDataEquals(userResponse: UserResponse, userEntity: UserEntity) {
        Assert.assertEquals(userResponse.userId, userEntity.userId)
        Assert.assertEquals(userEntity.name, userEntity.name)
        Assert.assertEquals(userEntity.email, userEntity.email)
        Assert.assertEquals(userEntity.phone, userEntity.phone)
        Assert.assertEquals(userEntity.website, userEntity.website)
    }

    private fun assertDataEquals(userEntity: UserEntity, user: User) {
        Assert.assertEquals(userEntity.userId, user.userId)
        Assert.assertEquals(userEntity.name, user.name)
        Assert.assertEquals(userEntity.email, user.email)
        Assert.assertEquals(userEntity.phone, user.phone)
        Assert.assertEquals(userEntity.website, user.website)

    }
}