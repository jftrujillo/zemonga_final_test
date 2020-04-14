package com.example.zemongatest.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.zemongatest.data.UserRepository
import com.example.zemongatest.data.database.dao.UserDao
import com.example.zemongatest.data.database.entitity.UserEntity
import com.example.zemongatest.data.mapper.UserMapper
import com.example.zemongatest.data.network.retrofit.JsonPlaceHolderApi
import com.example.zemongatest.domain.model.User

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userMapper: UserMapper,
    private val jsonPlaceHolderApi: JsonPlaceHolderApi
) : UserRepository {
    override fun getUserById(userId: Int) : LiveData<User> {
        return Transformations.map(userDao.getUserById(userId)) {
            userMapper.transformEntityToPresentation(it)
        }
    }

    override suspend fun fetchAllUsers(): List<UserEntity> {
        return jsonPlaceHolderApi.getAllUsersRequest().map {
            userMapper.transformResponseToEntity(it)
        }
    }
}