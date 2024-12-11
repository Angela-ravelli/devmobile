package ufr.mim.devmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen
import ufr.mim.devmobile.screens.HomeScreen
import ufr.mim.devmobile.screens.LibrairyScreen
import ufr.mim.devmobile.screens.ListScreen

@Composable
fun NavigationListScreen(progressViewModel: ProgressViewModel, favoriteViewModel: FavoriteViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ListScreens.ListScreen.route
    ) {
        composable(ListScreens.ListScreen.route) {
            ListScreen(
                onViewDetails = {
                    navController.navigate(ListScreens.DetailsScreen.route)
                },
                onListDetails = {
                    navController.navigate(ListScreens.LibrairyScreen.route)
                }
            )
        }

        composable(ListScreens.DetailsScreen.route) {
            DetailsScreen(
                //id = "",
                progressViewModel = progressViewModel,
                favoriteViewModel = favoriteViewModel,
            )
        }

        composable(ListScreens.LibrairyScreen.route) {
            LibrairyScreen(
                onViewDetails = { ListScreens.LibrairyScreen.route }
            )
        }
    }
}