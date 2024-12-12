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
import ufr.mim.devmobile.viewmodel.ProgressViewModel
import ufr.mim.devmobile.screens.DetailsScreen
import ufr.mim.devmobile.screens.LibrairyScreen
import ufr.mim.devmobile.screens.ListScreen

@Composable
fun NavigationListScreen(progressViewModel: ProgressViewModel, favoriteViewModel: FavoriteViewModel, navigationViewModel: NavigationViewModel) {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry) {
        val isInDepth = currentBackStackEntry?.destination?.route != ListScreens.ListScreen.route
        navigationViewModel.setInDepthNavigation(isInDepth)
    }

    NavHost(
        navController = navController,
        startDestination = ListScreens.ListScreen.route
    ) {
        composable(ListScreens.ListScreen.route) {
            ListScreen(
                onViewDetails = { id ->
                    navController.navigate(ListScreens.DetailsScreen.route + "/$id")
                },
                onListDetails = {
                    navController.navigate(ListScreens.LibrairyScreen.route)
                }
            )
        }

        composable(
            route = ListScreens.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                id = backStackEntry.arguments?.getString("id"),
                progressViewModel = progressViewModel,
                favoriteViewModel = favoriteViewModel
            )
        }

        composable(ListScreens.LibrairyScreen.route) {
            LibrairyScreen(
                nameList = "",
                favoriteViewModel = favoriteViewModel,
                onViewDetails = { ListScreens.LibrairyScreen.route }
            )
        }
    }
}