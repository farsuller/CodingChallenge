package com.solodev.codingchallenge.domain.usecase.movies

import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class SelectMovies(
    private val moviesRepository: MovieRepository,
) {
    operator fun invoke(): Flow<List<Movie>> {
        return moviesRepository.selectMovies()
    }
}
