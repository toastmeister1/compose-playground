package com.joseph.composeplayground.ui.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joseph.composeplayground.ui.homescreen.dto.HomeState


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
) {
    val uiState = viewModel.uiState.collectAsState()
}