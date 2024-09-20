package com.solodev.codingchallenge.di

import android.app.Application
import com.solodev.codingchallenge.data.manager.LocalUserManagerImpl
import com.solodev.codingchallenge.domain.manager.LocalUserManager
import com.solodev.codingchallenge.domain.repository.MovieRepository
import com.solodev.codingchallenge.domain.usecase.movies.GetMovies
import com.solodev.codingchallenge.domain.usecase.MoviesUseCases
import com.solodev.codingchallenge.domain.usecase.appentry.AppEntryUseCases
import com.solodev.codingchallenge.domain.usecase.appentry.ReadAppEntry
import com.solodev.codingchallenge.domain.usecase.appentry.SaveAppEntry
import com.solodev.codingchallenge.domain.usecase.movies.DeleteMovie
import com.solodev.codingchallenge.domain.usecase.movies.SearchMovies
import com.solodev.codingchallenge.domain.usecase.movies.SelectMovie
import com.solodev.codingchallenge.domain.usecase.movies.SelectMovies
import com.solodev.codingchallenge.domain.usecase.movies.UpsertMovie
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
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)



    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager,
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager),
    )

    @Provides
    @Singleton
    fun provideMoviesUseCases(
        moviesRepository: MovieRepository,
    ): MoviesUseCases {
        return MoviesUseCases(
            getMovies = GetMovies(moviesRepository),
            searchMovies = SearchMovies(moviesRepository),
            upsertMovie = UpsertMovie(moviesRepository),
            deleteMovie = DeleteMovie(moviesRepository),
            selectMovies = SelectMovies(moviesRepository),
            selectMovie = SelectMovie(moviesRepository),
        )
    }
}