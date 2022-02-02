package com.joseph.composeplayground.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.joseph.composeplayground.ui.theme.Suit


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                ,
                text = uiState.value.upComingMovieList.movies.toString(),
                fontFamily = Suit,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}