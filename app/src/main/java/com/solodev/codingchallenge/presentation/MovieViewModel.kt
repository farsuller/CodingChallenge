package com.solodev.codingchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> = _movies

    init {
        fetchMovies("star", "au", "movie")
    }

    private fun fetchMovies(term: String, country: String, media: String) {
        viewModelScope.launch {
            val movies = repository.fetchMoviesFromApi(term, country, media)
            _movies.value = movies
        }
    }
}