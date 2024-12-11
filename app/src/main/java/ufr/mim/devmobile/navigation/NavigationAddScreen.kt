package ufr.mim.netfloux.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.navigation.AddScreens
import ufr.mim.devmobile.screens.AddBookScreen
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen

@Composable
fun BooksNavigationComponent(
    progressViewModel: ProgressViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController
) {

    // val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AddScreens.AddScreen.name) {
        composable(AddScreens.AddScreen.name) {
            AddScreen(onDetails = {
                navController.navigate(AddScreens.AddBookScreen.name)
            })
        }

        composable(AddScreens.AddBookScreen.name) {
            AddBookScreen()
        }
    }
}