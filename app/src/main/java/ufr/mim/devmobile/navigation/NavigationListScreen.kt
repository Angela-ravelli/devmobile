package ufr.mim.devmobile.navigation

import ufr.mim.devmobile.viewmodel.NavigationViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import ufr.mim.devmobile.screens.DetailsScreen
import ufr.mim.devmobile.screens.LibrairyScreen
import ufr.mim.devmobile.screens.ListScreen

@Composable
fun NavigationListScreen() {
    // View Models
    val navigationViewModel: NavigationViewModel = viewModel()

    val navController = navigationViewModel.navController

    // On évite les force casts si possible
    val currentBackStackEntry: NavBackStackEntry? = navController?.currentBackStackEntryAsState()?.value

    LaunchedEffect(currentBackStackEntry) {
        val isInDepth = currentBackStackEntry?.destination?.route != ListScreens.ListScreen.route
        navigationViewModel.setInDepthNavigation(isInDepth)
    }

    if (navController != null) {
        NavHost(
            navController = navController,
            startDestination = ListScreens.ListScreen.route
        ) {
            composable(ListScreens.ListScreen.route) {
                ListScreen(
                    onViewDetails = { id ->
                        navController.navigate(ListScreens.DetailsScreen.route + "/$id")
                    },
                    onListDetails = { nameList ->
                        navController.navigate(ListScreens.LibrairyScreen.route + "/$nameList")
                    },
                )
            }

            composable(
                route = ListScreens.DetailsScreen.route + "/{id}",
                arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
            ) { backStackEntry ->
                backStackEntry.arguments?.getString("id")?.let {
                    DetailsScreen(
                        id = it,
                    )
                }
            }

            composable(
                route = ListScreens.LibrairyScreen.route + "/{nameList}",
                arguments = listOf(navArgument(name = "nameList") { type = NavType.StringType })
            ){ backStackEntry ->
                backStackEntry.arguments?.getString("nameList")?.let {
                    LibrairyScreen(
                        nameList = it,
                        onViewDetails = { id ->
                            navController.navigate(ListScreens.DetailsScreen.route + "/$id")
                        },
                    )
                }
            }
        }
    }
}