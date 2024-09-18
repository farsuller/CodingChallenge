package com.solodev.codingchallenge.presentation.screens.onboarding

import androidx.annotation.DrawableRes
import com.solodev.codingchallenge.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Get the Latest Movies",
        description = "New Movies, TV Shows, and Updates",
        image = R.drawable.onboarding1,
    ),
    Page(
        title = "Discover and Watch",
        description = "Explore Timely Stories from Across the Globe",
        image = R.drawable.onboarding2,
    ),
    Page(
        title = "Movie Recommendations ",
        description = "Tailored to Suit Your Interests",
        image = R.drawable.onboarding3,
    ),
)
