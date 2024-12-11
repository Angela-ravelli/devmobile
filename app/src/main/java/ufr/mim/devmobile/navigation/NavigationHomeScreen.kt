package ufr.mim.devmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.screens.AddDetailsScreen
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen
import ufr.mim.devmobile.screens.HomeScreen
import ufr.mim.devmobile.screens.LibrairyScreen

@Composable
fun NavigationHomeScreen(progressViewModel: ProgressViewModel, favoriteViewModel: FavoriteViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreens.HomeScreen.route
    ) {
        composable(HomeScreens.HomeScreen.route) {
            HomeScreen(
                onViewDetails = {
                    navController.navigate(HomeScreens.DetailsScreen.route)
                },
                onListDetails = {
                    navController.navigate(HomeScreens.LibrairyScreen.route)
                }
            )
        }

        composable(HomeScreens.DetailsScreen.route) {
            DetailsScreen(
                //id = "",
                progressViewModel = progressViewModel,
                favoriteViewModel = favoriteViewModel,
            )
        }

        composable(HomeScreens.LibrairyScreen.route) {
            LibrairyScreen(
                onViewDetails = { navController.navigate(HomeScreens.DetailsScreen.route) }
            )
        }
    }
}