package ufr.mim.devmobile.navigation

import NavigationViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.viewmodel.UserViewModel
import ufr.mim.devmobile.screens.DetailsScreen
import ufr.mim.devmobile.screens.HomeScreen
import ufr.mim.devmobile.screens.LibrairyScreen

@Composable
fun NavigationHomeScreen(
                         favoriteViewModel: FavoriteViewModel,
                         userViewModel: UserViewModel,
                         navigationViewModel: NavigationViewModel
) {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry) {
        val isInDepth = currentBackStackEntry?.destination?.route != HomeScreens.HomeScreen.route
        navigationViewModel.setInDepthNavigation(isInDepth)
    }

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