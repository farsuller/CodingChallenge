package com.solodev.codingchallenge.presentation.screens.bookmark

import com.solodev.codingchallenge.domain.model.Movie

data class BookmarkState(
    val movies: List<Movie> = emptyList(),
)
