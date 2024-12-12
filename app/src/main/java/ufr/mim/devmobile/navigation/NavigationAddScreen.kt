package ufr.mim.devmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.screens.AddDetailsScreen
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationAddScreen(
    favoriteViewModel: FavoriteViewModel
) {

    val navController = rememberNavController()

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
                favoriteViewModel = favoriteViewModel
            )
        }
    }

}