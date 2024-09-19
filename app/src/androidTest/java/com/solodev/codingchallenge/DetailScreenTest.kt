package com.solodev.codingchallenge

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.presentation.screens.details.DetailScreen
import com.solodev.codingchallenge.presentation.screens.details.components.DetailTopBar
import com.solodev.codingchallenge.utils.Constants.TestTags.DETAIL_DESCRIPTION
import com.solodev.codingchallenge.utils.Constants.TestTags.DETAIL_IMAGE
import com.solodev.codingchallenge.utils.Constants.TestTags.DETAIL_TITLE
import com.solodev.codingchallenge.utils.Constants.TestTags.DETAIL_TOP_BAR

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailTopBar_Assert_DetailTopBarDisplayed() {
        composeTestRule.setContent {
            DetailTopBar(
                onBrowsingClick = {},
                onShareClick = {},
                onBookmarkClick = {},
                onBackClick = {},
            )
        }

        composeTestRule.onNodeWithTag(DETAIL_TOP_BAR).assertExists()

        composeTestRule.onNodeWithContentDescription("BackButton").assertExists()
        composeTestRule.onNodeWithContentDescription("BackButton").performClick()

        composeTestRule.onNodeWithContentDescription("BookmarkButton").assertExists()
        composeTestRule.onNodeWithContentDescription("BookmarkButton").performClick()

        composeTestRule.onNodeWithContentDescription("ShareButton").assertExists()
        composeTestRule.onNodeWithContentDescription("ShareButton").performClick()

        composeTestRule.onNodeWithContentDescription("BrowseButton").assertExists()
        composeTestRule.onNodeWithContentDescription("BrowseButton").performClick()
    }

    @Test
    fun detailScreen_Assert_DetailScreenDisplayed() {
        composeTestRule.setContent {
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

        composeTestRule.onNodeWithTag(DETAIL_IMAGE).assertExists()

        composeTestRule.onNodeWithTag(DETAIL_TITLE).assertExists()

        composeTestRule.onNodeWithTag(DETAIL_DESCRIPTION).assertExists()
    }
}
