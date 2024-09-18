package com.solodev.codingchallenge.domain.repository

import androidx.paging.PagingData
import com.solodev.codingchallenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(term: String, country: String, media: String): Flow<PagingData<Movie>>
    fun searchMovies(term: String, country: String, media: String): Flow<PagingData<Movie>>
    suspend fun upsertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    fun selectMovies(): Flow<List<Movie>>
    suspend fun selectMovie(trackId: String): Movie?
}