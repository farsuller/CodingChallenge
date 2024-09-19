package com.solodev.codingchallenge.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.usecase.MoviesUseCases
import com.solodev.codingchallenge.utils.dummyMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    moviesUseCases: MoviesUseCases,
) : ViewModel() {

    val movies = moviesUseCases.getMovies(term = "star", country = "ph", media = "movie")
        .cachedIn(viewModelScope)

    val dummyMovieList: Flow<List<Movie>> = flow {
        emit(listOf(dummyMovie))
    }
}
