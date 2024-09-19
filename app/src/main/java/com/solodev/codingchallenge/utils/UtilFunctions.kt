package com.solodev.codingchallenge.utils

import android.content.Context
import com.solodev.codingchallenge.domain.model.Movie
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val dummyMovie = Movie(
    trackId = 317909453,
    wrapperType = "track",
    kind = "feature-movie",
    collectionId = 1422441683,
    artistName = "J.J. Abrams",
    collectionName = "Star Trek: 3 Movie Collection",
    trackName = "Star Trek",
    collectionCensoredName = "Star Trek, Star Trek into Darkness, Star Trek Beyond: 3 Film Collection",
    trackCensoredName = "Star Trek",
    collectionArtistId = 1008915738,
    collectionArtistViewUrl = "",
    collectionViewUrl = "",
    trackViewUrl = "",
    previewUrl = "",
    artworkUrl30 = "",
    artworkUrl60 = "",
    artworkUrl100 = "",
    collectionPrice = 279.00,
    trackPrice = 279.00,
    trackRentalPrice = 149.00,
    collectionHdPrice = 399.00,
    trackHdPrice = 399.00,
    trackHdRentalPrice = 149.00,
    releaseDate = "2009-05-08T07:00:00Z",
    collectionExplicitness = "notExplicit",
    trackExplicitness = "notExplicit",
    discCount = 1,
    discNumber = 1,
    trackCount = 3,
    trackNumber = 1,
    trackTimeMillis = 7606975,
    country = "PHL",
    currency = "PHP",
    primaryGenreName = "Sci-Fi & Fantasy",
    contentAdvisoryRating = "G",
    hasITunesExtras = true,
    shortDescription = "",
    longDescription = ""
)

fun Context.sharedPreferences(name: String) = SharedPreferenceDelegate(this, name)

fun formatDateTimeVersionCodeO(inputDateTime: String): String {
    val dateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ISO_DATE_TIME)
    val month = dateTime.month.toString().substring(0, 3)
    val day = dateTime.dayOfMonth.toString().padStart(2, '0')
    val year = dateTime.year

    return "$month. $day, $year"
}
