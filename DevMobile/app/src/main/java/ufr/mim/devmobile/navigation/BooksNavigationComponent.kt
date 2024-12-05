package ufr.mim.netfloux.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ufr.mim.devmobile.navigation.BooksScreens
import ufr.mim.devmobile.screens.DetailsScreen
import ufr.mim.devmobile.screens.HomeScreen

@Composable
fun BooksNavigationComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BooksScreens.HomeScreen.name) {
        composable(BooksScreens.HomeScreen.name) {
            HomeScreen(onDetails = { id ->
                navController.navigate(BooksScreens.DetailsScreen.name + "/$id")
            })
        }

        composable(
            route = BooksScreens.DetailsScreen.name + "/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                /*backAction = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                id = backStackEntry.arguments?.getString("id")*/
            )
        }
    }
}