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

    val navController = navigationViewModel.navController

    val currentBackStackEntry by navController!!.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry) {
        val isInDepth = currentBackStackEntry?.destination?.route != HomeScreens.HomeScreen.route
        navigationViewModel.setInDepthNavigation(isInDepth)
    }

    if (navController != null) {
        NavHost(
            navController = navController,
            startDestination = HomeScreens.HomeScreen.route
        ) {
            composable(HomeScreens.HomeScreen.route) {
                HomeScreen(
                    onViewDetails = {id ->
                        navController.navigate(HomeScreens.DetailsScreen.route + "/$id")
                    },
                    onListDetails = { nameList ->
                        navController.navigate(HomeScreens.LibrairyScreen.route + "/$nameList")
                    },
                    userViewModel = userViewModel,
                    favoriteViewModel = favoriteViewModel
                )
            }

            composable(
                route = HomeScreens.DetailsScreen.route + "/{id}",
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
                route = HomeScreens.LibrairyScreen.route + "/{nameList}",
                arguments = listOf(navArgument(name = "nameList") { type = NavType.StringType })
            ){ backStackEntry ->
                backStackEntry.arguments?.getString("nameList")?.let {
                    LibrairyScreen(
                        nameList = it,
                        onViewDetails = { id ->
                            navController.navigate(HomeScreens.DetailsScreen.route + "/$id")
                        },
                        favoriteViewModel = favoriteViewModel,
                    )
                }
            }
        }
    }
}