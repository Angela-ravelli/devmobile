package ufr.mim.devmobile.navigation

import NavigationViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.viewmodel.ProgressViewModel
import ufr.mim.devmobile.screens.AddDetailsScreen
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationAddScreen(
    progressViewModel: ProgressViewModel,
    favoriteViewModel: FavoriteViewModel,
    navigationViewModel: NavigationViewModel
) {

    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry) {
        val isInDepth = currentBackStackEntry?.destination?.route != AddScreens.AddScreen.route
        navigationViewModel.setInDepthNavigation(isInDepth)
    }

    NavHost(
        navController = navController,
        startDestination = AddScreens.AddScreen.route
    ) {
        composable(AddScreens.AddScreen.route) {
            AddScreen(
                onDetails = {
                    navController.navigate(AddScreens.AddDetailsScreen.route )
                },
                onViewDetails = { id ->
                    navController.navigate(AddScreens.DetailsScreen.route+ "/$id")
                },
                onListDetails = {
                    navController.navigate(AddScreens.LibrairyScreen.route)
                }
            )
        }

        composable(AddScreens.AddDetailsScreen.route) {
            AddDetailsScreen(
                progressViewModel,
                onCancel = { navController.popBackStack() },
                onSave = { navController.navigate(AddScreens.DetailsScreen.route) }
            )
        }

        composable(
            route = AddScreens.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry  ->
            DetailsScreen(
                id = backStackEntry.arguments?.getString("id"),
                progressViewModel = progressViewModel,
                favoriteViewModel = favoriteViewModel
            )
        }
    }

}