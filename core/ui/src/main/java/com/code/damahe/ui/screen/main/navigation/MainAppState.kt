package com.code.damahe.ui.screen.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.code.damahe.ui.model.MainDestination

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
): MainAppState {
    return remember(
        navController,
    ) {
        MainAppState(
            navController = navController,
        )
    }
}

@Stable
class MainAppState(
    val navController: NavHostController,
) {
    fun navigateToDestination(route: String) {
        navController.navigateToDestination(route)
    }

    fun selectedDestination(navBackStackEntry: NavBackStackEntry?) =
        navBackStackEntry?.destination?.route ?: MainRoute.HOME

    fun navigateTo(destination: MainDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

fun NavController.navigateToDestination(navRoute: String, navOptions: NavOptions? = null) {
    this.navigate(navRoute, navOptions)
}