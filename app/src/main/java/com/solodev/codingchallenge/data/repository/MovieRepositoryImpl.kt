package com.solodev.codingchallenge.data.repository

import com.solodev.codingchallenge.data.remote.MoviesApi
import com.solodev.codingchallenge.domain.model.MoviesResult
import com.solodev.codingchallenge.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MoviesApi,
) : MovieRepository {
    override suspend fun fetchMoviesFromApi(
        term: String,
        country: String,
        media: String
    ): List<MoviesResult> {
        val response = apiService.searchMovies(term, country, media)
        return response.results
    }
}