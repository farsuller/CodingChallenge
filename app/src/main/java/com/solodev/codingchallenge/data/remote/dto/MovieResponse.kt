package com.solodev.codingchallenge.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.solodev.codingchallenge.domain.model.Movie

data class MovieResponse(

    @SerializedName("resultCount")
    val resultCount: Int,

    @SerializedName("results")
    val results: List<Movie>
)