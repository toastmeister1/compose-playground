package com.joseph.composeplayground.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.joseph.composeplayground.ui.detail.DetailScreen
import com.joseph.composeplayground.ui.home.HomeScreen
import com.joseph.composeplayground.ui.theme.ComposePlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) {
                        DetailScreen()
                    }

                    composable(
                        route = "search_screen/{movieName}",
                        arguments = listOf(navArgument("movieName") { type = NavType.StringType } )
                    ) {
                        // SearchScreen
                    }
                }
            }
        }
    }
}