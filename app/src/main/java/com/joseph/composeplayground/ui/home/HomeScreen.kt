package com.joseph.composeplayground.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.transform.GrayscaleTransformation
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.ui.theme.ComposePlaygroundTheme
import com.joseph.composeplayground.ui.theme.MainBlue
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
    
    ComposePlaygroundTheme {
        Surface(
            color = MainBlue,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                TitleText()
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar()
                Spacer(modifier = Modifier.height(26.dp))
                UpComingMoviesTitle()
                Spacer(modifier = Modifier.height(12.dp))
                UpComingMovieList(
                    movieList = uiState.value.upComingMovieList.movies,
                    navigateToDetailScreen = navigateToDetailScreen
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
fun UpComingMoviesTitle() {
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        text = "UpComing Movies",
        fontFamily = Suit,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 14.sp
    )
}

@Composable
fun UpComingMovieList(
    movieList: List<Movie>,
    navigateToDetailScreen: (MovieId) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        itemsIndexed(items = movieList, key = { _, movie -> movie.id!! }) { index, movie ->
            MovieItem(movie = movie, navigateToDetailScreen = navigateToDetailScreen)
        }
    }
}

@Composable
fun MovieItem(
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
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .scale(0.5F)
                        .align(Alignment.Center)
                )
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