package com.example.Retrocam.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "photos",
    foreignKeys = [ForeignKey(
        entity = Album::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("albumId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Photo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0 ,
    val albumId: Int,
    val url: String,
    val title: String,
    val timestamp: Long
)
