package com.solodev.codingchallenge.domain.usecase.movies

import androidx.paging.PagingData
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class SearchMovies(
    private val moviesRepository: MovieRepository,
) {
    operator fun invoke(term: String, country: String, media: String): Flow<PagingData<Movie>> {
        return moviesRepository.searchMovies(term = term, country = country, media = media)
    }
}