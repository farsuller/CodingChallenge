package com.solodev.codingchallenge.presentation.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.solodev.codingchallenge.presentation.common.MoviesPreviews
import com.solodev.codingchallenge.ui.theme.CodingChallengeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "BackButton",
                )
            }
        },
        actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(
                    imageVector = Icons.Filled.Bookmark,
                    contentDescription = "BookmarkButton",
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "ShareButton",
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    imageVector = Icons.Filled.OpenInBrowser,
                    contentDescription = "BrowseButton",
                )
            }
        },
    )
}

@MoviesPreviews
@Composable
internal fun DetailTopBarPreview() {
    CodingChallengeTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DetailTopBar(
                onBrowsingClick = { },
                onShareClick = { },
                onBookmarkClick = {},
                onBackClick = {},
            )
        }
    }
}
