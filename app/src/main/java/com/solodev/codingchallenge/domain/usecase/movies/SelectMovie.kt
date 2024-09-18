package com.solodev.codingchallenge.domain.usecase.movies

import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository

class SelectMovie(
    private val moviesRepository: MovieRepository,
) {
    suspend operator fun invoke(trackId: String): Movie? {
        return moviesRepository.selectMovie(trackId)
    }
}
