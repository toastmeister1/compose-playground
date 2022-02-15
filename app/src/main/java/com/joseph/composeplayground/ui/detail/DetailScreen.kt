package com.joseph.composeplayground.ui.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.joseph.composeplayground.util.LoadState


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when(uiState.value.loadState) {
            LoadState.Idle -> {
                // Show DetailScreen
            }
            LoadState.Loading -> {
                // Show LoadingScreen
            }
            LoadState.Failed -> {
                // Show FailedScreen
            }
        }
    }
}