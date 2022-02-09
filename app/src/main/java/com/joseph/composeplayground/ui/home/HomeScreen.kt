package com.joseph.composeplayground.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.joseph.composeplayground.R
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.ui.home.dto.HomeAction
import com.joseph.composeplayground.ui.home.dto.HomeState
import com.joseph.composeplayground.ui.theme.ComposePlaygroundTheme
import com.joseph.composeplayground.ui.theme.MainBlue
import com.joseph.composeplayground.ui.theme.NetflixRed
import com.joseph.composeplayground.ui.theme.Suit
import com.joseph.composeplayground.util.Constants.BASE_IMAGE_URL_500
import com.joseph.composeplayground.util.Constants.BASE_IMAGE_URL_ORIGINAL

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navigateToDetailScreen = { id -> id })
}

typealias MovieId = Int

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailScreen: (MovieId) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    ComposePlaygroundTheme {
        Surface(
            color = MainBlue,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                TitleText()
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar()
                Spacer(modifier = Modifier.height(26.dp))
                ListSectionHeader(text = "UpComingMovies")
                Spacer(modifier = Modifier.height(12.dp))
                UpComingMovieList(
                    upComingMoviesState = uiState.value.upComingMoviesState,
                    navigateToDetailScreen = navigateToDetailScreen,
                    onEndReached = { viewModel.onAction(HomeAction.FetchUpComingMovieList) }
                )
                Spacer(modifier = Modifier.height(26.dp))
                ListSectionHeader(text = "PopularMovies")
                Spacer(modifier = Modifier.height(12.dp))
                PopularMovieList(
                    popularMoviesState = uiState.value.popularMoviesState,
                    navigateToDetailScreen = navigateToDetailScreen,
                    onEndReached = { viewModel.onAction(HomeAction.FetchUpComingMovieList) }
                )
            }
        }
    }
}

@Preview
@Composable
fun TitleText() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .height(20.dp),
            text = "Compose Playground",
            fontFamily = Suit,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .height(13.dp),
            text = "Jetpack Compose + MVI",
            fontFamily = Suit,
            fontSize = 10.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun SearchBar(
    onSearch: (String) -> Unit = { it }
) {
    val inputText by remember { mutableStateOf("Search Movie") }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(34.dp)
            .background(Color(0xFFF0F0F0)),
    ) {
        Image(
            modifier = Modifier
                .size(34.dp)
                .padding(12.dp),
            painter = painterResource(id = com.joseph.composeplayground.R.drawable.ic_search),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color(0xFFA9A9A9))
        )

        BasicTextField(
            value = inputText,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .wrapContentHeight(),
            onValueChange = onSearch,
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = Suit,
                fontWeight = FontWeight.Light,
                color = Color(0xFFA9A9A9),
                textAlign = TextAlign.Start,
                fontSize = 10.sp
            ),
        )
    }
}

@Composable
fun ListSectionHeader(
    text: String
) {
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        text = text,
        fontFamily = Suit,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 14.sp
    )
}

@Composable
fun UpComingMovieList(
    upComingMoviesState: HomeState.MovieListState,
    navigateToDetailScreen: (MovieId) -> Unit,
    onEndReached: () -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        itemsIndexed(
            items = upComingMoviesState.movies,
            key = { _, movie -> movie.id!! }) { index, upComingMovie ->

            if (index >= upComingMoviesState.movies.size - 3 && !upComingMoviesState.endReached) {
                onEndReached.invoke()
            }

            MovieItemTypeA(movie = upComingMovie, navigateToDetailScreen = navigateToDetailScreen)
        }
    }
}

@Composable
fun MovieItemTypeA(
    movie: Movie,
    navigateToDetailScreen: ((MovieId) -> Unit)
) {
    Box(
        modifier = Modifier
            .size(width = 180.dp, height = 260.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable {
                navigateToDetailScreen(movie.id!!)
            },
    ) {
        val painter = rememberImagePainter(
            data = BASE_IMAGE_URL_500 + movie.posterPath,
            builder = {
                crossfade(true)
            }
        )

        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            painter = painter,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop
        )

        if (painter.state is ImagePainter.State.Loading) {
            CircularProgressIndicator(
                color = MainBlue,
                modifier = Modifier
                    .scale(0.5F)
                    .align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(122.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
        ) {}

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp, vertical = 12.dp)
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = movie.title ?: "",
                fontFamily = Suit,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = movie.originalTitle ?: "",
                fontFamily = Suit,
                fontWeight = FontWeight.Light,
                color = Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun PopularMovieList(
    popularMoviesState: HomeState.MovieListState,
    navigateToDetailScreen: (MovieId) -> Unit,
    onEndReached: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = popularMoviesState.movies,
            key = { _, movie -> movie.id!! }) { index, popularMovie ->

            if (index >= popularMoviesState.movies.size - 3 && !popularMoviesState.endReached) {
                onEndReached.invoke()
            }

            MovieItemTypeB(movie = popularMovie, navigateToDetailScreen = navigateToDetailScreen)
        }
    }
}

@Composable
fun MovieItemTypeB(
    movie: Movie,
    navigateToDetailScreen: ((MovieId) -> Unit)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(240.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable {
                navigateToDetailScreen.invoke(movie.id!!)
            }
    ) {
        val painter = rememberImagePainter(
            data = BASE_IMAGE_URL_ORIGINAL + movie.backdropPath,
            builder = {
                crossfade(true)
            }
        )

        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            painter = painter,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(122.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
        ) {}

        Column(
            modifier = Modifier
                .width(250.dp)
                .padding(horizontal = 10.dp, vertical = 12.dp)
                .wrapContentHeight()
                .align(Alignment.BottomEnd)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = movie.title ?: "",
                fontFamily = Suit,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = movie.originalTitle ?: "",
                fontFamily = Suit,
                fontWeight = FontWeight.Light,
                color = Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.End
            )
        }

        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .size(32.dp)
                    .clip(RoundedCornerShape(5.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(10.dp),
                    painter = painterResource(
                        id = R.drawable.ic_star
                    ),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = movie.voteAverage.toString(),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Suit,
                    color = Color(0xFF6D6D6D),
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .size(32.dp)
                    .clip(RoundedCornerShape(5.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(movie.adult == true) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "성인",
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Suit,
                        color = NetflixRed,
                        textAlign = TextAlign.Center,
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "전체",
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Suit,
                        color = Color(0xFF6D6D6D),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}