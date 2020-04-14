package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.UserEntity
import com.example.zemongatest.data.network.responses.UserResponse
import com.example.zemongatest.domain.model.User
import java.util.*

class UserMapper : BaseMapper<UserResponse, UserEntity, User> {
    override fun transformResponseToEntity(service: UserResponse): UserEntity {
        return UserEntity(
            service.userId,
            service.name,
            service.email,
            service.phone,
            service.website
        )
    }

    override fun transformEntityToPresentation(entity: UserEntity): User {
        return User(entity.userId, entity.name, entity.email, entity.phone, entity.website)
    }
}