package com.solodev.codingchallenge.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.ui.theme.CodingChallengeTheme
import com.solodev.codingchallenge.utils.Constants
import com.solodev.codingchallenge.utils.MoviesPreviews
import com.solodev.codingchallenge.utils.dummyMovie

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: () -> Unit,
) {
    val context = LocalContext.current

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Constants.Elevation.level0),
    ) {
        Row(
            modifier = modifier
                .padding(all = 10.dp)
                .clickable {
                    onClick()
                },
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(height = 130.dp, width = 90.dp)
                    .clip(MaterialTheme.shapes.medium),
                model = ImageRequest
                    .Builder(context)
                    .data(movie.artworkUrl100)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),

                ) {
                Text(
                    text = movie.trackName ?: "Default Title",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = "${movie.currency} ${movie.trackPrice}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = movie.primaryGenreName ?: "Default Genre",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    text = movie.contentAdvisoryRating ?: "Default Rating",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    text = movie.shortDescription?: "Default Description",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 15.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

@Composable
fun MovieCardBookmarked(
    movie: Movie,
    onClick: () -> Unit,
) {
    val context = LocalContext.current

    AsyncImage(
        modifier = Modifier
            .size(height = 120.dp, width = 70.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() },
        model = ImageRequest
            .Builder(context)
            .data(movie.artworkUrl100)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
    )
}

@MoviesPreviews
@Composable
internal fun MovieCardBookmarkedPreview() {
    CodingChallengeTheme {
        Surface {
            MovieCardBookmarked(
                movie = dummyMovie,
                onClick = {}
            )
        }
    }
}

@MoviesPreviews
@Composable
internal fun MoviesCardPreview() {
    CodingChallengeTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            MovieCard(
                movie = dummyMovie,
                onClick = {},
            )
        }
    }
}
