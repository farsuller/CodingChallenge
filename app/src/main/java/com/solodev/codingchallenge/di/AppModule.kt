package com.solodev.codingchallenge.di

import com.solodev.codingchallenge.domain.repository.MovieRepository
import com.solodev.codingchallenge.domain.usecase.GetMovies
import com.solodev.codingchallenge.domain.usecase.MoviesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesUseCases(
        moviesRepository: MovieRepository,
    ): MoviesUseCases {
        return MoviesUseCases(
            getMovies = GetMovies(moviesRepository),
        )
    }
}