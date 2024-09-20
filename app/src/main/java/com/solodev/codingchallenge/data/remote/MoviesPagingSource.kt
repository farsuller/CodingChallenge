package com.solodev.codingchallenge.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.solodev.codingchallenge.data.remote.dto.MoviesApi
import com.solodev.codingchallenge.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(
    private val moviesApi: MoviesApi,
    private val term: String,
    private val country: String,
    private val media: String,

    ) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val moviesResponse = moviesApi.getMovies(term = term, country = country, media = media)


            LoadResult.Page(
                data = moviesResponse.results.distinctBy { it.trackName },
                nextKey = null,
                prevKey = null,
            )
        } catch (e: IOException) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}
