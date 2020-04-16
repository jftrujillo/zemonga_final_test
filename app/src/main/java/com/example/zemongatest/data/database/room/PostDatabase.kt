package com.example.zemongatest.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.zemongatest.data.database.dao.CommentDao
import com.example.zemongatest.data.database.dao.PostDao
import com.example.zemongatest.data.database.dao.UserDao
import com.example.zemongatest.data.database.entitity.CommentEntity
import com.example.zemongatest.data.database.entitity.PostEntity
import com.example.zemongatest.data.database.entitity.UserEntity
import com.example.zemongatest.data.worker.SyncDataWorker

@Database(entities = [PostEntity::class, UserEntity::class, CommentEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao

    companion object {
        private const val DATABASE_NAME = "comments_database"

        @Volatile
        private var instance: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PostDatabase {
            return Room.databaseBuilder(
                context, PostDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SyncDataWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }

}