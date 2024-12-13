package ufr.mim.devmobile.navigation

import ufr.mim.devmobile.viewmodel.NavigationViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.screens.AddDetailsScreen
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen
import androidx.navigation.compose.rememberNavController
import ufr.mim.devmobile.screens.LibrairyScreen

@Composable
fun NavigationAddScreen(
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
                onListDetails = { nameList ->
                    navController.navigate(AddScreens.LibrairyScreen.route+ "/$nameList")
                },
            )
        }

        composable(AddScreens.AddDetailsScreen.route) {
            AddDetailsScreen(
                onCancel = { navController.popBackStack()},
                onAdd = {
                    navController.navigate(AddScreens.AddScreen.route )
                },
            )
        }

        composable(
            route = AddScreens.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry  ->
            backStackEntry.arguments?.getString("id")?.let {
                DetailsScreen(
                    id = it,
                    favoriteViewModel = favoriteViewModel
                )
            }
        }

        composable(
            route = AddScreens.LibrairyScreen.route + "/{nameList}",
            arguments = listOf(navArgument(name = "nameList") { type = NavType.StringType })
        ){ backStackEntry ->
            backStackEntry.arguments?.getString("nameList")?.let {
                LibrairyScreen(
                    nameList = it,
                    onViewDetails = { id ->
                        navController.navigate(AddScreens.DetailsScreen.route + "/$id")
                    },
                    favoriteViewModel = favoriteViewModel,
                )
            }
        }
    }

}