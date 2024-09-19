package com.solodev.codingchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.solodev.codingchallenge.domain.usecase.MoviesUseCases
import com.solodev.codingchallenge.presentation.screens.bookmark.BookmarkViewModel
import com.solodev.codingchallenge.utils.dummyMovie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookmarkViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: BookmarkViewModel
    private lateinit var moviesUseCases: MoviesUseCases

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        moviesUseCases = mockk()
        coEvery { moviesUseCases.selectMovies() } returns flow {
            emit(listOf(dummyMovie))
        }
        viewModel = BookmarkViewModel(moviesUseCases)
    }

    @Test
    fun `getMovies should update state with reversed movies`() = runTest {
        advanceUntilIdle()
        val expectedMovies = listOf(dummyMovie)
        assertEquals(expectedMovies, viewModel.state.value.movies)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


}