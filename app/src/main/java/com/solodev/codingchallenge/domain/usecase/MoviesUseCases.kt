package com.solodev.codingchallenge.domain.usecase

import com.solodev.codingchallenge.domain.usecase.movies.DeleteMovie
import com.solodev.codingchallenge.domain.usecase.movies.GetMovies
import com.solodev.codingchallenge.domain.usecase.movies.SearchMovies
import com.solodev.codingchallenge.domain.usecase.movies.SelectMovie
import com.solodev.codingchallenge.domain.usecase.movies.SelectMovies
import com.solodev.codingchallenge.domain.usecase.movies.UpsertMovie

data class MoviesUseCases (
  val getMovies: GetMovies,
  val searchMovies: SearchMovies,
  val upsertMovie: UpsertMovie,
  val deleteMovie: DeleteMovie,
  val selectMovies: SelectMovies,
  val selectMovie: SelectMovie,
)