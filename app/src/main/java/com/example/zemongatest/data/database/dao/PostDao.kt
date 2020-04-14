package com.example.zemongatest.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zemongatest.data.database.entitity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * from post ORDER BY idPost")
    fun getAllPost(): LiveData<List<PostEntity>>

    @Query("SELECT * from post WHERE idPost = :idPost")
    fun getPostById(idPost: Int): LiveData<PostEntity>

    @Query("SELECT * from post WHERE isFavorite = 1")
    fun getAllFavoritePost(): LiveData<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(post: List<PostEntity>)

    @Query("DELETE FROM post")
    suspend fun deleteAll()

    @Query("DELETE FROM post WHERE idPost = :idPost")
    suspend fun deleteByIdPost(idPost: Int)

    @Query("UPDATE post SET wasRead = :wasRead WHERE idPost = :idPost")
    suspend fun updateWasRead(idPost: Int, wasRead: Boolean)

    @Query("UPDATE post SET isFavorite = :isFavorite WHERE idPost = :idPost")
    suspend fun updateIsFavorite(idPost: Int, isFavorite: Boolean)
}