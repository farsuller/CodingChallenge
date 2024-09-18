package com.solodev.codingchallenge.domain.usecase

import androidx.paging.PagingData
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovies(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(term: String, country: String, media: String): Flow<PagingData<Movie>> {
        return movieRepository.getMovies(term = term, country = country, media = media)
    }
}