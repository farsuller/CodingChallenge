package com.solodev.codingchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.solodev.codingchallenge.domain.usecase.MoviesUseCases
import com.solodev.codingchallenge.presentation.screens.search.SearchEvent
import com.solodev.codingchallenge.presentation.screens.search.SearchViewModel
import com.solodev.codingchallenge.utils.dummyMovie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchViewModel
    private lateinit var moviesUseCases: MoviesUseCases

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        moviesUseCases = mockk()
        coEvery { moviesUseCases.searchMovies(any(), any(), any()) } returns flow {
            listOf(dummyMovie)
        }
        viewModel = SearchViewModel(moviesUseCases)
    }

    @Test
    fun `should update search query correctly`() {
        val query = "star"
        viewModel.onEvent(SearchEvent.UpdateSearchQuery(query))
        assertEquals(query, viewModel.state.value.searchQuery)
    }

    @Test
    fun `should update movies on search`() = runTest {
        val pagingData = viewModel.dummyMovieList.first().toList()
        val expectedPagingData = listOf(dummyMovie)
        assertEquals(expectedPagingData, pagingData)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}