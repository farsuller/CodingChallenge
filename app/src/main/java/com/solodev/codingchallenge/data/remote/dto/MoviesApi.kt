package com.solodev.codingchallenge.data.remote.dto

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("search")
    suspend fun getMovies(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): MovieResponse
}