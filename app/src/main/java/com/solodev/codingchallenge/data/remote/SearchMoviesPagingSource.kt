package com.solodev.codingchallenge.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.solodev.codingchallenge.data.remote.dto.MoviesApi
import com.solodev.codingchallenge.domain.model.Movie

class SearchMoviesPagingSource(
    private val moviesApi: MoviesApi,
    private val term: String,
    private val country: String,
    private val media: String,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val moviesResponse = moviesApi.searchMovies(term = term, country = country, media = media)

            val movies = moviesResponse.results.distinctBy { it.trackName }

            LoadResult.Page(
                data = movies,
                nextKey = null,
                prevKey = null,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e,
            )
        }
    }
}
