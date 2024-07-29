package com.example.Retrocam.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums ORDER BY timestamp DESC")
    fun getAllAlbums(): LiveData<List<Album>>

    @Insert
    suspend fun insert(album: Album): Long

    @Update
    suspend fun update(album: Album)

    @Delete
    suspend fun delete(album: Album)
}
