package com.code.damahe.ui.screen.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.code.damahe.system.viewmodel.NewsViewModel
import com.code.damahe.ui.screen.main.HomeScreen

object MainRoute {
    const val HOME = "Home"
    const val FEATURED = "Featured"
    const val BOOKMARK = "Bookmark"
}

@Composable
fun MainHost(
    mainAppState: MainAppState
) {
    
//    val navController = mainAppState.navController
    val newsViewModel: NewsViewModel = hiltViewModel()

    NavHost(navController = mainAppState.navController, startDestination = MainRoute.HOME) {

        composable(MainRoute.HOME) {
            HomeScreen(newsViewModel)
        }
    }

}