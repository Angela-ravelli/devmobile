package ufr.mim.devmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.data.UserViewModel
import ufr.mim.devmobile.screens.AddDetailsScreen
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen
import ufr.mim.devmobile.screens.HomeScreen
import ufr.mim.devmobile.screens.LibrairyScreen

@Composable
fun NavigationHomeScreen(progressViewModel: ProgressViewModel,
                         favoriteViewModel: FavoriteViewModel,
                         userViewModel: UserViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreens.HomeScreen.route
    ) {
        composable(HomeScreens.HomeScreen.route) {
            HomeScreen(
                onViewDetails = {id ->
                    navController.navigate(HomeScreens.DetailsScreen.route + "/$id")
                },
                onListDetails = {
                    navController.navigate(HomeScreens.LibrairyScreen.route)
                },
                userViewModel = userViewModel
            )
        }

        composable(
            route = HomeScreens.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry  ->
            DetailsScreen(
                id = backStackEntry.arguments?.getString("id"),
                progressViewModel = progressViewModel,
                favoriteViewModel = favoriteViewModel
            )
        }

        composable(HomeScreens.LibrairyScreen.route) {
            LibrairyScreen(
                nameList = "",
                favoriteViewModel = favoriteViewModel,
                onViewDetails = { navController.navigate(HomeScreens.DetailsScreen.route) }
            )
        }
    }
}