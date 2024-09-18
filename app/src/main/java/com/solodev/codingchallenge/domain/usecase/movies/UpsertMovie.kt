package com.solodev.codingchallenge.domain.usecase.movies

import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository

class UpsertMovie(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) {
        movieRepository.upsertMovie(movie = movie)
    }
}
