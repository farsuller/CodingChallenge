package com.solodev.codingchallenge.presentation.screens.details

import com.solodev.codingchallenge.domain.model.Movie


sealed class DetailsEvent {
    data class UpsertDeleteMovie(val movie: Movie) : DetailsEvent()
    data object RemoveSideEffect : DetailsEvent()
}
