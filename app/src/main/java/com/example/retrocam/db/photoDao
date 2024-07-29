package com.example.Retrocam.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos WHERE albumId = :albumId")
    fun getPhotosByAlbum(albumId: Int): LiveData<List<Photo>>

    @Insert
    suspend fun insert(photo: Photo)
}
