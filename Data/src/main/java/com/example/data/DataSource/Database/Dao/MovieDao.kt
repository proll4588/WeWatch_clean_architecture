package com.example.data.DataSource.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.DataSource.Database.Entity.DBMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM DBMovieEntity")
    fun getAllMovies(): Flow<List<DBMovieEntity>>

    @Insert
    suspend fun insert(movie: DBMovieEntity)

    @Delete
    suspend fun delete(movie: DBMovieEntity)
}