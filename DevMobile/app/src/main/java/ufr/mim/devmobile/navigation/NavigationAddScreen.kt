package ufr.mim.netfloux.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ufr.mim.devmobile.navigation.BooksScreens
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen
import ufr.mim.devmobile.screens.HomeScreen

@Composable
fun BooksNavigationComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BooksScreens.AddScreen.name) {
        composable(BooksScreens.AddScreen.name) {
            AddScreen(onDetails = { id ->
                navController.navigate(BooksScreens.DetailsScreen.name)
            })
        }

        composable(BooksScreens.DetailsScreen.name) {
            DetailsScreen()
        }
    }
}