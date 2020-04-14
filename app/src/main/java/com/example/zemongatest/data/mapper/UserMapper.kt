package com.example.zemongatest.data.mapper

import com.example.zemongatest.data.database.entitity.UserEntity
import com.example.zemongatest.data.network.responses.UserResponse
import java.util.*

class UserMapper : BaseMapper<UserResponse, UserEntity, Objects> {
    override fun transformResponseToEntity(service: UserResponse): UserEntity {
        return UserEntity(
            service.userId,
            service.name,
            service.email,
            service.phone,
            service.website
        )
    }

    override fun transformEntityToPresentation(entity: UserEntity): Objects {
        //pending
    }
}