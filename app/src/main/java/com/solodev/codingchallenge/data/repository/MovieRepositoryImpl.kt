package com.solodev.codingchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.solodev.codingchallenge.data.remote.MoviesPagingSource
import com.solodev.codingchallenge.data.remote.dto.MoviesApi
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MoviesApi,
) : MovieRepository {

    override fun getMovies(term: String, country: String, media: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MoviesPagingSource(
                    moviesApi = apiService,
                    term = term,
                    country = country,
                    media = media
                )
            },
        ).flow
    }

    override suspend fun upsertMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun selectMovies(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
}