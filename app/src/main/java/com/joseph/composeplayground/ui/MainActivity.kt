package com.joseph.composeplayground.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.joseph.composeplayground.ui.home.HomeScreen
import com.joseph.composeplayground.ui.home.detail.DetailScreen
import com.joseph.composeplayground.ui.theme.ComposePlaygroundTheme
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home_screen"
                ) {
                    composable(
                        route = "home_screen"
                    ) {
                        HomeScreen(navController = navController)
                    }

                    composable(
                        route = "detail_screen/{movieId}",
                        arguments = listOf(
                            navArgument("movieId"){ type = NavType.IntType }
                        )
                    ) {
                        DetailScreen()
                    }
                }
            }
        }
    }
}