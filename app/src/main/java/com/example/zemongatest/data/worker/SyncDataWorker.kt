package com.example.zemongatest.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.zemongatest.data.PostRepository
import com.example.zemongatest.data.RepositoryFactory
import com.example.zemongatest.data.UserRepository
import com.example.zemongatest.data.database.entitity.PostEntity
import com.example.zemongatest.data.database.room.PostDatabase
import com.example.zemongatest.data.mapper.PostMapper
import com.example.zemongatest.data.mapper.UserMapper
import com.example.zemongatest.data.network.retrofit.RetrofitClient
import kotlinx.coroutines.coroutineScope


const val MAX_POST_UNREADED = 20

class SyncDataWorker(
    context: Context,
    workerParams: WorkerParameters
) :
    CoroutineWorker(context, workerParams) {
    companion object {
        private val TAG = SyncDataWorker::class.java.simpleName
    }


    override suspend fun doWork(): Result = coroutineScope {
        try {
            val postRepository = RepositoryFactory.getPostRespositoryInstace(
                PostDatabase.getInstance(applicationContext).postDao(),
                PostMapper(),
                RetrofitClient.getServiceClient()
            )

            val userRepository =  RepositoryFactory.getUserRepositoryInstace(
                PostDatabase.getInstance(applicationContext).userDao(),
                UserMapper(),
                RetrofitClient.getServiceClient()
            )

            val serviceRepose = postRepository.fetchAllPost()

            val database = PostDatabase.getInstance(applicationContext)
            database.postDao().insertAll(getUnReadPosts(serviceRepose))
            database.postDao().insertAll(getReadPosts(serviceRepose))
            database.userDao().insertAll(userRepository.fetchAllUsers())
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    private fun getUnReadPosts(serviceRepose: List<PostEntity>): List<PostEntity> {
        return serviceRepose.toMutableList().take(MAX_POST_UNREADED).map {
            it.apply {
                wasRead = false
            }
        }

    }

    private fun getReadPosts(serviceRepose: List<PostEntity>): List<PostEntity> {
        return serviceRepose.toMutableList().subList(20, serviceRepose.size)
    }
}