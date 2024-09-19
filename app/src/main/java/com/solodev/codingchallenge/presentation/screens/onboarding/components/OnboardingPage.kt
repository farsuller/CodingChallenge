package com.solodev.codingchallenge.presentation.screens.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.solodev.codingchallenge.utils.MoviesPreviews
import com.solodev.codingchallenge.presentation.screens.onboarding.Page
import com.solodev.codingchallenge.presentation.screens.onboarding.pages
import com.solodev.codingchallenge.ui.theme.CodingChallengeTheme
import com.solodev.codingchallenge.utils.Dimens.MediumPadding1
import com.solodev.codingchallenge.utils.Dimens.MediumPadding2

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    page: Page,
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6F),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            modifier = Modifier.padding(horizontal = MediumPadding2),
            text = page.title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            modifier = Modifier.padding(horizontal = MediumPadding2),
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@MoviesPreviews
@Composable
fun OnboardingPagePreview() {
    CodingChallengeTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            OnboardingPage(page = pages[0])
        }
    }
}
