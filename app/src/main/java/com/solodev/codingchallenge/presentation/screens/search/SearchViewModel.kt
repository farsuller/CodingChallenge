package com.solodev.codingchallenge.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
class SearchViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchMovies -> {
                searchMovies()
            }

            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }
        }
    }

    private fun searchMovies() {
        val movies = moviesUseCases.searchMovies(
            term = state.value.searchQuery, country = "ph", media = "movie",
        ).cachedIn(viewModelScope)

        _state.value = state.value.copy(movies = movies)
    }

    val dummyMovieList: Flow<List<Movie>> = flow {
        emit(listOf(dummyMovie))
    }
}
