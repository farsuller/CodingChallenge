package com.solodev.codingchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.solodev.codingchallenge.data.local.MovieDao
import com.solodev.codingchallenge.data.remote.MoviesPagingSource
import com.solodev.codingchallenge.data.remote.SearchMoviesPagingSource
import com.solodev.codingchallenge.data.remote.dto.MoviesApi
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MoviesApi,
    val moviesDao: MovieDao
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

    override fun searchMovies(
        term: String,
        country: String,
        media: String
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchMoviesPagingSource(
                    moviesApi = apiService,
                    term = term,
                    country = country,
                    media = media
                )
            },
        ).flow
    }

    override suspend fun upsertMovie(movie: Movie) {
        return moviesDao.upsert(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        return moviesDao.delete(movie)
    }

    override fun selectMovies(): Flow<List<Movie>> {
        return moviesDao.getMovies()
    }

    override suspend fun selectMovie(trackId: String): Movie? {
        return moviesDao.getMovie(trackId = trackId)
    }
}