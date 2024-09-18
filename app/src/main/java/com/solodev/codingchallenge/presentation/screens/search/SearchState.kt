package com.solodev.codingchallenge.presentation.screens.search

import androidx.paging.PagingData
import com.solodev.codingchallenge.domain.model.Movie

import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val movies: Flow<PagingData<Movie>>? = null,
)
