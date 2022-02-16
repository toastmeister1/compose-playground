package com.joseph.composeplayground.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.joseph.composeplayground.model.MovieDetail
import com.joseph.composeplayground.ui.detail.dto.DetailState
import com.joseph.composeplayground.ui.theme.MainBlue
import com.joseph.composeplayground.ui.theme.Suit
import com.joseph.composeplayground.util.Constants
import com.joseph.composeplayground.util.LoadState


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState.value.loadState) {
            LoadState.Idle -> {
                DetailMovieInformation(uiState.value)
            }
            LoadState.Loading -> {
                //
            }
            LoadState.Failed -> {
                // Show FailedScreen
            }
        }
    }
}

@Preview
@Composable
fun DetailMovieInformation(
    state: DetailState = DetailState()
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .background(MainBlue)
            .fillMaxSize()
            .scrollable(scrollState, Orientation.Vertical)
    ) {
        BackdropImage(
            url = state.movieDetail?.backdropPath ?: "",
            modifier = Modifier.align(Alignment.TopCenter)
        )
        MoviePreviewSection(
            movieDetail = state.movieDetail!!,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.TopCenter)
                .padding(16.dp),
        )
    }
}

@Composable
fun BackdropImage(
    modifier: Modifier = Modifier,
    url: String
) {
    val backdropPainter = rememberImagePainter(
        data = Constants.BASE_IMAGE_URL_ORIGINAL + url,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = backdropPainter,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
    )
}

@Composable
fun MoviePreviewSection(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail
) {
    val posterPainter = rememberImagePainter(
        data = Constants.BASE_IMAGE_URL_ORIGINAL + movieDetail.posterPath,
        builder = {
            crossfade(true)
        }
    )

    ConstraintLayout(
        modifier = modifier
    ) {
        val (posterImage, title, originalTitle, informationBoxes) = createRefs()

        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .size(120.dp, 180.dp)
                .constrainAs(posterImage) {
                    top.linkTo(anchor = parent.top, margin = 144.dp)
                    start.linkTo(anchor = parent.start)
                }
        ) {
            Image(
                painter = posterPainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(5.dp))
            )
        }


        Text(
            text = movieDetail.title ?: "",
            fontFamily = Suit,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(anchor = posterImage.top, margin = 88.dp)
                    start.linkTo(anchor = posterImage.end, margin = 18.dp)
                }
        )

        Text(
            text = movieDetail.originalTitle ?: "",
            fontFamily = Suit,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFFB1B1B1),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(originalTitle) {
                    top.linkTo(anchor = title.bottom, margin = 4.dp)
                    start.linkTo(anchor = title.start)
                }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .constrainAs(informationBoxes) {
                    top.linkTo(anchor = originalTitle.bottom, margin = 12.dp)
                    start.linkTo(anchor = originalTitle.start)
                },
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InformationBox(
                title = "평점",
                content = movieDetail.voteAverage.toString()
            )

            InformationBox(
                title = "등급",
                content = if (movieDetail.adult == true) "성인" else "전체"
            )
        }

    }
}

@Composable
fun InformationBox(
    title: String,
    content: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(48.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(5.dp)),
    ) {
        Text(
            text = title,
            fontSize = 10.sp,
            color = MainBlue,
            fontWeight = FontWeight.Bold,
            fontFamily = Suit,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),

        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = content,
            fontSize = 10.sp,
            color = MainBlue,
            fontWeight = FontWeight.Light,
            fontFamily = Suit,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
    }
}

@Composable
fun ErrorScreen() {

}

@Composable
fun LoadingScreen() {

}