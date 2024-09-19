package com.solodev.codingchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.solodev.codingchallenge.domain.usecase.MoviesUseCases
import com.solodev.codingchallenge.presentation.screens.home.HomeViewModel
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
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var moviesUseCases: MoviesUseCases

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        moviesUseCases = mockk()
        coEvery { moviesUseCases.getMovies(any(), any(), any()) } returns flow {
            listOf(dummyMovie)
        }
        viewModel = HomeViewModel(moviesUseCases)
    }

    @Test
    fun `getMovies should emit paging data with movies`() = runTest {
        val pagingData = viewModel.dummyMovieList.first().toList()

        val expectedPagingData = listOf(dummyMovie)
        assertEquals(expectedPagingData, pagingData)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}