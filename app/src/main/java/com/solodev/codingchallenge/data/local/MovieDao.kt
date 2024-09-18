package com.solodev.codingchallenge.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.solodev.codingchallenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE trackId=:trackId")
    suspend fun getMovie(trackId: String): Movie?
}