package com.solodev.codingchallenge.data.remote

import com.solodev.codingchallenge.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("search")
    suspend fun searchMovies(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): Movie
}