package com.solodev.codingchallenge.domain.repository

import com.solodev.codingchallenge.domain.model.Movie

interface MovieRepository {
    suspend fun fetchMoviesFromApi(term: String, country: String, media: String): List<Movie>?
}