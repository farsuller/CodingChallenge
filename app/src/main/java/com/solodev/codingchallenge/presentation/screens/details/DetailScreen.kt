package com.solodev.codingchallenge.presentation.screens.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solodev.codingchallenge.R
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.presentation.common.MoviesPreviews
import com.solodev.codingchallenge.presentation.screens.details.components.DetailTopBar
import com.solodev.codingchallenge.ui.theme.CodingChallengeTheme

@Composable
fun DetailScreen(
    movie: Movie,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        DetailTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(movie.previewUrl)
                    context.startActivity(it)
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, movie.collectionArtistViewUrl)
                    it.type = "text/plain"
                    context.startActivity(it)
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteMovie(movie)) },
            onBackClick = navigateUp,
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 24.dp,
                end = 24.dp,
                top = 24.dp,
            ),
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest
                        .Builder(context)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .data(movie.artworkUrl100)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier,
                    text = movie.trackName,
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 30.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 30.sp,
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier,
                    text = movie.longDescription,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 25.sp,
                )
            }
        }
    }
}

@MoviesPreviews
@Composable
internal fun DetailScreenPreview() {
    CodingChallengeTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DetailScreen(
                movie = Movie(
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
                    collectionArtistViewUrl = "https://itunes.apple.com/ph/artist/paramount-home-entertainment-inc/1008915738?uo=4",
                    collectionViewUrl = "https://itunes.apple.com/ph/movie/star-trek/id317909453?uo=4",
                    trackViewUrl = "https://itunes.apple.com/ph/movie/star-trek/id317909453?uo=4",
                    previewUrl = "https://video-ssl.itunes.apple.com/itunes-assets/Video118/v4/96/71/a5/9671a5c3-6f2d-115c-3f5e-f02abd3f7c34/mzvf_7409264001548365958.640x356.h264lc.U.p.m4v",
                    artworkUrl30 = "https://is1-ssl.mzstatic.com/image/thumb/Video2/v4/c4/14/0d/c4140da9-181f-3033-9f27-e11e9729c140/pr_source.lsr/30x30bb.jpg",
                    artworkUrl60 = "https://is1-ssl.mzstatic.com/image/thumb/Video2/v4/c4/14/0d/c4140da9-181f-3033-9f27-e11e9729c140/pr_source.lsr/60x60bb.jpg",
                    artworkUrl100 = "https://is1-ssl.mzstatic.com/image/thumb/Video2/v4/c4/14/0d/c4140da9-181f-3033-9f27-e11e9729c140/pr_source.lsr/100x100bb.jpg",
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
                    shortDescription = "The greatest adventure of all time begins with Star Trek, the incredible story of a young crew's",
                    longDescription = "The greatest adventure of all time begins with Star Trek, the incredible story of a young crew's maiden voyage onboard the most advanced starship ever created: the U.S.S. Enterprise. On a journey filled with action, comedy and cosmic peril, the new recruits must find a way to stop an evil being whose mission of vengeance threatens all of mankind. The fate of the galaxy rests in the hands of bitter rivals. One, James Kirk (Chris Pine), is a delinquent, thrill-seeking Iowa farm boy. The other, Spock (Zachary Quinto), was raised in a logic-based society that rejects all emotion. As fiery instinct clashes with calm reason, their unlikely but powerful partnership is the only thing capable of leading their crew through unimaginable danger, boldly going where no one has gone before.",
                ),
                event = {},
                navigateUp = {},
            )
        }
    }
}
