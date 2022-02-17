package com.joseph.composeplayground.ui.detail

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
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

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            HeaderSection(
                movieDetail = state.movieDetail!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            BodySection(
                movieDetail = state.movieDetail,
            )
        }

    }
}

@Composable
fun BackdropImage(
    modifier: Modifier = Modifier,
    url: String
) {
    val painter = rememberImagePainter(
        data = Constants.BASE_IMAGE_URL_ORIGINAL + url,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
    )
}

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (posterImage, title, originalTitle, informationBoxes) = createRefs()

        PosterImage(
            url = movieDetail.posterPath ?: "",
            modifier = Modifier.constrainAs(posterImage) {
                top.linkTo(anchor = parent.top, margin = 144.dp)
                start.linkTo(anchor = parent.start)
            }
        )

        Title(
            text = movieDetail.title ?: "",
            modifier = Modifier.constrainAs(title) {
                top.linkTo(anchor = posterImage.top, margin = 88.dp)
                start.linkTo(anchor = posterImage.end, margin = 18.dp)
            }
        )

        OriginalTitle(
            text = movieDetail.originalTitle ?: "",
            modifier = Modifier.constrainAs(originalTitle) {
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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
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
fun PosterImage(
    url: String,
    modifier: Modifier = Modifier
) {
    val painter = rememberImagePainter(
        data = Constants.BASE_IMAGE_URL_ORIGINAL + url,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .size(120.dp, 180.dp)
    )
}

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontFamily = Suit,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        maxLines = 2,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Composable
fun OriginalTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontFamily = Suit,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFFB1B1B1),
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
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
            .clip(RoundedCornerShape(5.dp))
            .size(48.dp)
            .background(Color.White)
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
fun BodySection(
    movieDetail: MovieDetail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if(!movieDetail.tagline.isNullOrBlank()) {
            TagLine(movieDetail.tagline)
        }

        Spacer(modifier = Modifier.height(22.dp))
        ContentWithHeader(
            header = { ContentLabel("Description") },
            content = { Content(text = movieDetail.overview ?: "정보없음") }
        )
        Spacer(modifier = Modifier.height(18.dp))
        ContentWithHeader(
            header = { ContentLabel("ReleaseDate") },
            content = { Content(text = movieDetail.releaseDate ?: "정보없음") }
        )
    }
}

@Composable
fun TagLine(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Text(
            text = "\"$text\"",
            fontSize = 12.sp,
            color = Color.White,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Thin,
            fontFamily = Suit,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
        )
    }
}

@Composable
fun ContentWithHeader(
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        header()
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
fun ContentLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontFamily = Suit,
        textAlign = TextAlign.Start,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Composable
fun Content(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.White,
        fontWeight = FontWeight.Light,
        fontFamily = Suit,
        lineHeight = 16.sp,
        textAlign = TextAlign.Start,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Composable
fun ErrorScreen() {

}

@Composable
fun LoadingScreen() {

}