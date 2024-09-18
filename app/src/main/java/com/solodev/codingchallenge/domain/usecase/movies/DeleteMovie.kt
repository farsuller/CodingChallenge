package com.solodev.codingchallenge.domain.usecase.movies


import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository

class DeleteMovie(
    private val moviesRepository: MovieRepository,
) {
    suspend operator fun invoke(movie: Movie) {
        moviesRepository.deleteMovie(movie = movie)
    }
}
