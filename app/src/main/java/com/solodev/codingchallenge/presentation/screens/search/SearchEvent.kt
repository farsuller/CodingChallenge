package com.solodev.codingchallenge.presentation.screens.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    data object SearchMovies : SearchEvent()
}
